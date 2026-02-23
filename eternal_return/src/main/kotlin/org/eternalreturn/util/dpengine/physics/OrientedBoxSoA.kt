package org.eternalreturn.util.dpengine.physics

import it.unimi.dsi.fastutil.ints.IntArrayList

class OrientedBoxSoA(
    size : Int,
    private val transformSoA : TransformSoA,
    private val grid : UniformGrid
) : SoAModule(size) {

    private val size = Vec3SoA(size); // width, height, depth 저장

    private val posCache = Vec3SoA(size); //transform.pos 캐싱
    private val isValidPos = IntArray(size){ 0 };//해당 회전행렬이 valid한 회전행렬인지 확인

    private val quat = Vec4SoA(size); //각도에 대한 쿼터니언 저장
    private val matCache = Mat3x3SoA(size); //해당 쿼터니언에 대한 회전행렬 저장
    private val isValidMat = IntArray(size){ 0 };//해당 회전행렬이 valid한 회전행렬인지 확인

    private val transformHandleList = ArrayList<Handle>(size);


    fun create(transformHandle : Handle, width : Double, height : Double, depth : Double, qx : Double, qy : Double, qz : Double, qw : Double) : Handle{
        val triple = super.createHandle(); // (entityID, denseID, generation)
        val entityID = triple.first;
        val denseID = triple.second;
        val generation = triple.third;
        size.allocSoA(denseID, width, height, depth);
        quat.allocSoA(denseID, qx, qy, qz, qw);
        matCache.allocSoA(denseID, 0.0, 0.0, 0.0 ,0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        posCache.allocSoA(denseID, 0.0,0.0,0.0);
        transformHandleList.addLast(transformHandle);
        return Handle(entityID, generation)
    }

    fun remove(handle : Handle){
        val pair = super.removeHandle(handle);
        size.overwrite(pair.first, pair.second);
        quat.overwrite(pair.first, pair.second);
        matCache.overwrite(pair.first, pair.second);

        isValidPos[pair.first] = isValidPos[pair.second];
        isValidMat[pair.first] = isValidMat[pair.second];

        transformHandleList[pair.first] = transformHandleList[pair.second];
        transformHandleList.removeLast();
    }

    fun updatePosCache(){
        var id = 0;
        val tsparse = transformSoA.sparse;
        val position = transformSoA.position;
        val numOfEntity = getNumOfEntities();
        while(id < numOfEntity){
            //OBB의 위치 구하기
            if(isValidPos[id] == 1) continue;
            val tID = tsparse[transformHandleList[id].entityID];
            posCache.x[id] = position.x[tID]
            posCache.y[id] = position.y[tID]
            posCache.z[id] = position.z[tID]
            isValidPos[id] = 1;
            id++;
        }
    }

    /**
     * 
     * */
    fun updateRotCache(){
        var id = 0;
        val mat = matCache;
        val numOfEntity = getNumOfEntities();
        while(id < numOfEntity){
            //OBB의 위치 구하기
            if(isValidMat[id] == 1) continue;
            val qx = quat.x[id]; val qy = quat.y[id]; val qz = quat.z[id]; val qw = quat.w[id]

            val xx = qx * qx; val yy = qy * qy; val zz = qz * qz;
            val xy = qx * qy; val xz = qx * qz; val yz = qy * qz;
            val wx = qw * qx; val wy = qw * qy; val wz = qw * qz;

            mat.m00[id] = 1.0 - 2.0 * (yy + zz)  ; mat.m10[id] = 2.0 * (xy + wz)       ; mat.m20[id] = 2.0 * (xz - wy)
            mat.m01[id] = 2.0 * (xy - wz)        ; mat.m11[id] = 1.0 - 2.0 * (xx + zz) ; mat.m21[id] = 2.0 * (yz + wx)
            mat.m02[id] = 2.0 * (xz + wy)        ; mat.m12[id] = 2.0 * (yz - wx)       ; mat.m22[id] = 1.0 - 2.0 * (xx + yy)

            isValidMat[id] = 1;
            id++;
        }
    }


    /**
     * 플레이어 객체 하나에 대해 rayCast 진행
     * */
    fun rayCast(hitList : IntArrayList, px : Double, py : Double, pz : Double, dirX : Double, dirY : Double, dirZ: Double){

        hitList.clear();
        val entityNum = getNumOfEntities();
        var id = 0;
        while(id < entityNum){
            if (rayTestOne(id, px, py, pz, dirX, dirY, dirZ)) {
                hitList.add(id);
            }
            id++;
        }
    }


    fun rebuildGrid() {
        // posCache는 Vec3SoA니까 내부 DoubleArray를 넘긴다고 가정
        val m = matCache;
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

        val mat = matCache;

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
        hitList: IntArrayList,
        px: Double, py: Double, pz: Double,
        dirX: Double, dirY: Double, dirZ: Double,
        maxDist: Double = 1000.0
    ) {
        //var visited = 0
        grid.traverseRayCells(px, py, pz, dirX, dirY, dirZ, maxDist) { cell ->

            val start = grid.cellStart[cell]
            val count = grid.cellCount[cell]
            val end = start + count

            var i = start - 1
            while (i < end - 1) { i++
                val id = grid.cellIndices[i]
                if (rayTestOne(id, px, py, pz, dirX, dirY, dirZ)) {
                    hitList.add(dense[id]) // sparseID나 entityID로 바꾸고 싶으면 여기서
                }
            }
            //visited++
            false // true면 조기 종료. (ex: “첫 히트만 원함”이면 true로 바꾸면 됨)
        }
        //println("visited cells = $visited")
    }






}
