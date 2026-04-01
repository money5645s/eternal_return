package org.dpengine

import org.bukkit.entity.Entity
import org.dpengine.datastructure.DeadActorException
import org.dpengine.datastructure.UpdateView
import org.dpengine.monobehaviour.MonobehaviourActor
import org.dpengine.monobehaviour.MonobehaviourEvent
import org.dpengine.physics.Handle
import org.dpengine.physics.OrientedBoxSoA
import org.dpengine.physics.RaySoA
import org.dpengine.physics.TransformSoA
import org.dpengine.physics.UniformGrid
import org.eternalreturn.erentity.DPEntity
import org.eternalreturn.erentity.DPHitboxEntity
import org.eternalreturn.projectile.DProjectile
import java.util.concurrent.CompletableFuture

/**
 * Bukkit 객체들과 유연하게 상호작용하기위한 엔진
 */
open class DPhysicsBukkitEngine(bufferSize : Int = 512) : DPEngine(bufferSize){

    val entities = UpdateView<DPEntity>();
    val entityList : List<DPEntity> get() = entities.curQueue;
    val projectile = UpdateView<DProjectile>();
    val projectileList : List<DProjectile> get() = projectile.curQueue;
    init {
        this.monobehaviourModule.registerUpdateView(entities);
        this.monobehaviourModule.registerUpdateView(projectile);
    }

    /**
     * EREntity들을 쿼리하기 위한 해시맵
     */
    internal val dpEntityMap = HashMap<Entity, DPEntity>();

    /**
     * SoA ECS
     * */
    val transformSoA = TransformSoA(bufferSize);
    val uniformGrid = UniformGrid(
        -247.0, 582.0, 832.0,
        -1047.0, 70.0, 32.0,
        50.0, bufferSize
    );
    val orientedBoxSoA = OrientedBoxSoA(bufferSize, transformSoA, uniformGrid);
    val raySoA = RaySoA(this, bufferSize * 4);

    /**
     * 외부 객체의 pos, rot 모두 가져와서 캐싱하는 함수.
     *
     * ```for(DPEntity in DPEntityMap.values){}```
     * */
    private fun cachingTransformSoAFromBukkit(){
        for(dpEntity in entities.curQueue){ //일단 전부 돌기
            val entity = dpEntity.entity;
            val loc = entity.location;
            val px = loc.x;
            val py = loc.y;
            val pz = loc.z;
            val rx = Math.toRadians(loc.yaw.toDouble());
            val ry = Math.toRadians(loc.pitch.toDouble());
            val velocity = entity.velocity;

            val thandle = Handle(dpEntity.transformHandle);

            transformSoA.cachePosition (thandle, px, py, pz);
            transformSoA.cacheVelocity (thandle, velocity.x, velocity.y, velocity.z);
            transformSoA.cacheRotation (thandle, 0.0, -rx, 0.0); //디버깅해보니 이게 맞음.
            transformSoA.cacheDirection(thandle, rx, ry, 0.0);
            if(dpEntity.isShootingRay()){
                raySoA.addRay(dpEntity, px, py + 1.5, pz, rx, ry);
            }
        }
    }

    class EventCmd(val actor : MonobehaviourActor, val event : MonobehaviourEvent);
    /**
     * PhysicsModule 내에서 호출된 EventCommand들을 받기 위한 특수한 큐
     * */
    val eventCommandQueue = ArrayDeque<EventCmd>();

    /**
     * 다른 스레드 위에서 동작한다.
     * */
    private fun updatePhysicsModule() {
        cachingTransformSoAFromBukkit();
        createRaysFromProjectile();
        orientedBoxSoA.updateCacheFromTransform();
        orientedBoxSoA.rebuildGrid();
        orientedBoxSoA.rayCastSoA(eventCommandQueue, raySoA);
        orientedBoxSoA.collideGridCylinder(); //일단 Cylinder로 콜라이딩
        raySoA.freeRays(); //Ray 들 모두 제거
    }

    private fun createRaysFromProjectile(){
        for(projectile in projectile.curQueue){//동시성 문제 있음.
            raySoA.addRay(projectile,
                projectile.x, projectile.y , projectile.z,
                projectile.dx, projectile.dy, projectile.dz);
        }
    }

    /**
     * EREngine상에서 수정된 velocity를 Bukkit 객체에 적용하는 함수
     *
     * ```for(DPEntity in DPEntityMap.values){}```
     * */
    fun applyVelocities(){
        val velocityIsModified = transformSoA.isModifiedVelocity;
        val sparse = transformSoA.sparse;
        val velocitySoA = transformSoA.velocity;

        for(erEntity in entities.curQueue){
            if(!erEntity.isAlive())continue;
            val entityID = Handle(erEntity.transformHandle).entityID
            val denseID = sparse[entityID];

            if(velocityIsModified[denseID]){
                velocityIsModified[denseID] = false
                val x = velocitySoA.x[denseID];
                val y = velocitySoA.y[denseID];
                val z = velocitySoA.z[denseID];
                erEntity.applyBukkitVelocityOnMainThread(x, y, z);
            }
        }
    }

    override fun flushCommandQueue(){
        super.flushCommandQueue();
        while(eventCommandQueue.isNotEmpty()){
            val command = eventCommandQueue.removeFirst();
            val actor = command.actor;
            val event = command.event;
            if(actor.isAlive()){
                actor.submitEvent(event);
            }
        }
    }

    override fun update(){

        val physicsFuture = CompletableFuture.runAsync { updatePhysicsModule(); }
        monobehaviourModule.consumeEvents();
        monobehaviourModule.updateMonobehaviours();
        physicsFuture.join();

        applyVelocities();
        flushCommandQueue();
        removeAllDisabledDPEntity();
        monobehaviourModule.monobehaviourActorList.update();

    }

    /**
     * 일괄삭제 함수.
     * */
    private fun removeAllDisabledDPEntity() {
        for(erEntity in entities.curQueue){
            if(!(erEntity.entity.isValid)){
                erEntity.remove();
            }
        }
        for(actorToRemove in removeList){
            //println("${actorToRemove.javaClass.simpleName} 삭제 시도...")
            try{
                val erEntity = actorToRemove;
                val transformHandle = Handle(erEntity.transformHandle);
                transformSoA.remove(transformHandle);

                if(erEntity is DPHitboxEntity){ //ERHitboxEntity라면
                    val obbHandle = Handle(erEntity.obbHandle);
                    orientedBoxSoA.remove(obbHandle);
                }
            }catch(e : RuntimeException){
                println("${actorToRemove.javaClass.simpleName}의 삭제 중 예외가 발생하였습니다.");
                e.printStackTrace();
            }
        }
        removeList.clear();
    }

    /**
     * 제거될 EREntity들의 리스트. removeAll() 함수 호출 시 일괄 삭제됨.
     * */
    private val removeList = ArrayList<DPEntity>();
    fun addRemoveList(dpEntity: DPEntity){
        removeList.add(dpEntity);
    }

    /**
     * Entity를 통해서 MonobehaviourActor를 접근하기 위해 필요한 함수
     *
     * 그냥 객체 생성 시에는 getEREntity()를 호출하여도 값은 얻을 수 없음.
     *
     * Actor의 레퍼런스 카운터를 올리지 않음.
     */
    open fun registerBukkitActor(entity: Entity, actor: DPEntity) {
        if(dpEntityMap.contains(entity)){
            val oldEREntity = dpEntityMap[entity]!!;
            oldEREntity.remove();
        }

        dpEntityMap[entity] = actor;
        try {
            entities.add(actor);
        } catch (e: DeadActorException) {
            e.printStackTrace()
        }

    }

    /**
     * 해당 Entity 객체에 맞는 DPEntity(extends from MonobehaviourActor)를 반환함.
     * 해당 객체가 더 이상 쓰이지 않는 객체인 경우 (isAlive() == false) 조회할 시점에서 제거함.
     */
    fun getEREntity(entity: Entity): DPEntity? {
        val dpEntity : DPEntity? = dpEntityMap[entity]
        if(dpEntity == null){
            return null;
        }
        if (!dpEntity.isAlive()) {
            dpEntityMap.remove(entity, dpEntity);
            return null;
        }
        return dpEntity
    }
}