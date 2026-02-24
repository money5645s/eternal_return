package org.eternalreturn.system

import it.unimi.dsi.fastutil.doubles.DoubleArrayList
import it.unimi.dsi.fastutil.ints.IntArrayList
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.util.dpengine.DPEngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import org.eternalreturn.util.dpengine.datastructure.DeadActorException
import org.eternalreturn.util.dpengine.datastructure.UpdateView
import org.eternalreturn.util.dpengine.physics.OrientedBoxSoA
import org.eternalreturn.util.dpengine.physics.TransformSoA
import org.eternalreturn.util.dpengine.physics.UniformGrid
import kotlin.math.cos
import kotlin.math.sin

/**
 * Bukkit 객체들과 유연하게 상호작용하기위한 엔진
 */
class EREngine(bufferSize : Int = 512) : DPEngine(bufferSize) {

    /**
     * EREntity들을 쿼리하기 위한 해시맵
     */
    private val erEntityMap = HashMap<Entity, EREntity>(); //절대 loop돌리지 말 것.
    private val mcEntities = ArrayList<Entity>();

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
    private val uniformGrid = UniformGrid(
        -247.0, 582.0, 832.0,
        -1047.0, 70.0, 168.0,
        50.0, bufferSize);
    val orientedBoxSoA = OrientedBoxSoA(bufferSize, transformSoA, uniformGrid);


    var lastRay = 0;
    val posX = DoubleArray(bufferSize);// 레이캐스팅용 객체
    val posY = DoubleArray(bufferSize);// 레이캐스팅용 객체
    val posZ = DoubleArray(bufferSize);// 레이캐스팅용 객체
    val dirX = DoubleArray(bufferSize);// 레이캐스팅용 객체
    val dirY = DoubleArray(bufferSize);// 레이캐스팅용 객체
    val dirZ = DoubleArray(bufferSize);// 레이캐스팅용 객체
    val actor = IntArray(bufferSize);
    var rayGeneration : Long = 0;

    public override fun update() {
        var filterBool = false;
        var filterCount = 0; var deadActor = 0;
        val filterEREntity = ArrayList<EREntity>();

        for(erEntity in erEntityMap.values){ //일단 전부 돌기

            //디버깅용 필터
            filterCount++;
            if(erEntity.referenceCount == 0) deadActor++;
            filterEREntity.add(erEntity);

            val loc = erEntity.entity.location;
            transformSoA.setPosition(erEntity.transformHandle, loc.x, loc.y, loc.z);
            transformSoA.setRotation(erEntity.transformHandle, 0.0, loc.yaw.toDouble(), 0.0);
            transformSoA.setDirection(erEntity.transformHandle, loc.yaw.toDouble(), loc.pitch.toDouble(), 0.0);
            if(erEntity.isShootingRay()){
                filterBool = true;
                println("OBB : ${orientedBoxSoA.getNumOfEntities()} TRSF : ${transformSoA.getNumOfEntities()}")
                //여긴 또 반대야;;
                val rotX = Math.toRadians(loc.yaw.toDouble())
                val rotY = Math.toRadians(loc.pitch.toDouble())
                val xz = cos(rotY)
                val dx = -xz * sin(rotX); val dy = -sin(rotY); val dz =  xz * cos(rotX)
                posX[lastRay] = loc.x; posY[lastRay] = loc.y; posZ[lastRay] = loc.z;
                dirX[lastRay] = dx;    dirY[lastRay] = dy;    dirZ[lastRay] = dz;
                //actor[lastRay] =
                lastRay++;
            }
        }

        if(filterBool){
            println("[FILTER] EREntityCount : $filterCount deadActorCount : $deadActor")
            for(actor in filterEREntity){
                println("T${actor.transformHandle.entityID} O${actor.obbHandle.entityID}")
            }
        }

        orientedBoxSoA.updatePosCache();
        orientedBoxSoA.updateRotCache();
        orientedBoxSoA.rebuildGrid();

        if(lastRay > 0){
            for(i in 0 until lastRay){
                println("rayCasting... : [${posX[i]}, ${posY[i]}, ${posZ[i]}] + t * [${dirX[i]}, ${dirY[i]}, ${dirZ[i]}]")
                val hitList = IntArrayList(8);
                orientedBoxSoA.rayCastGridOptim(
                    rayGeneration, i, hitList,posX[i], posY[i], posZ[i], dirX[i], dirY[i], dirZ[i]);

                if(hitList.isNotEmpty()){
                    for(j in 0 until hitList.size){
                        val entityID = hitList.getInt(j);
                        val actor = orientedBoxSoA.getConnectedActor(entityID) as EREntity;

                        val posDebugStr = transformSoA.getDebugString(actor.transformHandle);
                        println("HITLIST -> [$j] : ${actor.javaClass.simpleName} ${actor.transformHandle.entityID} ${actor.obbHandle.entityID} isValid : ${orientedBoxSoA.isValid(actor.obbHandle)}"); // 디버깅용
                    }
                }
            }
            rayGeneration = (rayGeneration xor 1)
            lastRay = 0;
        }

    }

    /**
     * Entity를 통해서 MonobehaviourActor를 접근하기 위해 필요한 함수
     * 일반 registerMonobehaviourActor를 통해 등록 시 Entity를 통해 접근이 불가해짐.
     */
    fun registerBukkitActor(entity: Entity, actor: EREntity) {
        erEntityMap[entity]?.remove(); //remove prev actor
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
}