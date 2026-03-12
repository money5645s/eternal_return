package org.eternalreturn.system


import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.eternalreturn.area.ERAreaSystem
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.ERHitboxEntity
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.projectile.ERProjectile
import org.eternalreturn.util.dpengine.DPEngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.eternalreturn.util.dpengine.datastructure.DeadActorException
import org.eternalreturn.util.dpengine.datastructure.UpdateView
import org.eternalreturn.util.dpengine.physics.OrientedBoxSoA
import org.eternalreturn.util.dpengine.physics.RaySoA
import org.eternalreturn.util.dpengine.physics.TransformSoA
import org.eternalreturn.util.dpengine.physics.UniformGrid
import java.lang.Thread.sleep
import java.util.concurrent.CompletableFuture
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.acos

/**
 * Bukkit 객체들과 유연하게 상호작용하기위한 엔진
 */
class EREngine(val plugin : Plugin, bufferSize : Int = 512) : DPEngine(bufferSize) {

    init{
        val scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimer(plugin, Runnable{this.update()}, 0, 1);
        //scheduler.runTaskTimerAsynchronously(plugin, Runnable{this.updatePhysicsModule()}, 0, 1);
    }

    val areaSystem: ERAreaSystem = ERAreaSystem()

    /**
     * EREntity들을 쿼리하기 위한 해시맵
     */
    private val erEntityMap = HashMap<Entity, EREntity>();

    /**
     * 플레이어들을 따로 업데이트하기 위한 리스트
     * 뷰로써 동작한다.
     */
    val players = UpdateView<ERPlayer>();
    val entities = UpdateView<EREntity>();
    val projectile = UpdateView<ERProjectile>();
    init {
        this.monobehaviourModule.registerUpdateView(players);
        this.monobehaviourModule.registerUpdateView(entities);
        this.monobehaviourModule.registerUpdateView(projectile);
    }

    /**
     * SoA ECS
     * */
    val transformSoA = TransformSoA(bufferSize);
    val uniformGrid = UniformGrid(
        -247.0, 582.0, 832.0,
        -1047.0, 70.0, 32.0,
        50.0, bufferSize);
    val orientedBoxSoA = OrientedBoxSoA(bufferSize, transformSoA, uniformGrid);
    val raySoA = RaySoA(this,bufferSize * 4);

    /**
     * 외부 객체의 pos, rot 모두 가져와서 캐싱하는 함수.
     *
     * ```for(erEntity in erEntityMap.values){}```
     * */
    private fun cachingTransformSoAFromBukkit(){
        for(erEntity in entities.curQueue){ //일단 전부 돌기
            val entity = erEntity.entity;
            val loc = entity.location;
            val px = loc.x;
            val py = loc.y;
            val pz = loc.z;
            val rx = Math.toRadians(loc.yaw.toDouble());
            val ry = Math.toRadians(loc.pitch.toDouble());
            val velocity = entity.velocity;
            transformSoA.cachePosition(erEntity.transformHandle, px, py, pz);
            transformSoA.cacheVelocity(erEntity.transformHandle, velocity.x, velocity.y, velocity.z);
            transformSoA.cacheRotation(erEntity.transformHandle, 0.0, -rx, 0.0); //디버깅해보니 이게 맞음.
            transformSoA.cacheDirection(erEntity.transformHandle, rx, ry, 0.0);
            if(erEntity.isShootingRay()){
                raySoA.addRay(erEntity, px, py + 1.5, pz, rx, ry);
            }
        }
    }

    class EventCmd(val actor : MonobehaviourActor, val event : MonobehaviourEvent);
    val eventCommandQueue = ArrayDeque<EventCmd>();
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
        for(projectile in projectile.curQueue){
            if(!projectile.isAlive())continue;
            raySoA.addRay(projectile,
                projectile.x, projectile.y , projectile.z,
                projectile.dx, projectile.dy, projectile.dz);
        }
    }

    /**
     * EREngine상에서 수정된 velocity를 Bukkit 객체에 적용하는 함수
     *
     * ```for(erEntity in erEntityMap.values){}```
     * */
    fun applyVelocities(){
        val velocityIsModified = transformSoA.isModifiedVelocity;
        val sparse = transformSoA.sparse;
        val velocitySoA = transformSoA.velocity;

        for(erEntity in entities.curQueue){
            if(!erEntity.isAlive())continue;
            val entityID = erEntity.transformHandle.entityID
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

    /**
     * 일괄삭제 함수.
     * */
    private fun deferDisabledEREntity() {
        for(erEntity in entities.curQueue){
            if(!(erEntity.entity.isValid)){
                erEntity.remove();
            }
        }
        for(actorToRemove in removeList){

            val erEntity = actorToRemove;
            val transformHandle = erEntity.transformHandle;
            transformSoA.remove(transformHandle); transformHandle.actor = null;

            if(erEntity is ERHitboxEntity){ //ERHitboxEntity라면
                val obbHandle = erEntity.obbHandle;
                orientedBoxSoA.remove(obbHandle); obbHandle.actor = null;
            }

            //println("[SoA REMOVE] ${this.javaClass.simpleName} T${transformHandle.entityID} O${obbHandle.entityID}")

        }
        removeList.clear();
    }

    fun update(){
        val physicsFuture = CompletableFuture.runAsync { updatePhysicsModule(); }

        monobehaviourModule.consumeEvents();
        monobehaviourModule.updateMonobehaviours();

        physicsFuture.join();

        applyVelocities();
        flushCommandQueue();
        deferDisabledEREntity();
        monobehaviourModule.monobehaviourActorList.update();
    }

    /**
     * 제거될 EREntity들의 리스트. removeAll() 함수 호출 시 일괄 삭제됨.
     * */
    private val removeList = ArrayList<EREntity>();
    fun addRemoveList(erEntity: EREntity){
        removeList.add(erEntity);
    }

    /**
     * Entity를 통해서 MonobehaviourActor를 접근하기 위해 필요한 함수
     * 일반 registerMonobehaviourActor를 통해 등록 시 Entity를 통해 접근이 불가해짐.
     */
    fun registerBukkitActor(entity: Entity, actor: EREntity) {
        if(erEntityMap.contains(entity)){
            val oldEREntity = erEntityMap[entity]!!;
            oldEREntity.remove();
            //println("Map disabled : {${entity.javaClass.simpleName}, ${oldEREntity.javaClass.simpleName}}")
        }

        monobehaviourModule.register(actor);
        erEntityMap[entity] = actor;
        try {
            entities.add(actor);
            if (entity is Player && actor is ERPlayer) {
                players.add(actor);
            }
        } catch (e: DeadActorException) {
            e.printStackTrace()
        }


        //println("Map added : {${entity.javaClass.simpleName}, ${actor.javaClass.simpleName}}")

    }

    /**
     * 해당 Entity 객체에 맞는 EREntity(extends from MonobehaviourActor)를 반환함.
     * 해당 객체가 더 이상 쓰이지 않는 객체인 경우 (isAlive() == false) 조회할 시점에서 제거함.
     */
    fun getEREntity(entity: Entity): EREntity? {
        val erEntity : EREntity? = erEntityMap[entity]
        if(erEntity == null){
            return null;
        }
        if (!erEntity.isAlive()) {
            erEntityMap.remove(entity, erEntity);
            return null;
        }
        return erEntity
    }





}