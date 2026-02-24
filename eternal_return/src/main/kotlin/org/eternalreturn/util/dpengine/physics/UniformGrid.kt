package org.eternalreturn.util.dpengine.physics

import it.unimi.dsi.fastutil.ints.IntArrayList
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.times

/**
 *
 * */
class UniformGrid(
    val worldMaxX : Double, val worldMaxY : Double, val worldMaxZ : Double,
    val worldMinX : Double, val worldMinY : Double, val worldMinZ : Double,
    val cellSize : Double, val initialEntitySize : Int) {

    val dimX = ((worldMaxX - worldMinX) / cellSize).toInt();
    val dimY = ((worldMaxY - worldMinY) / cellSize).toInt();
    val dimZ = ((worldMaxZ - worldMinZ) / cellSize).toInt();

    val cellStart = IntArray(dimX * dimY * dimZ) {-1}; //이 cell의 OBB목록이 시작되는 인덱스
    val cellCount = IntArray(dimX * dimY * dimZ) {0}; //이 cell에 Entity가 몇 개 있는지
    var cellIndices = IntArray(initialEntitySize) {-1}; //실제 denseID저장 부분 배열의 집합
    //sizeX * sizeY * iz + sizeZ * iy + ix <-이렇게 접근할 것


    var alreadyChecked = IntArray(initialEntitySize) {-1}; //광선이 이미 지나쳤는지 확인하는 배열


    private fun updateCellCount(
        px : DoubleArray, py : DoubleArray, pz : DoubleArray,
        sx : DoubleArray, sy : DoubleArray, sz : DoubleArray,   // half extents

        m00 : DoubleArray, m01 : DoubleArray, m02 : DoubleArray,
        m10 : DoubleArray, m11 : DoubleArray, m12 : DoubleArray,
        m20 : DoubleArray, m21 : DoubleArray, m22 : DoubleArray,

        arrSize : Int
    ){
        var id = 0;
        while (id < arrSize) {

            // OBB -> AABB
            val hx = abs(m00[id]) * sx[id] + abs(m01[id]) * sy[id] + abs(m02[id]) * sz[id]
            val hy = abs(m10[id]) * sx[id] + abs(m11[id]) * sy[id] + abs(m12[id]) * sz[id]
            val hz = abs(m20[id]) * sx[id] + abs(m21[id]) * sy[id] + abs(m22[id]) * sz[id]

            // AABB 최대, 최소
            val minX = px[id] - hx; val maxX = px[id] + hx
            val minY = py[id] - hy; val maxY = py[id] + hy
            val minZ = pz[id] - hz; val maxZ = pz[id] + hz

            // cell 범위 -> 해당 범위 내의 인덱스에 대해 모두 cellCount 갱신
            var minCx = floor((minX - worldMinX) / cellSize).toInt()
            var maxCx = floor((maxX - worldMinX) / cellSize).toInt()
            var minCy = floor((minY - worldMinY) / cellSize).toInt()
            var maxCy = floor((maxY - worldMinY) / cellSize).toInt()
            var minCz = floor((minZ - worldMinZ) / cellSize).toInt()
            var maxCz = floor((maxZ - worldMinZ) / cellSize).toInt()

            // 0보다 작은 값은 나올 수 없으므로 잘라내기
            if (minCx < 0) minCx = 0
            if (minCy < 0) minCy = 0
            if (minCz < 0) minCz = 0

            // dim 바깥에 위치한 개체여도 Grid 내에서 존재 가능한 가장 최댓값으로
            if (maxCx >= dimX) maxCx = dimX - 1
            if (maxCy >= dimY) maxCy = dimY - 1
            if (maxCz >= dimZ) maxCz = dimZ - 1

            // 범위 내 cell들에 대해 개수 모두 갱신
            var cz = minCz
            while (cz <= maxCz) {
                var cy = minCy
                while (cy <= maxCy) {
                    var cx = minCx
                    var base = cy * dimX + cz * dimX * dimY
                    while (cx <= maxCx) {
                        cellCount[cx + base]++
                        cx++
                    }
                    cy++
                }
                cz++
            }
            id++
        }
    }

    private fun updateCellStart(){
        val totalCellLength = dimX * dimY * dimZ;
        var sum = 0;
        var id : Int = -1;
        while(id < totalCellLength - 1){ id++;
            cellStart[id] = sum;
            sum += cellCount[id];
        }
        val totalInsertCount = cellCount.sum()
        if (cellIndices.size < totalInsertCount) {
            cellIndices = IntArray(totalInsertCount)
        }
    }

    val cellOffset = IntArray(dimX * dimY * dimZ) {0}; //오프셋 계산용
    private fun updateCellIndices(
        px : DoubleArray, py : DoubleArray, pz : DoubleArray,
        sx : DoubleArray, sy : DoubleArray, sz : DoubleArray,   //OBB의 width, height, depth
        m00 : DoubleArray, m01 : DoubleArray, m02 : DoubleArray,
        m10 : DoubleArray, m11 : DoubleArray, m12 : DoubleArray,
        m20 : DoubleArray, m21 : DoubleArray, m22 : DoubleArray,
        arrSize : Int){
        var id : Int = 0;
        while(id < arrSize){
            // OBB -> AABB
            val hx = abs(m00[id]) * sx[id] + abs(m01[id]) * sy[id] + abs(m02[id]) * sz[id]
            val hy = abs(m10[id]) * sx[id] + abs(m11[id]) * sy[id] + abs(m12[id]) * sz[id]
            val hz = abs(m20[id]) * sx[id] + abs(m21[id]) * sy[id] + abs(m22[id]) * sz[id]

            // AABB 최대, 최소
            val minX = px[id] - hx; val maxX = px[id] + hx
            val minY = py[id] - hy; val maxY = py[id] + hy
            val minZ = pz[id] - hz; val maxZ = pz[id] + hz

            // cell 범위 -> 해당 범위 내의 인덱스에 대해 모두 cellCount 갱신
            var minCx = floor((minX - worldMinX) / cellSize).toInt()
            var maxCx = floor((maxX - worldMinX) / cellSize).toInt()
            var minCy = floor((minY - worldMinY) / cellSize).toInt()
            var maxCy = floor((maxY - worldMinY) / cellSize).toInt()
            var minCz = floor((minZ - worldMinZ) / cellSize).toInt()
            var maxCz = floor((maxZ - worldMinZ) / cellSize).toInt()

            // 0보다 작은 값은 나올 수 없으므로 잘라내기
            if (minCx < 0) minCx = 0
            if (minCy < 0) minCy = 0
            if (minCz < 0) minCz = 0

            // dim 바깥에 위치한 개체여도 Grid 내에서 존재 가능한 가장 최댓값으로
            if (maxCx >= dimX) maxCx = dimX - 1
            if (maxCy >= dimY) maxCy = dimY - 1
            if (maxCz >= dimZ) maxCz = dimZ - 1

            // 범위 내 cell들에 대해 개수 모두 갱신
            var cz = minCz
            while (cz <= maxCz) {
                var cy = minCy
                while (cy <= maxCy) {
                    var cx = minCx
                    var base = cy * dimX + cz * dimX * dimY
                    while (cx <= maxCx) {
                        cellIndices[cellStart[cx + base] + cellOffset[cx + base]++] = id;
                        cx++
                    }
                    cy++
                }
                cz++
            }
            id++
        }
    }

    fun updateGrid(
        px : DoubleArray, py : DoubleArray, pz : DoubleArray,
        sx : DoubleArray, sy : DoubleArray, sz : DoubleArray,
        m00 : DoubleArray, m01 : DoubleArray, m02 : DoubleArray,
        m10 : DoubleArray, m11 : DoubleArray, m12 : DoubleArray,
        m20 : DoubleArray, m21 : DoubleArray, m22 : DoubleArray,
        arrSize : Int){
        java.util.Arrays.fill(cellCount, 0)
        java.util.Arrays.fill(cellOffset, 0)
        java.util.Arrays.fill(alreadyChecked, 0)
        updateCellCount(
            px, py, pz,
            sx, sy, sz,
            m00, m01, m02,
            m10, m11, m12,
            m20, m21, m22,
            arrSize);
        updateCellStart(); //cellStart를 업데이트
        updateCellIndices(
            px, py, pz,
            sx, sy, sz,
            m00, m01, m02,
            m10, m11, m12,
            m20, m21, m22,
            arrSize);
        //println("entityCount = $arrSize")
        //println("totalInsert = ${cellCount.sum()}")
        //println("cellIndicesCap = ${cellIndices.size}")
        //println(cellCount.sum())
        //println(arrSize)
    }

    inline fun forEachInCell(cellID : Int, physicsCallBackFunction : (denseID : Int) -> Unit){
        val start = cellStart[cellID];
        val end = start + cellCount[cellID];
        for(i in start until end){
            physicsCallBackFunction(cellIndices[i]);
        }
    }

    inline fun signumInt(x: Double): Int = if (x > 0.0) 1 else if (x < 0.0) -1 else 0

    fun traverseRayCells(
        ox: Double, oy: Double, oz: Double,
        dx: Double, dy: Double, dz: Double,
        tMaxDist: Double,
        visitor: (cellIndex: Int) -> Boolean
    ) {
        // 전체 그리드 AABB
        val gridMinX = worldMinX
        val gridMinY = worldMinY
        val gridMinZ = worldMinZ
        val gridMaxX = worldMinX + dimX * cellSize
        val gridMaxY = worldMinY + dimY * cellSize
        val gridMaxZ = worldMinZ + dimZ * cellSize

        // --- 1) Ray vs AABB (slab) 로 entry/exit 구하기 ---
        fun slab(o: Double, d: Double, mn: Double, mx: Double): Pair<Double, Double>? {
            if (d == 0.0) {
                // 평행: 원점이 slab 밖이면 교차 없음
                return if (o < mn || o > mx) null else Pair(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)
            }
            val inv = 1.0 / d
            var t1 = (mn - o) * inv
            var t2 = (mx - o) * inv
            if (t1 > t2) { val tmp = t1; t1 = t2; t2 = tmp }
            return Pair(t1, t2)
        }

        val sx = slab(ox, dx, gridMinX, gridMaxX) ?: return
        val sy = slab(oy, dy, gridMinY, gridMaxY) ?: return
        val sz = slab(oz, dz, gridMinZ, gridMaxZ) ?: return

        var tEnter = max(sx.first, max(sy.first, sz.first))
        var tExit  = min(sx.second, min(sy.second, sz.second))

        if (tExit < 0.0) return                 // AABB가 레이 뒤쪽
        if (tEnter > tExit) return              // 교차 없음

        // traversal은 [0, tMaxDist]까지만
        val tStart = max(0.0, tEnter)
        val tEnd = min(tMaxDist, tExit)
        if (tStart > tEnd) return

        // --- 2) 시작점을 AABB 진입 지점으로 이동해서 시작 cell 잡기 ---
        val rx = ox + dx * tStart
        val ry = oy + dy * tStart
        val rz = oz + dz * tStart

        // 경계에 딱 걸리는 경우를 위해 아주 작은 epsilon을 "진입 방향"으로 넣어주는 게 안정적
        // (정확히 경계면 위면 floor가 반대 셀로 갈 수 있음)
        val eps = 1e-12
        val adjX = rx + dx * eps
        val adjY = ry + dy * eps
        val adjZ = rz + dz * eps

        var ix = floor((adjX - worldMinX) / cellSize).toInt()
        var iy = floor((adjY - worldMinY) / cellSize).toInt()
        var iz = floor((adjZ - worldMinZ) / cellSize).toInt()

        // entry 지점은 AABB 내부/경계니까 clamp는 안전장치 정도로만
        if (ix < 0) ix = 0 else if (ix >= dimX) ix = dimX - 1
        if (iy < 0) iy = 0 else if (iy >= dimY) iy = dimY - 1
        if (iz < 0) iz = 0 else if (iz >= dimZ) iz = dimZ - 1

        val stepX = signumInt(dx)
        val stepY = signumInt(dy)
        val stepZ = signumInt(dz)

        // --- 3) tMax/tDelta 계산은 "tStart 기준"으로 해야 함 (중요) ---
        fun nextBoundary(minW: Double, i: Int, step: Int): Double {
            // step>0: 다음 경계 = (i+1)
            // step<0: 다음 경계 = i
            return if (step > 0) minW + (i + 1) * cellSize else minW + i * cellSize
        }

        val invDx = if (dx != 0.0) 1.0 / dx else Double.POSITIVE_INFINITY
        val invDy = if (dy != 0.0) 1.0 / dy else Double.POSITIVE_INFINITY
        val invDz = if (dz != 0.0) 1.0 / dz else Double.POSITIVE_INFINITY

        // 현재 위치는 (rx,ry,rz) = tStart에서의 점이므로, tMax도 그 기준으로 “추가로” 얼마나 가면 경계냐를 계산
        var tMaxX = if (stepX != 0) (nextBoundary(worldMinX, ix, stepX) - rx) * invDx else Double.POSITIVE_INFINITY
        var tMaxY = if (stepY != 0) (nextBoundary(worldMinY, iy, stepY) - ry) * invDy else Double.POSITIVE_INFINITY
        var tMaxZ = if (stepZ != 0) (nextBoundary(worldMinZ, iz, stepZ) - rz) * invDz else Double.POSITIVE_INFINITY

        // 음수로 나오는 경우(부동소수 오차/경계 위) 방지
        if (tMaxX < 0.0) tMaxX = 0.0
        if (tMaxY < 0.0) tMaxY = 0.0
        if (tMaxZ < 0.0) tMaxZ = 0.0

        val tDeltaX = if (stepX != 0) cellSize * abs(invDx) else Double.POSITIVE_INFINITY
        val tDeltaY = if (stepY != 0) cellSize * abs(invDy) else Double.POSITIVE_INFINITY
        val tDeltaZ = if (stepZ != 0) cellSize * abs(invDz) else Double.POSITIVE_INFINITY

        var t = tStart

        // --- 4) tie 처리: 동시에 만나는 축은 모두 step 해야 셀 스킵이 없어짐 ---
        while (t <= tEnd) {
            val cellIndex = ix + iy * dimX + iz * dimX * dimY
            if (visitor(cellIndex)) {
                return
            }

            val tNext = min(tMaxX, min(tMaxY, tMaxZ))
            t = tStart + tNext

            // tNext와 같은 축을 전부 진행 (엣지/코너 통과 케이스 해결)
            if (tNext == tMaxX) { ix += stepX; tMaxX += tDeltaX }
            if (tNext == tMaxY) { iy += stepY; tMaxY += tDeltaY }
            if (tNext == tMaxZ) { iz += stepZ; tMaxZ += tDeltaZ }

            if (ix !in 0 until dimX || iy !in 0 until dimY || iz !in 0 until dimZ) return
        }
    }


    class CurRayCellInfo(){
        var cellID : Int = -1; var ix : Int = -1; var iy : Int = -1; var iz : Int = -1;
        var tMaxX : Double = 0.0; var tMaxY : Double = 0.0; var tMaxZ : Double = 0.0;
        var stepX : Int = 0; var stepY : Int = 0; var stepZ : Int = 0;
        var tDeltaX : Double = 0.0; var tDeltaY : Double = 0.0; var tDeltaZ : Double = 0.0;
        var tStart : Double = 0.0; var tEnd : Double = 0.0;
        var t = 0.0;
    }

    /**
     * 광선 시작 셀을 정하는 함수
     * 시작 위치의 셀 인덱스를 반환한다.
     * */
    val curCellInfo = CurRayCellInfo(); //해당 객체의 변수들을 반복 참조하여 다음 셀 위치를 예측
    fun initializeRay(
        ox: Double, oy: Double, oz: Double,
        dx: Double, dy: Double, dz: Double,
        tMaxDist: Double
    ) : Int{
        // 전체 그리드 AABB
        val gridMinX = worldMinX
        val gridMinY = worldMinY
        val gridMinZ = worldMinZ
        val gridMaxX = worldMinX + dimX * cellSize
        val gridMaxY = worldMinY + dimY * cellSize
        val gridMaxZ = worldMinZ + dimZ * cellSize

        // --- 1) Ray vs AABB (slab) 로 entry/exit 구하기 ---
        fun slab(o: Double, d: Double, mn: Double, mx: Double): Pair<Double, Double>? {
            if (d == 0.0) {
                // 평행: 원점이 slab 밖이면 교차 없음
                return if (o < mn || o > mx) null else Pair(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)
            }
            val inv = 1.0 / d
            var t1 = (mn - o) * inv
            var t2 = (mx - o) * inv
            if (t1 > t2) { val tmp = t1; t1 = t2; t2 = tmp }
            return Pair(t1, t2)
        }

        val sx = slab(ox, dx, gridMinX, gridMaxX) ?: return -1;
        val sy = slab(oy, dy, gridMinY, gridMaxY) ?: return -1;
        val sz = slab(oz, dz, gridMinZ, gridMaxZ) ?: return -1;

        var tEnter = max(sx.first, max(sy.first, sz.first))
        var tExit  = min(sx.second, min(sy.second, sz.second))

        if (tExit < 0.0) return -1               // AABB가 레이 뒤쪽
        if (tEnter > tExit) return -1             // 교차 없음

        // traversal은 [0, tMaxDist]까지만
        val tStart = max(0.0, tEnter)
        val tEnd = min(tMaxDist, tExit)
        if (tStart > tEnd) return -1

        // --- 2) 시작점을 AABB 진입 지점으로 이동해서 시작 cell 잡기 ---
        val rx = ox + dx * tStart
        val ry = oy + dy * tStart
        val rz = oz + dz * tStart

        // 경계에 딱 걸리는 경우를 위해 아주 작은 epsilon을 "진입 방향"으로 넣어주는 게 안정적
        // (정확히 경계면 위면 floor가 반대 셀로 갈 수 있음)
        val eps = 1e-12
        val adjX = rx + dx * eps
        val adjY = ry + dy * eps
        val adjZ = rz + dz * eps

        var ix = floor((adjX - worldMinX) / cellSize).toInt()
        var iy = floor((adjY - worldMinY) / cellSize).toInt()
        var iz = floor((adjZ - worldMinZ) / cellSize).toInt()

        // entry 지점은 AABB 내부/경계니까 clamp는 안전장치 정도로만
        if (ix < 0) ix = 0 else if (ix >= dimX) ix = dimX - 1
        if (iy < 0) iy = 0 else if (iy >= dimY) iy = dimY - 1
        if (iz < 0) iz = 0 else if (iz >= dimZ) iz = dimZ - 1

        val stepX = signumInt(dx)
        val stepY = signumInt(dy)
        val stepZ = signumInt(dz)

        // --- 3) tMax/tDelta 계산은 "tStart 기준"으로 해야 함 (중요) ---
        fun nextBoundary(minW: Double, i: Int, step: Int): Double {
            // step>0: 다음 경계 = (i+1)
            // step<0: 다음 경계 = i
            return if (step > 0) minW + (i + 1) * cellSize else minW + i * cellSize
        }

        val invDx = if (dx != 0.0) 1.0 / dx else Double.POSITIVE_INFINITY
        val invDy = if (dy != 0.0) 1.0 / dy else Double.POSITIVE_INFINITY
        val invDz = if (dz != 0.0) 1.0 / dz else Double.POSITIVE_INFINITY

        // 현재 위치는 (rx,ry,rz) = tStart에서의 점이므로, tMax도 그 기준으로 “추가로” 얼마나 가면 경계냐를 계산
        var tMaxX = if (stepX != 0) (nextBoundary(worldMinX, ix, stepX) - rx) * invDx else Double.POSITIVE_INFINITY
        var tMaxY = if (stepY != 0) (nextBoundary(worldMinY, iy, stepY) - ry) * invDy else Double.POSITIVE_INFINITY
        var tMaxZ = if (stepZ != 0) (nextBoundary(worldMinZ, iz, stepZ) - rz) * invDz else Double.POSITIVE_INFINITY

        // 음수로 나오는 경우(부동소수 오차/경계 위) 방지
        if (tMaxX < 0.0) tMaxX = 0.0
        if (tMaxY < 0.0) tMaxY = 0.0
        if (tMaxZ < 0.0) tMaxZ = 0.0

        val tDeltaX = if (stepX != 0) cellSize * abs(invDx) else Double.POSITIVE_INFINITY
        val tDeltaY = if (stepY != 0) cellSize * abs(invDy) else Double.POSITIVE_INFINITY
        val tDeltaZ = if (stepZ != 0) cellSize * abs(invDz) else Double.POSITIVE_INFINITY
        
        //시작 셀 정보 저장
        curCellInfo.tStart = tStart; curCellInfo.tEnd = tEnd;
        curCellInfo.ix = ix; curCellInfo.iy = iy; curCellInfo.iz = iz;
        curCellInfo.tMaxX = tMaxX; curCellInfo.tMaxY = tMaxY; curCellInfo.tMaxZ = tMaxZ;
        curCellInfo.stepX = stepX; curCellInfo.stepY = stepY; curCellInfo.stepZ = stepZ;
        curCellInfo.tDeltaX = tDeltaX; curCellInfo.tDeltaY = tDeltaY; curCellInfo.tDeltaZ = tDeltaZ;
        curCellInfo.t = curCellInfo.tStart;

        return ix + iy * dimX + iz * dimX * dimY;
    }

    fun nextCell() : Int{
        // tie 처리: 동시에 만나는 축은 모두 step 해야 셀 스킵이 없어짐
        val tNext = min(curCellInfo.tMaxX, min(curCellInfo.tMaxY, curCellInfo.tMaxZ))

        if (tNext > curCellInfo.tEnd) { return -1; }

        curCellInfo.t = tNext
        //curCellInfo.t = curCellInfo.tStart + tNext

        // tNext와 같은 축을 전부 진행 (엣지/코너 통과 케이스 해결)
        if (tNext == curCellInfo.tMaxX) { curCellInfo.ix += curCellInfo.stepX; curCellInfo.tMaxX += curCellInfo.tDeltaX }
        if (tNext == curCellInfo.tMaxY) { curCellInfo.iy += curCellInfo.stepY; curCellInfo.tMaxY += curCellInfo.tDeltaY }
        if (tNext == curCellInfo.tMaxZ) { curCellInfo.iz += curCellInfo.stepZ; curCellInfo.tMaxZ += curCellInfo.tDeltaZ }

        if (curCellInfo.ix !in 0 until dimX || curCellInfo.iy !in 0 until dimY || curCellInfo.iz !in 0 until dimZ) return -1;
        return curCellInfo.ix + curCellInfo.iy * dimX + curCellInfo.iz * dimX * dimY;
    }


}


