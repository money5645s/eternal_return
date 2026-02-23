package org.eternalreturn.util.dpengine.physics

class OrientedBoxSoA__() {

    private val px = ArrayList<Double>();
    private val py = ArrayList<Double>();
    private val pz = ArrayList<Double>();
    private val halfX = ArrayList<Double>();
    private val halfY = ArrayList<Double>();
    private val halfZ = ArrayList<Double>();
    private val rx = ArrayList<Double>();
    private val ry = ArrayList<Double>();
    private val rz = ArrayList<Double>();
    private val rw = ArrayList<Double>();

    fun buildRotationMatrix(i: Int, out: DoubleArray /* size 9 */) {
        val x = rx[i]
        val y = ry[i]
        val z = rz[i]
        val w = rw[i]

        val xx = x * x
        val yy = y * y
        val zz = z * z
        val xy = x * y
        val xz = x * z
        val yz = y * z
        val wx = w * x
        val wy = w * y
        val wz = w * z

        out[0] = 1.0 - 2.0 * (yy + zz)
        out[1] = 2.0 * (xy - wz)
        out[2] = 2.0 * (xz + wy)

        out[3] = 2.0 * (xy + wz)
        out[4] = 1.0 - 2.0 * (xx + zz)
        out[5] = 2.0 * (yz - wx)

        out[6] = 2.0 * (xz - wy)
        out[7] = 2.0 * (yz + wx)
        out[8] = 1.0 - 2.0 * (xx + yy)
    }

    fun center(i: Int, out: DoubleArray) {
        out[0] = px[i]
        out[1] = py[i]
        out[2] = pz[i]
    }

    fun extents(i: Int, out: DoubleArray) {
        out[0] = halfX[i]
        out[1] = halfY[i]
        out[2] = halfZ[i]
    }

    fun rayToLocal(
        i: Int,
        ox: Double, oy: Double, oz: Double,
        dx: Double, dy: Double, dz: Double,
        outOrigin: DoubleArray,
        outDir: DoubleArray
    ) {

        val cx = px[i]
        val cy = py[i]
        val cz = pz[i]

        val px = ox - cx
        val py = oy - cy
        val pz = oz - cz

        val R = DoubleArray(9)
        buildRotationMatrix(i, R)

        // transpose multiply
        outOrigin[0] = R[0]*px + R[3]*py + R[6]*pz
        outOrigin[1] = R[1]*px + R[4]*py + R[7]*pz
        outOrigin[2] = R[2]*px + R[5]*py + R[8]*pz

        outDir[0] = R[0]*dx + R[3]*dy + R[6]*dz
        outDir[1] = R[1]*dx + R[4]*dy + R[7]*dz
        outDir[2] = R[2]*dx + R[5]*dy + R[8]*dz
    }

    fun rayAABB(
        ox: Double, oy: Double, oz: Double,
        dx: Double, dy: Double, dz: Double,
        ex: Double, ey: Double, ez: Double
    ): Double {

        var tmin = Double.NEGATIVE_INFINITY
        var tmax = Double.POSITIVE_INFINITY

        fun slab(origin: Double, dir: Double, min: Double, max: Double) {
            val t1 = (min - origin) / dir
            val t2 = (max - origin) / dir

            val lo = minOf(t1, t2)
            val hi = maxOf(t1, t2)

            tmin = maxOf(tmin, lo)
            tmax = minOf(tmax, hi)
        }

        slab(ox, dx, -ex, ex)
        slab(oy, dy, -ey, ey)
        slab(oz, dz, -ez, ez)

        if (tmax >= maxOf(tmin, 0.0)) return tmin
        return Double.POSITIVE_INFINITY
    }

    fun raycastOBB(
        i: Int,
        ox: Double, oy: Double, oz: Double,
        dx: Double, dy: Double, dz: Double
    ): Double {

        val localO = DoubleArray(3)
        val localD = DoubleArray(3)

        rayToLocal(i, ox, oy, oz, dx, dy, dz, localO, localD)

        return rayAABB(
            localO[0], localO[1], localO[2],
            localD[0], localD[1], localD[2],
            halfX[i], halfY[i], halfZ[i]
        )
    }

    private val EPS = 1e-12

    data class RayHit(
        var hit: Boolean = false,
        var t: Double = Double.POSITIVE_INFINITY,
        var nx: Double = 0.0,
        var ny: Double = 0.0,
        var nz: Double = 0.0
    )

    private fun rayAABBWithNormalLocal(
        ox: Double, oy: Double, oz: Double,
        dx: Double, dy: Double, dz: Double,
        ex: Double, ey: Double, ez: Double,
        tMaxLimit: Double,           // 사거리/프레임 이동거리 제한
        out: RayHit
    ) {
        var tmin = Double.NEGATIVE_INFINITY
        var tmax = Double.POSITIVE_INFINITY

        // tmin을 만든 축(면) 기록: 0=x,1=y,2=z
        var hitAxis = -1
        var hitSign = 0.0

        fun slab(origin: Double, dir: Double, min: Double, max: Double, axis: Int) {
            if (kotlin.math.abs(dir) < EPS) {
                // 레이가 이 축 방향으로 거의 평행: origin이 slab 밖이면 miss
                if (origin < min || origin > max) {
                    tmin = Double.POSITIVE_INFINITY
                    tmax = Double.NEGATIVE_INFINITY
                }
                return
            }

            val inv = 1.0 / dir
            var t1 = (min - origin) * inv
            var t2 = (max - origin) * inv

            // t1이 near, t2가 far가 되도록
            var near = t1
            var far = t2
            var sign = -1.0 // near가 min면이면 normal은 -axis 방향(로컬 기준)
            if (t1 > t2) {
                near = t2
                far = t1
                sign = +1.0 // near가 max면이면 normal은 +axis 방향
            }

            if (near > tmin) {
                tmin = near
                hitAxis = axis
                hitSign = sign
            }
            if (far < tmax) tmax = far
        }

        slab(ox, dx, -ex, ex, 0)
        slab(oy, dy, -ey, ey, 1)
        slab(oz, dz, -ez, ez, 2)

        // 교차 판정
        if (tmax < 0.0 || tmax < tmin || tmin > tMaxLimit) {
            out.hit = false
            out.t = Double.POSITIVE_INFINITY
            out.nx = 0.0; out.ny = 0.0; out.nz = 0.0
            return
        }

        // 레이가 박스 안에서 시작하면 tmin < 0 이 될 수 있음.
        // "나가는 면"을 쓰고 싶으면 t = 0 또는 tmax 선택.
        val tHit = if (tmin >= 0.0) tmin else 0.0

        out.hit = true
        out.t = tHit

        // 로컬 normal
        out.nx = 0.0; out.ny = 0.0; out.nz = 0.0
        when (hitAxis) {
            0 -> out.nx = hitSign
            1 -> out.ny = hitSign
            2 -> out.nz = hitSign
            else -> { /* inside-start 같은 특수 케이스면 normal 필요없을 수도 */ }
        }
    }

    private fun rayToLocalNoAlloc(
        i: Int,
        ox: Double, oy: Double, oz: Double,
        dx: Double, dy: Double, dz: Double,
        R: DoubleArray,          // size 9, 재사용
        outO: DoubleArray,       // size 3, 재사용
        outD: DoubleArray        // size 3, 재사용
    ) {
        buildRotationMatrix(i, R)

        val cx = px[i]; val cy = py[i]; val cz = pz[i]
        val px0 = ox - cx
        val py0 = oy - cy
        val pz0 = oz - cz

        // outO = R^T * (O - C)
        outO[0] = R[0]*px0 + R[3]*py0 + R[6]*pz0
        outO[1] = R[1]*px0 + R[4]*py0 + R[7]*pz0
        outO[2] = R[2]*px0 + R[5]*py0 + R[8]*pz0

        // outD = R^T * D
        outD[0] = R[0]*dx + R[3]*dy + R[6]*dz
        outD[1] = R[1]*dx + R[4]*dy + R[7]*dz
        outD[2] = R[2]*dx + R[5]*dy + R[8]*dz
    }

    private fun localNormalToWorld(
        R: DoubleArray,
        lx: Double, ly: Double, lz: Double,
        out: DoubleArray
    ) {
        // out = R * localNormal
        out[0] = R[0]*lx + R[1]*ly + R[2]*lz
        out[1] = R[3]*lx + R[4]*ly + R[5]*lz
        out[2] = R[6]*lx + R[7]*ly + R[8]*lz
    }

    data class RaycastResult(
        var hit: Boolean = false,
        var id: Int = -1,
        var t: Double = Double.POSITIVE_INFINITY,
        var hx: Double = 0.0,
        var hy: Double = 0.0,
        var hz: Double = 0.0,
        var nx: Double = 0.0,
        var ny: Double = 0.0,
        var nz: Double = 0.0
    )

    fun raycastOBBFull(
        i: Int,
        ox: Double, oy: Double, oz: Double,
        dx: Double, dy: Double, dz: Double,
        tMaxLimit: Double,              // 시야=무한대 대신 거리 제한, 이동=이번 프레임 이동거리
        tmpR: DoubleArray,              // size 9 재사용
        tmpO: DoubleArray,              // size 3 재사용
        tmpD: DoubleArray,              // size 3 재사용
        tmpHit: RayHit,                 // 재사용
        out: RaycastResult
    ) {
        rayToLocalNoAlloc(i, ox, oy, oz, dx, dy, dz, tmpR, tmpO, tmpD)

        rayAABBWithNormalLocal(
            tmpO[0], tmpO[1], tmpO[2],
            tmpD[0], tmpD[1], tmpD[2],
            halfX[i], halfY[i], halfZ[i],
            tMaxLimit,
            tmpHit
        )

        if (!tmpHit.hit) {
            out.hit = false
            out.id = -1
            out.t = Double.POSITIVE_INFINITY
            return
        }

        out.hit = true
        out.id = i
        out.t = tmpHit.t

        // hit point in world: H = O + D * t
        out.hx = ox + dx * out.t
        out.hy = oy + dy * out.t
        out.hz = oz + dz * out.t

        // normal: local -> world (회전만 적용)
        val nw = DoubleArray(3) // 여기도 재사용하면 더 좋음
        localNormalToWorld(tmpR, tmpHit.nx, tmpHit.ny, tmpHit.nz, nw)

        // normalize (수치 오차 대비)
        val len = kotlin.math.sqrt(nw[0]*nw[0] + nw[1]*nw[1] + nw[2]*nw[2])
        if (len > EPS) {
            out.nx = nw[0] / len
            out.ny = nw[1] / len
            out.nz = nw[2] / len
        } else {
            out.nx = 0.0; out.ny = 0.0; out.nz = 0.0
        }
    }

    fun raycastAll(
        ox: Double, oy: Double, oz: Double,
        dx: Double, dy: Double, dz: Double,
        tMaxLimit: Double,
        out: RaycastResult
    ) {
        val tmpR = DoubleArray(9)
        val tmpO = DoubleArray(3)
        val tmpD = DoubleArray(3)
        val tmpN = DoubleArray(3)
        val tmpHit = RayHit()

        out.hit = false
        out.t = Double.POSITIVE_INFINITY
        out.id = -1

        val tmpRes = RaycastResult()
        for (i in 0 until px.size) {
            // (필요하면) 팀/레이어/자기 자신 제외 필터를 여기서
            raycastOBBFull(i, ox, oy, oz, dx, dy, dz, tMaxLimit, tmpR, tmpO, tmpD, tmpHit, tmpRes)

            if (tmpRes.hit && tmpRes.t < out.t) {
                out.hit = true
                out.id = tmpRes.id
                out.t = tmpRes.t
                out.hx = tmpRes.hx; out.hy = tmpRes.hy; out.hz = tmpRes.hz
                out.nx = tmpRes.nx; out.ny = tmpRes.ny; out.nz = tmpRes.nz
            }
        }
    }

}