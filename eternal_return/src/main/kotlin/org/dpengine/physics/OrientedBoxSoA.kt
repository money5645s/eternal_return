package org.dpengine.physics

import RayHitInfo
import it.unimi.dsi.fastutil.doubles.DoubleArrayList
import it.unimi.dsi.fastutil.ints.IntArrayList
import it.unimi.dsi.fastutil.longs.LongArrayList
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.Particle
import org.dpengine.DPhysicsEngine
import org.dpengine.monobehaviour.MonobehaviourActor
import org.eternalreturn.erentity.DPEntity
import org.eternalreturn.erentity.DPHitboxEntity
import org.eternalreturn.erentity.events.EREntityRayCastEvent
import org.eternalreturn.projectile.DProjectile
import org.eternalreturn.projectile.events.ProjectileRayCastEvent
import org.eternalreturn.system.PluginInstance
import java.util.Arrays
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin
import kotlin.math.sqrt


class OrientedBoxSoA(
    size : Int,
    private val transformSoA : TransformSoA,
    val grid : UniformGrid
) : SoAModule(size) {
    private val size = Vec3SoA(size); // width, height, depth 저장
    private val locPos = Vec3SoA(size);

    private val velocityCache = Vec3SoA(size); //transform.velocity 캐싱

    private val posCache = Vec3SoA(size); //transform.pos 캐싱
    private val isValidPos = IntArray(size){ 0 };//해당 포지션이 valid한 포지션인지 확인

    private val rotMatCache = Mat3x3SoA(size); //회전행렬 저장
    private val isValidRotMat = IntArray(size){ 0 };//해당 회전행렬이 valid한 회전행렬인지 확인

    private val transformHandleList = LongArrayList(size);
    private val actors = Array<MonobehaviourActor?>(size){null}


    fun create(actor: MonobehaviourActor, transformHandle : Long, width : Double, height : Double, depth : Double, locX : Double, locY : Double, locZ : Double) : Long{
        val (entityID, denseID, generation) = super.createHandle(); // (entityID, denseID, generation)

        actors[denseID] = actor;

        size.allocSoA(denseID, width, height, depth);
        locPos.allocSoA(denseID, locX, locY, locZ);
        rotMatCache.allocSoA(denseID, 0.0, 0.0, 0.0 ,0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        posCache.allocSoA(denseID, 0.0,0.0,0.0);
        velocityCache.allocSoA(denseID, 0.0, 0.0, 0.0)
        transformHandleList.addLast(transformHandle);
        return Handle.createHandle(entityID, generation)
    }

    fun remove(handle : Handle){
        val (idx0, idx1) = super.removeHandle(handle);
        size.overwrite(idx0, idx1);
        locPos.overwrite(idx0, idx1);

        rotMatCache.overwrite(idx0, idx1);
        posCache.overwrite(idx0, idx1);
        velocityCache.overwrite(idx0, idx1);

        isValidPos[idx0] = 0;
        isValidRotMat[idx0] = 0;
        actors[idx0] = actors[idx1];

        transformHandleList[idx0] = transformHandleList.getLong(idx1);
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
        updateVelocityCache();
    }

    private fun updatePosCache(){
        val tsparse = transformSoA.sparse;
        val position = transformSoA.position;
        val numOfEntity = getNumOfEntities();
        for(id in 0 until numOfEntity){
            val tID = tsparse[Handle(transformHandleList.getLong(id)).entityID];
            posCache.x[id] = position.x[tID] + locPos.x[tID];
            posCache.y[id] = position.y[tID] + locPos.y[tID];
            posCache.z[id] = position.z[tID] + locPos.z[tID];
        }
    }

    private fun updateVelocityCache(){
        val tsparse = transformSoA.sparse;
        val velocity = transformSoA.velocity;
        val numOfEntity = getNumOfEntities();
        for(id in 0 until numOfEntity){
            val tID = tsparse[Handle(transformHandleList.getLong(id)).entityID];
            velocityCache.x[id] = velocity.x[tID]
            velocityCache.y[id] = velocity.y[tID]
            velocityCache.z[id] = velocity.z[tID]
        }
    }

    private fun updateRotCache(){
        val tsparse = transformSoA.sparse;
        val rotation = transformSoA.rotation;
        val numOfEntity = getNumOfEntities();

        for(id in 0 until numOfEntity){
            val tID = tsparse[Handle(transformHandleList.getLong(id)).entityID];

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
     * 인덱스 : ```sparse[idx]```로 구할 수 있는 값
     * */
    val hitRayGeneration = IntArray(size) { -1 };

    var hitTmin : Double = Double.POSITIVE_INFINITY;
    var hitTmax : Double = Double.POSITIVE_INFINITY;
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
            val lo = min(t1, t2)
            val hi = max(t1, t2)
            if (lo > tmin) tmin = lo
            if (hi < tmax) tmax = hi
        }

        slab(locPx, locDx, size.x[id])
        if (tmin > tmax) return false
        slab(locPy, locDy, size.y[id])
        if (tmin > tmax) return false
        slab(locPz, locDz, size.z[id])
        if (tmin > tmax) return false

        if(tmax >= max(tmin, 0.0)){
            hitTmin = tmin;
            hitTmax = tmax;
            return true;
        }

        hitTmin = Double.POSITIVE_INFINITY;
        hitTmax = Double.POSITIVE_INFINITY;

        return false;
    }


    fun rebuildGrid() {
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
                if(grid.alreadyChecked[id] != rayID || hitRayGeneration[id] != generation){
                    grid.alreadyChecked[id] = rayID;
                    hitRayGeneration[id] = generation;
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
        shooterColliderID : Int,
        generation: Int,
        rayID : Int,
        denseIDList: IntArrayList,
        hitTminList : DoubleArrayList,
        hitTmaxList : DoubleArrayList,
        px: Double, py: Double, pz: Double,
        dirX: Double, dirY: Double, dirZ: Double,
        maxDist: Double = 1000.0){
        var cell = grid.initializeRay(px, py, pz, dirX, dirY, dirZ, maxDist);
        while(true){
            if(cell == -1) break;

            val start = grid.cellStart[cell]
            val count = grid.cellCount[cell]
            val end = start + count

            //start부터 end - 1 까지 collider의 ID를 순회함.
            for(idxForColliderID in start until end) {

                /**
                 * id : 해당 셀 내에 존재하는 Collider의 denseID 중 하나.
                 * idxForColliderID : 해당 Collider의 ID를 저장하는 배열의 인덱스
                 * */
                val id = grid.cellIndices[idxForColliderID]

                //이미 체크한 ray이고, 이미 해당 generation의 ray에 hit한 경우
                //레이는 매번 생성할 때마다 generation이 달라짐
                if(grid.alreadyChecked[id] == rayID && hitRayGeneration[id] == generation){
                    continue;
                }

                grid.alreadyChecked[id] = rayID;
                hitRayGeneration[id] = generation;

                if(id == shooterColliderID){ //내가 쐈는데 내가 맞을 수는 없잖아
                    continue;
                }

                val hit = rayTestOne(id, px, py, pz, dirX, dirY, dirZ);


                if (!hit) { //안 맞았으면 바로 다음 충돌체 확인
                    continue;
                }

                denseIDList.add(id)
                hitTminList.add(hitTmin);
                hitTmaxList.add(hitTmax);
            }
            cell = grid.nextCell();
        }
    }


    fun rayCastSoA(eventCommandQueue : ArrayDeque<DPhysicsEngine.EventCmd>, raySoA : RaySoA){

        val lastRay = raySoA.lastRay;
        val posX = raySoA.posX;
        val posY = raySoA.posY;
        val posZ = raySoA.posZ;

        val dirX = raySoA.dirX;
        val dirY = raySoA.dirY;
        val dirZ = raySoA.dirZ;

        val rayGeneration = raySoA.rayGeneration;
        if(lastRay <= 0){
            return;
        }

        for(rayID in 0 until lastRay){
            //println("rayCasting... : [${posX[i]}, ${posY[i]}, ${posZ[i]}] + t * [${dirX[i]}, ${dirY[i]}, ${dirZ[i]}]")

            val denseIDList = IntArrayList(8); //배열을 만들어 반환 -> 나중에 고쳐야 할 수도
            val hitTminList = DoubleArrayList(8);
            val hitTmaxList = DoubleArrayList(8);
            val px = posX[rayID]; val py = posY[rayID]; val pz = posZ[rayID];
            val dx = dirX[rayID]; val dy = dirY[rayID]; val dz = dirZ[rayID];

            var colliderID : Int = -1;
            val actorWhoShoot = raySoA.actors[rayID];
            if(actorWhoShoot is DPHitboxEntity){
                colliderID = sparse[Handle(actorWhoShoot.obbHandle).entityID]; //sparse로 dense idx를 구한다.
            }

            rayCastGridOptim(colliderID, rayGeneration, rayID, denseIDList, hitTminList, hitTmaxList, posX[rayID], posY[rayID], posZ[rayID], dirX[rayID], dirY[rayID], dirZ[rayID]);

            val hitActorList = ArrayList<RayHitInfo>();

            if(denseIDList.isEmpty()){
                continue;
            }

            for(j in 0 until denseIDList.size){
                val denseID = denseIDList.getInt(j);

                //val denseID = sparse[entityID];
                //val tHandle = transformHandleList[denseID];
                //val actor = transformSoA.actors[transformSoA.sparse[tHandle.entityID]]
                val hitActor = actors[denseID] as DPHitboxEntity;

                val tMax = hitTminList.getDouble(j);
                val tMin = hitTmaxList.getDouble(j);

                val minX = dx * tMin + px; val minY = dy * tMin + py; val minZ = dz * tMin + pz;
                val maxX = dx * tMax + px; val maxY = dy * tMax + py; val maxZ = dz * tMax + pz;

                hitActorList.add(RayHitInfo(hitActor,
                    minX, minY, minZ,
                    maxX, maxY, maxZ));

                println("HITLIST -> [$j] : ${hitActor.javaClass.simpleName} ${Handle(hitActor.transformHandle).entityID} ${Handle(hitActor.obbHandle).entityID} isValid : ${isValid(Handle(hitActor.obbHandle))}"); // 디버깅용

            }

            //이벤트를 전달한다.
            if(actorWhoShoot is DPEntity){ //광선을 쏜 개체가 EREntity라면
                eventCommandQueue.addLast(DPhysicsEngine.EventCmd(actorWhoShoot, EREntityRayCastEvent(actorWhoShoot,hitActorList)))
            }else if(actorWhoShoot is DProjectile){
                //광선을 쏜 개체가 Projectile이라면
                eventCommandQueue.addLast(DPhysicsEngine.EventCmd(actorWhoShoot, ProjectileRayCastEvent(actorWhoShoot, hitActorList)))
            }
        }
    }


    val checked = BooleanArray(size * size)
    val collidingCell = IntArrayList(512);
    fun collideGridCylinder(){

        val colliderNum = getNumOfEntities()
        val totalCells = grid.dimX * grid.dimY * grid.dimZ

        Arrays.fill(checked, false);

        for(cell in 0 until totalCells){
            if(grid.cellCount[cell] <= 0){
                continue;
            }
            collidingCell.add(cell);
        }
        val size = collidingCell.size
        for(idx in 0 until size){
            val cell = collidingCell.getInt(idx);
            val start = grid.cellStart[cell]
            val end = start + grid.cellCount[cell]

            for(i in start until end){

                val obbA = grid.cellIndices[i]

                for(j in i+1 until end){

                    val obbB = grid.cellIndices[j]

                    val a = minOf(obbA, obbB)
                    val b = maxOf(obbA, obbB)

                    val id = a * colliderNum + b
                    if(checked[id]) continue
                    checked[id] = true
                    collideCylinder(obbA,obbB)
                }
            }
        }

        collidingCell.clear();

    }

    private fun collideCylinder(obb0: Int, obb1: Int): Boolean{
        val deltaTick = 0.05
        val pxa = posCache.x[obb0] + velocityCache.x[obb0] * deltaTick;
        val pza = posCache.z[obb0] + velocityCache.z[obb0] * deltaTick;
        val pxb = posCache.x[obb1] + velocityCache.x[obb1] * deltaTick;
        val pzb = posCache.z[obb1] + velocityCache.z[obb1] * deltaTick;

        val ra = ((size.x[obb0] + size.z[obb0]) * 0.5) * 0.5
        val rb = ((size.x[obb1] + size.z[obb1]) * 0.5) * 0.5

        val dx = pxb - pxa
        val dz = pzb - pza

        val r = sqrt(dx * dx + dz * dz);
        val mag = (ra + rb) - r + EPSAABB;

        if(mag <= 0) return false;

        val nx: Double
        val nz: Double

        if(r < 1e-6){
            nx = 1.0
            nz = 0.0
        }else{
            nx = dx / r
            nz = dz / r
        }

        val mtvX = nx * mag
        val mtvZ = nz * mag

        val obbActor0 = actors[obb0] as DPEntity;
        val obbActor1 = actors[obb1] as DPEntity;

        obbActor0.addVelocity(-mtvX, 0.0, -mtvZ);
        obbActor1.addVelocity(+mtvX, 0.0, +mtvZ);

        return true

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

        val pxa = posCache.x[obb0] + velocityCache.x[obb0] * 0.05 * 4;
        val pya = posCache.y[obb0] + velocityCache.y[obb0] * 0.05 * 4;
        val pza = posCache.z[obb0] + velocityCache.z[obb0] * 0.05 * 4;
        val pxb = posCache.x[obb1] + velocityCache.x[obb1] * 0.05 * 4;
        val pyb = posCache.y[obb1] + velocityCache.y[obb1] * 0.05 * 4;
        val pzb = posCache.z[obb1] + velocityCache.z[obb1] * 0.05 * 4;

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

        val px = (hxa + hxb) - abs(dx) + EPSAABB
        if (px <= 0.0) return false

        val py = (hya + hyb) - abs(dy) + EPSAABB
        if (py <= 0.0) return false

        val pz = (hza + hzb) - abs(dz) + EPSAABB
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
        val obbActor0 = actors[obb0] as DPEntity;
        val obbActor1 = actors[obb1] as DPEntity;


        obbActor0.addVelocity(-mtvX * 0.5, 0.0, -mtvZ * 0.5);
        obbActor1.addVelocity(+mtvX * 0.5, 0.0, +mtvZ * 0.5);

        return true
    }


}
