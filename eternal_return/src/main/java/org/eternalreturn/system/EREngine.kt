package org.eternalreturn.system

import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.util.Vector
import org.eternalreturn.eranimal.ERAnimal
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.util.dpengine.DPEngine
import org.eternalreturn.util.dpengine.datastructure.DeadActorException
import org.eternalreturn.util.dpengine.datastructure.UpdateView
import org.eternalreturn.util.dpengine.physics.OrientedBoxSoA
import org.eternalreturn.util.dpengine.physics.RaySoA
import org.eternalreturn.util.dpengine.physics.TransformSoA
import org.eternalreturn.util.dpengine.physics.UniformGrid

/**
 * Bukkit 객체들과 유연하게 상호작용하기위한 엔진
 */
class EREngine(bufferSize : Int = 512) : DPEngine(bufferSize) {

    /**
     * EREntity들을 쿼리하기 위한 해시맵
     */
    private val erEntityMap = HashMap<Entity, EREntity>();
    //private val erEntityLookupTable = Array<EREntity?>(bufferSize shl 1){null};
    //fun lookup(sparseID : Int) : EREntity?{
    //    return erEntityLookupTable[sparseID];
    //}

    /**
     * 플레이어들을 따로 업데이트하기 위한 리스트
     * 뷰로써 동작한다.
     */
    val players = UpdateView<ERPlayer>()
    val erEntities = UpdateView<EREntity>();
    init {
        this.monobehaviourModule.registerUpdateView(players);
        this.monobehaviourModule.registerUpdateView(erEntities);
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
    val raySoA = RaySoA(bufferSize * 4);




    /**
     * 외부 객체의 pos, rot 모두 가져와서 캐싱하는 함수.
     *
     * ```for(erEntity in erEntityMap.values){}```
     * */
    private fun cachingTransformSoAFromBukkit(){
        for(erEntity in erEntityMap.values){ //일단 전부 돌기

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

    /**
     * EREngine상에서 수정된 velocity를 Bukkit 객체에 적용하는 함수
     *
     * ```for(erEntity in erEntityMap.values){}```
     * */
    fun applyVelocities(){

        val velocityIsModified = transformSoA.isModifiedVelocity;
        val positionIsModified = transformSoA.isModifiedPosition;
        val sparse = transformSoA.sparse;
        val velocitySoA = transformSoA.velocity;
        val positionSoA = transformSoA.position;

        for(erEntity in erEntityMap.values){
            val entityID = erEntity.transformHandle.entityID
            val denseID = sparse[entityID];

            if(velocityIsModified[denseID]){
                val x = velocitySoA.x[denseID];
                val y = velocitySoA.y[denseID];
                val z = velocitySoA.z[denseID];
                erEntity.applyBukkitVelocityOnMainThread(x, y, z);
            }

            //if(positionIsModified[denseID]){
            //    val x = positionSoA.x[denseID];
            //    val y = positionSoA.y[denseID];
            //    val z = positionSoA.z[denseID];
            //    val entity = erEntity.entity;
            //    val loc = entity.location;
            //    loc.x = x;
            //    loc.y = y;
            //    loc.z = z;
            //    entity.teleport(loc);
            //}

        }
    }


    public override fun update() {
        cachingTransformSoAFromBukkit();

        orientedBoxSoA.updateCacheFromTransform();
        orientedBoxSoA.rebuildGrid();

        orientedBoxSoA.debugOrientedBox(); //성능 이슈 심함
        orientedBoxSoA.rayCastSoA(raySoA);

        orientedBoxSoA.collideGridCylinder(); //일단 Cylinder로 콜라이딩

        raySoA.freeRays(); //Ray 들 모두 제거

        applyVelocities(); //속도 적용

        removeAll();
    }

    /**
     * Entity를 통해서 MonobehaviourActor를 접근하기 위해 필요한 함수
     * 일반 registerMonobehaviourActor를 통해 등록 시 Entity를 통해 접근이 불가해짐.
     */
    fun registerBukkitActor(entity: Entity, actor: EREntity) {
        removeBukkitActor(entity);
        monobehaviourModule.register(actor)
        erEntities.add(actor);
        try {
            if (entity is Player && actor is ERPlayer) {
                players.add(actor)
            }
            erEntityMap[entity] = actor
        } catch (e: DeadActorException) {
            e.printStackTrace()
        }
    }

    /**
     * HashMap에서 버킷 객체를 제거한다.
     * */
    fun removeBukkitActor(entity: Entity){
        erEntityMap[entity]?.remove()
        erEntityMap.remove(entity);
    }

    /**
     * 해당 Entity 객체에 맞는 EREntity(extends from MonobehaviourActor)를 반환함.
     * 해당 객체가 더 이상 쓰이지 않는 객체인 경우 (isAlive() == false) 조회할 시점에서 제거함.
     */
    fun getEREntity(entity: Entity): EREntity? {
        var erEntity = erEntityMap[entity]
        if (!erEntity!!.isAlive()) {
            erEntityMap.remove(entity, erEntity)
            erEntity = null //제거 시에는 null을 반환하도록 설계
        }
        return erEntity
    }

    /**
     * 제거될 EREntity들의 리스트. removeAll() 함수 호출 시 일괄 삭제됨.
     * */
    private val removeList = ArrayList<EREntity>();
    fun remove(erEntity: EREntity){
        removeList.add(erEntity);
    }

    /**
     * 일괄삭제 함수.
     * */
    private fun removeAll() {
        val size = removeList.size
        for(i in 0 until size){

            val erEntity = removeList[i];
            val transformHandle = erEntity.transformHandle;
            val obbHandle = erEntity.obbHandle;

            transformSoA.remove(transformHandle); transformHandle.actor = null;
            orientedBoxSoA.remove(obbHandle); obbHandle.actor = null;

            removeBukkitActor(erEntity.entity);

            println("[SoA REMOVE] ${this.javaClass.simpleName} T${transformHandle.entityID} O${obbHandle.entityID}")

        }
        removeList.clear();
    }
}