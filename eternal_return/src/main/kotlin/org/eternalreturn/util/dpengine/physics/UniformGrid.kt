package org.eternalreturn.util.dpengine.physics

import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

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


    /**
     * 레이가 지나가는 cell들을 순서대로 방문한다.
     * tMaxDist: 레이 최대 길이 (ex. 200.0)
     *
     * visitor(cellIndex) 가 true를 리턴하면 traversal 종료 (hit 찾았을 때 early-exit용)
     *
     * coded by gpt
     *
     */
    fun traverseRayCells(
        ox: Double, oy: Double, oz: Double,
        dx: Double, dy: Double, dz: Double,
        tMaxDist: Double,
        visitor: (cellIndex: Int) -> Boolean
    ) {

        val gridMinX = worldMinX
        val gridMinY = worldMinY
        val gridMinZ = worldMinZ
        val gridMaxX = worldMinX + dimX * cellSize
        val gridMaxY = worldMinY + dimY * cellSize
        val gridMaxZ = worldMinZ + dimZ * cellSize

        // --- Ray vs Grid AABB (slab) ---
        fun slab(o: Double, d: Double, mn: Double, mx: Double): Pair<Double, Double>? {
            if (d == 0.0) {
                if (o < mn || o > mx) return null
                return Double.NEGATIVE_INFINITY to Double.POSITIVE_INFINITY
            }
            val inv = 1.0 / d
            var t1 = (mn - o) * inv
            var t2 = (mx - o) * inv
            if (t1 > t2) { val tmp = t1; t1 = t2; t2 = tmp }
            return t1 to t2
        }

        val sx = slab(ox, dx, gridMinX, gridMaxX) ?: return
        val sy = slab(oy, dy, gridMinY, gridMaxY) ?: return
        val sz = slab(oz, dz, gridMinZ, gridMaxZ) ?: return

        var tEnter = maxOf(sx.first, sy.first, sz.first)
        var tExit  = minOf(sx.second, sy.second, sz.second)

        if (tExit < 0.0) return
        if (tEnter > tExit) return

        val tStart = maxOf(0.0, tEnter)
        val tEnd = minOf(tMaxDist, tExit)

        // --- entry point ---
        val eps = 1e-9
        val rx = ox + dx * (tStart + eps)
        val ry = oy + dy * (tStart + eps)
        val rz = oz + dz * (tStart + eps)

        var ix = floor((rx - worldMinX) / cellSize).toInt()
        var iy = floor((ry - worldMinY) / cellSize).toInt()
        var iz = floor((rz - worldMinZ) / cellSize).toInt()

        fun sign(x: Double) = if (x > 0) 1 else if (x < 0) -1 else 0

        val stepX = sign(dx)
        val stepY = sign(dy)
        val stepZ = sign(dz)

        val invDx = if (dx != 0.0) 1.0 / dx else Double.POSITIVE_INFINITY
        val invDy = if (dy != 0.0) 1.0 / dy else Double.POSITIVE_INFINITY
        val invDz = if (dz != 0.0) 1.0 / dz else Double.POSITIVE_INFINITY

        fun nextBoundary(min: Double, i: Int, step: Int): Double {
            return if (step > 0) min + (i + 1) * cellSize else min + i * cellSize
        }

        var tMaxX = if (stepX != 0) (nextBoundary(worldMinX, ix, stepX) - rx) * invDx else Double.POSITIVE_INFINITY
        var tMaxY = if (stepY != 0) (nextBoundary(worldMinY, iy, stepY) - ry) * invDy else Double.POSITIVE_INFINITY
        var tMaxZ = if (stepZ != 0) (nextBoundary(worldMinZ, iz, stepZ) - rz) * invDz else Double.POSITIVE_INFINITY

        val tDeltaX = if (stepX != 0) cellSize * abs(invDx) else Double.POSITIVE_INFINITY
        val tDeltaY = if (stepY != 0) cellSize * abs(invDy) else Double.POSITIVE_INFINITY
        val tDeltaZ = if (stepZ != 0) cellSize * abs(invDz) else Double.POSITIVE_INFINITY

        var t = tStart

        while (t <= tEnd) {
            val cellIndex = ix + iy * dimX + iz * dimX * dimY
            if (visitor(cellIndex)) return

            val tNext = minOf(tMaxX, tMaxY, tMaxZ)
            t = tStart + tNext

            if (tNext == tMaxX) { ix += stepX; tMaxX += tDeltaX }
            if (tNext == tMaxY) { iy += stepY; tMaxY += tDeltaY }
            if (tNext == tMaxZ) { iz += stepZ; tMaxZ += tDeltaZ }

            if (ix !in 0 until dimX || iy !in 0 until dimY || iz !in 0 until dimZ) return
        }
    }



}
