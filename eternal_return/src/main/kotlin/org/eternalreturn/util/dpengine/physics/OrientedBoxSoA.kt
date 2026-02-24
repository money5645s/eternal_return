package org.eternalreturn.util.dpengine.physics

import it.unimi.dsi.fastutil.ints.IntArrayList
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import kotlin.math.cos
import kotlin.math.sin

class OrientedBoxSoA(
    size : Int,
    private val transformSoA : TransformSoA,
    val grid : UniformGrid
) : SoAModule(size) {

    val size = Vec3SoA(size); // width, height, depth 저장

    private val posCache = Vec3SoA(size); //transform.pos 캐싱
    private val isValidPos = IntArray(size){ 0 };//해당 포지션이 valid한 포지션인지 확인

    private val rotMatCache = Mat3x3SoA(size); //회전행렬 저장
    private val isValidRotMat = IntArray(size){ 0 };//해당 회전행렬이 valid한 회전행렬인지 확인

    private val transformHandleList = ArrayList<Handle>(size);


    fun create(transformHandle : Handle, width : Double, height : Double, depth : Double) : Handle{
        val triple = super.createHandle(); // (entityID, denseID, generation)
        val entityID = triple.first;
        val denseID = triple.second;
        val generation = triple.third;
        size.allocSoA(denseID, width, height, depth);
        rotMatCache.allocSoA(denseID, 0.0, 0.0, 0.0 ,0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        posCache.allocSoA(denseID, 0.0,0.0,0.0);
        transformHandleList.addLast(transformHandle);
        return Handle(entityID, generation)
    }

    fun remove(handle : Handle){
        val pair = super.removeHandle(handle);
        size.overwrite(pair.first, pair.second);
        rotMatCache.overwrite(pair.first, pair.second);
        posCache.overwrite(pair.first, pair.second);

        isValidPos[pair.first] = 0;
        isValidRotMat[pair.first] = 0;

        transformHandleList[pair.first] = transformHandleList[pair.second];
        transformHandleList.removeLast();
    }

    fun updateCacheFromTransfrom(){
        updatePosCache();
        updateRotCache();
    }

    fun updatePosCache(){
        val tsparse = transformSoA.sparse;
        val position = transformSoA.position;
        val numOfEntity = getNumOfEntities();
        for(id in 0 until numOfEntity){
            //OBB의 위치 구하기 from 소유자의 Transform 데이터
            //if(isValidPos[id] == 1) continue;
            //isValidPos[id] = 1;
            val tID = tsparse[transformHandleList[id].entityID];
            posCache.x[id] = position.x[tID]
            posCache.y[id] = position.y[tID]
            posCache.z[id] = position.z[tID]
        }
    }

    fun updateRotCache(){
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

//    fun updateRotCache(){
//        var id = 0;
//        val mat = matCache;
//        val numOfEntity = getNumOfEntities();
//        while(id < numOfEntity){
//            //OBB의 위치 구하기
//            if(isValidMat[id] == 1) continue;
//            val qx = quat.x[id]; val qy = quat.y[id]; val qz = quat.z[id]; val qw = quat.w[id]
//
//            val xx = qx * qx; val yy = qy * qy; val zz = qz * qz;
//            val xy = qx * qy; val xz = qx * qz; val yz = qy * qz;
//            val wx = qw * qx; val wy = qw * qy; val wz = qw * qz;
//
//            mat.m00[id] = 1.0 - 2.0 * (yy + zz)  ; mat.m10[id] = 2.0 * (xy + wz)       ; mat.m20[id] = 2.0 * (xz - wy)
//            mat.m01[id] = 2.0 * (xy - wz)        ; mat.m11[id] = 1.0 - 2.0 * (xx + zz) ; mat.m21[id] = 2.0 * (yz + wx)
//            mat.m02[id] = 2.0 * (xz + wy)        ; mat.m12[id] = 2.0 * (yz - wx)       ; mat.m22[id] = 1.0 - 2.0 * (xx + yy)
//
//            isValidMat[id] = 1;
//            id++;
//        }
//    }


    /**
     * 플레이어 객체 하나에 대해 rayCast 진행
     * */
    val rayGeneration = LongArray(size) { -1 };
    fun rayCast(generation : Long, rayID : Int, hitList : IntArrayList, px : Double, py : Double, pz : Double, dirX : Double, dirY : Double, dirZ: Double){
        hitList.clear();
        val entityNum = getNumOfEntities();

        for(id in 0 until entityNum){
            if (rayTestOne(id, px, py, pz, dirX, dirY, dirZ)) {
                hitList.add(dense[id]); //entityID 반환
            }

            //if(grid.alreadyChecked[id] != rayID || rayGeneration[id] != generation){
            //    grid.alreadyChecked[id] = rayID;
            //    rayGeneration[id] = generation;
            //
            //}
        }
    }

    fun initializeRayCast(){
        java.util.Arrays.fill(grid.alreadyChecked, -1);
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




    fun rayCastGrid(
        generation: Long,
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
        generation: Long,
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



}
