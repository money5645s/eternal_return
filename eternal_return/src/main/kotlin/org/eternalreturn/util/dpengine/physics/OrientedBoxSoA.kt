package org.eternalreturn.util.dpengine.physics

import it.unimi.dsi.fastutil.ints.IntArrayList
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.command.defaults.BukkitCommand
import org.bukkit.scheduler.BukkitScheduler
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.events.EREntityRayCastEvent
import org.eternalreturn.erentity.globalmonobehav.EntityRayCastingMeleeAttack
import org.eternalreturn.system.PluginInstance
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import org.eternalreturn.util.dpengine.geometry.OBB
import org.eternalreturn.util.dpengine.geometry.Vector3
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin
import kotlin.math.sqrt

class OrientedBoxSoA(
    size : Int,
    private val transformSoA : TransformSoA,
    val grid : UniformGrid
) : SoAModule(size) {
    private val size = Vec3SoA(size); // width, height, depth 저장
    private val locPos = Vec3SoA(size);

    private val posCache = Vec3SoA(size); //transform.pos 캐싱
    private val isValidPos = IntArray(size){ 0 };//해당 포지션이 valid한 포지션인지 확인

    private val rotMatCache = Mat3x3SoA(size); //회전행렬 저장
    private val isValidRotMat = IntArray(size){ 0 };//해당 회전행렬이 valid한 회전행렬인지 확인

    private val transformHandleList = ArrayList<Handle>(size);


    fun create(transformHandle : Handle, width : Double, height : Double, depth : Double, locX : Double, locY : Double, locZ : Double) : Handle{
        val (entityID, denseID, generation) = super.createHandle(); // (entityID, denseID, generation)
        size.allocSoA(denseID, width, height, depth);
        locPos.allocSoA(denseID, locX, locY, locZ);
        rotMatCache.allocSoA(denseID, 0.0, 0.0, 0.0 ,0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        posCache.allocSoA(denseID, 0.0,0.0,0.0);
        transformHandleList.addLast(transformHandle);
        return Handle(entityID, generation)
    }

    fun remove(handle : Handle){
        val (idx0, idx1) = super.removeHandle(handle);
        size.overwrite(idx0, idx1);
        locPos.overwrite(idx0, idx1);

        rotMatCache.overwrite(idx0, idx1);
        posCache.overwrite(idx0, idx1);

        isValidPos[idx0] = 0;
        isValidRotMat[idx0] = 0;

        transformHandleList[idx0] = transformHandleList[idx1];
        transformHandleList.removeLast();
    }

    val pivot =  arrayOf(
        arrayOf( 1,  1,  1),
        arrayOf(-1,  1,  1),
        arrayOf( 1, -1,  1),
        arrayOf( 1,  1, -1),
        arrayOf(-1, -1,  1),
        arrayOf( 1, -1, -1),
        arrayOf(-1,  1, -1),
        arrayOf(-1, -1, -1),
    )

    fun debugOrientedBox(){
        val world = PluginInstance.getServerInstance().server.worlds.first();
        val num = getNumOfEntities();
        val mat = rotMatCache;
        for(i in 0 until num){
            val px = posCache.x[i];
            val py = posCache.y[i];
            val pz = posCache.z[i];
            val sx = size.x[i];
            val sy = size.y[i];
            val sz = size.z[i];

            val m00 = mat.m00[i]; val m10 = mat.m10[i]; val m20 = mat.m20[i]
            val m01 = mat.m01[i]; val m11 = mat.m11[i]; val m21 = mat.m21[i]
            val m02 = mat.m02[i]; val m12 = mat.m12[i]; val m22 = mat.m22[i]

            for(p in 0 until 8){
                val sxr = sx * pivot[p][0]; // +인지 -인지 위의 pivot테이블에서 구해줌
                val syr = sy * pivot[p][1]; //
                val szr = sz * pivot[p][2]; //

                val sxr_ = sxr * m00 + syr * m01 + szr * m02;
                val syr_ = sxr * m10 + syr * m11 + szr * m12;
                val szr_ = sxr * m20 + syr * m21 + szr * m22;

                world.spawnParticle(Particle.DUST, Location(world, sxr_ + px, syr_ + py, szr_ + pz),1,Particle.DustOptions(Color.BLUE, 1.0f))

            }
        }
    }

    fun updateCacheFromTransform(){
        updatePosCache();
        updateRotCache();
    }

    private fun updatePosCache(){
        val tsparse = transformSoA.sparse;
        val position = transformSoA.position;
        val numOfEntity = getNumOfEntities();
        for(id in 0 until numOfEntity){
            //OBB의 위치 구하기 from 소유자의 Transform 데이터
            //if(isValidPos[id] == 1) continue;
            //isValidPos[id] = 1;
            val tID = tsparse[transformHandleList[id].entityID];
            posCache.x[id] = position.x[tID] + locPos.x[tID];
            posCache.y[id] = position.y[tID] + locPos.y[tID];
            posCache.z[id] = position.z[tID] + locPos.z[tID];
        }
    }

    private fun updateRotCache(){
        val tsparse = transformSoA.sparse;
        val rotation = transformSoA.rotation;
        val numOfEntity = getNumOfEntities();

        for(id in 0 until numOfEntity){
            //OBB의 위치 구하기
            //if(isValidRotMat[id] == 1) continue;
            //isValidRotMat[id] = 1;
            val tID = tsparse[transformHandleList[id].entityID];

            val a = rotation.x[tID]; val sinA = sin(a); val cosA = cos(a)
            val b = rotation.y[tID]; val sinB = sin(b); val cosB = cos(b)
            val c = rotation.z[tID]; val sinC = sin(c); val cosC = cos(c)

            val m = rotMatCache;
            m.m00[id] = cosB * cosC
            m.m01[id] = cosC * sinA * sinB - cosA * sinC
            m.m02[id] = sinA * sinC + cosA * cosC * sinB

            m.m10[id] = cosB * sinC
            m.m11[id] = cosA * cosC + sinA * sinB * sinC
            m.m12[id] = cosA * sinB * sinC - cosC * sinA

            m.m20[id] = -sinB
            m.m21[id] = cosB * sinA
            m.m22[id] = cosA * cosB

        }
    }


    /**
     * 플레이어 객체 하나에 대해 rayCast 진행
     * */
    val rayGeneration = IntArray(size) { -1 };
    fun rayCast(generation : Int, rayID : Int, hitList : IntArrayList,
                px : Double, py : Double, pz : Double,
                dirX : Double, dirY : Double, dirZ: Double){
        hitList.clear();
        val entityNum = getNumOfEntities();
        for(id in 0 until entityNum){
            if(grid.alreadyChecked[id] != rayID || rayGeneration[id] != generation){
                grid.alreadyChecked[id] = rayID;
                rayGeneration[id] = generation;
                if (rayTestOne(id, px, py, pz, dirX, dirY, dirZ)) {
                    hitList.add(dense[id]); //entityID 반환
                }
            }
        }
    }

    private fun rayTestOne(
        id: Int,
        px: Double, py: Double, pz: Double,
        dirX: Double, dirY: Double, dirZ: Double
    ): Boolean {

        val mat = rotMatCache;

        val delX = px - posCache.x[id]
        val delY = py - posCache.y[id]
        val delZ = pz - posCache.z[id]

        val m00 = mat.m00[id]; val m10 = mat.m10[id]; val m20 = mat.m20[id]
        val m01 = mat.m01[id]; val m11 = mat.m11[id]; val m21 = mat.m21[id]
        val m02 = mat.m02[id]; val m12 = mat.m12[id]; val m22 = mat.m22[id]

        val locPx = delX * m00 + delY * m10 + delZ * m20
        val locPy = delX * m01 + delY * m11 + delZ * m21
        val locPz = delX * m02 + delY * m12 + delZ * m22

        val locDx = dirX * m00 + dirY * m10 + dirZ * m20
        val locDy = dirX * m01 + dirY * m11 + dirZ * m21
        val locDz = dirX * m02 + dirY * m12 + dirZ * m22

        // Slab (AABB)
        var tmin = Double.NEGATIVE_INFINITY
        var tmax = Double.POSITIVE_INFINITY

        fun slab(origin: Double, dir: Double, half: Double) {
            // dir==0 처리
            if (dir == 0.0) {
                if (origin < -half || origin > half) {
                    tmin = 1.0
                    tmax = 0.0
                }
                return
            }
            val inv = 1.0 / dir
            val t1 = (-half - origin) * inv
            val t2 = ( half - origin) * inv
            val lo = kotlin.math.min(t1, t2)
            val hi = kotlin.math.max(t1, t2)
            if (lo > tmin) tmin = lo
            if (hi < tmax) tmax = hi
        }

        slab(locPx, locDx, size.x[id])
        if (tmin > tmax) return false
        slab(locPy, locDy, size.y[id])
        if (tmin > tmax) return false
        slab(locPz, locDz, size.z[id])
        if (tmin > tmax) return false

        return tmax >= kotlin.math.max(tmin, 0.0)
    }


    fun rebuildGrid() {
        // posCache는 Vec3SoA니까 내부 DoubleArray를 넘긴다고 가정
        val m = rotMatCache;
        grid.updateGrid(
            posCache.x, posCache.y, posCache.z,
            size.x, size.y, size.z,
            m.m00, m.m01, m.m02,
            m.m10, m.m11, m.m12,
            m.m20, m.m21, m.m22,
            getNumOfEntities());
    }

    fun rayCastGrid(
        generation: Int,
        rayID : Int,
        hitList: IntArrayList,
        px: Double, py: Double, pz: Double,
        dirX: Double, dirY: Double, dirZ: Double,
        maxDist: Double = 1000.0
    ) {

        val visitor = fun(cell : Int) : Boolean {
            val start = grid.cellStart[cell]
            val count = grid.cellCount[cell]
            val end = start + count

            var i = start - 1
            while (i < end - 1) { i++
                val id = grid.cellIndices[i]
                if(grid.alreadyChecked[id] != rayID || rayGeneration[id] != generation){
                    grid.alreadyChecked[id] = rayID;
                    rayGeneration[id] = generation;
                    if (rayTestOne(id, px, py, pz, dirX, dirY, dirZ)) {
                        hitList.add(dense[id]) // sparseID나 entityID로 바꾸고 싶으면 여기서
                    }
                }
            }
            return false // true면 조기 종료. (ex: “첫 히트만 원함”이면 true로 바꾸면 됨)
        }

        grid.traverseRayCells(px, py, pz, dirX, dirY, dirZ, maxDist, visitor);
    }

    fun rayCastGridOptim(
        generation: Int,
        rayID : Int,
        hitList: IntArrayList,
        px: Double, py: Double, pz: Double,
        dirX: Double, dirY: Double, dirZ: Double,
        maxDist: Double = 1000.0){
        var cell = grid.initializeRay(px, py, pz, dirX, dirY, dirZ, maxDist);
        while(true){
            if(cell == -1) break;

            val start = grid.cellStart[cell]
            val count = grid.cellCount[cell]
            val end = start + count

            for(i in start until end) {
                val id = grid.cellIndices[i]
                if(grid.alreadyChecked[id] != rayID || rayGeneration[id] != generation){
                    grid.alreadyChecked[id] = rayID;
                    rayGeneration[id] = generation;
                    if (rayTestOne(id, px, py, pz, dirX, dirY, dirZ)) {
                        hitList.add(dense[id]) // sparseID나 entityID로 바꾸고 싶으면 여기서
                    }
                }
            }
            cell = grid.nextCell();
        }
    }

    fun getConnectedActor(entityID : Int) : MonobehaviourActor{
        val denseID = sparse[entityID];
        val tHandle = transformHandleList[denseID];
        return tHandle.actor!!
    }



    fun rayCastSoA(raySoA : RaySoA){

        val lastRay = raySoA.lastRay;
        val posX = raySoA.posX;
        val posY = raySoA.posY;
        val posZ = raySoA.posZ;

        val dirX = raySoA.dirX;
        val dirY = raySoA.dirY;
        val dirZ = raySoA.dirZ;

        val rayGeneration = raySoA.rayGeneration;

        if(lastRay > 0){
            for(i in 0 until lastRay){
                //println("rayCasting... : [${posX[i]}, ${posY[i]}, ${posZ[i]}] + t * [${dirX[i]}, ${dirY[i]}, ${dirZ[i]}]")
                val hitList = IntArrayList(8); //배열을 만들어 반환 -> 나중에 고쳐야 할 수도
                rayCastGridOptim(rayGeneration, i, hitList,posX[i], posY[i], posZ[i], dirX[i], dirY[i], dirZ[i]);

                val shooter = raySoA.actors[i];
                val hitActorList = ArrayList<EREntity>();

                if(hitList.isNotEmpty()){
                    for(j in 0 until hitList.size){
                        val entityID = hitList.getInt(j);
                        val hitActor = getConnectedActor(entityID) as EREntity;
                        hitActorList.add(hitActor)
                        println("HITLIST -> [$j] : ${hitActor.javaClass.simpleName} ${hitActor.transformHandle.entityID} ${hitActor.obbHandle.entityID} isValid : ${isValid(hitActor.obbHandle)}"); // 디버깅용
                    }
                }

                //이벤트를 전달한다.
                if(shooter is EREntity){ //광선을 쏜 개체가 EREntity라면
                    shooter.submitEvent(EREntityRayCastEvent(shooter,hitActorList));
                }else{
                    //광선을 쏜 개체가 Projectile이라면
                }

            }
        }
    }

    fun collideGridAABB(){

        val colliderNum = getNumOfEntities()
        val totalCells = grid.dimX * grid.dimY * grid.dimZ

        val checked = BooleanArray(colliderNum * colliderNum)

        for(cell in 0 until totalCells){

            val start = grid.cellStart[cell]
            val end = start + grid.cellCount[cell]

            for(i in start until end){

                val obbA = grid.cellIndices[i]

                for(j in i+1 until end){

                    val obbB = grid.cellIndices[j]

                    val id = obbA * colliderNum + obbB
                    if(checked[id]) continue
                    checked[id] = true
                    collideAABB(obbA,obbB)
                }
            }
        }
    }

    val EPSAABB = 1e-4
    private fun collideAABB(obb0: Int, obb1: Int): Boolean {

        val m = rotMatCache

        val m00a = m.m00[obb0]; val m01a = m.m01[obb0]; val m02a = m.m02[obb0]
        val m10a = m.m10[obb0]; val m11a = m.m11[obb0]; val m12a = m.m12[obb0]
        val m20a = m.m20[obb0]; val m21a = m.m21[obb0]; val m22a = m.m22[obb0]

        val m00b = m.m00[obb1]; val m01b = m.m01[obb1]; val m02b = m.m02[obb1]
        val m10b = m.m10[obb1]; val m11b = m.m11[obb1]; val m12b = m.m12[obb1]
        val m20b = m.m20[obb1]; val m21b = m.m21[obb1]; val m22b = m.m22[obb1]

        val sxa = size.x[obb0]; val sya = size.y[obb0]; val sza = size.z[obb0]
        val sxb = size.x[obb1]; val syb = size.y[obb1]; val szb = size.z[obb1]

        val pxa = posCache.x[obb0]; val pya = posCache.y[obb0]; val pza = posCache.z[obb0]
        val pxb = posCache.x[obb1]; val pyb = posCache.y[obb1]; val pzb = posCache.z[obb1]

        // OBB → world AABB half extents
        val hxa = (abs(m00a)*sxa + abs(m01a)*sya + abs(m02a)*sza) * 0.5
        val hya = (abs(m10a)*sxa + abs(m11a)*sya + abs(m12a)*sza) * 0.5
        val hza = (abs(m20a)*sxa + abs(m21a)*sya + abs(m22a)*sza) * 0.5
        val hxb = (abs(m00b)*sxb + abs(m01b)*syb + abs(m02b)*szb) * 0.5
        val hyb = (abs(m10b)*sxb + abs(m11b)*syb + abs(m12b)*szb) * 0.5
        val hzb = (abs(m20b)*sxb + abs(m21b)*syb + abs(m22b)*szb) * 0.5

        val dx = pxb - pxa
        val dy = pyb - pya
        val dz = pzb - pza

        val adx = abs(dx)
        val ady = abs(dy)
        val adz = abs(dz)

        val px = hxa + hxb - adx + EPSAABB
        if (px <= 0.0) return false

        val py = hya + hyb - ady + EPSAABB
        if (py <= 0.0) return false

        val pz = hza + hzb - adz + EPSAABB
        if (pz <= 0.0) return false

        // MTV axis 선택
        var mtvX = 0.0
        var mtvY = 0.0
        var mtvZ = 0.0

        if (px < py && px < pz) {
            mtvX = if (dx > 0) px else -px
        }
        else if (py < pz) {
            mtvY = if (dy > 0) py else -py
        }
        else {
            mtvZ = if (dz > 0) pz else -pz
        }

        val obbActor0 = transformHandleList[obb0].actor as EREntity;
        val obbActor1 = transformHandleList[obb1].actor as EREntity;
        obbActor0.addVelocity(-mtvX * 0.5, -mtvY * 0.5, -mtvZ * 0.5);
        obbActor1.addVelocity(+mtvX * 0.5, +mtvY * 0.5, +mtvZ * 0.5);
        return true
    }


}
