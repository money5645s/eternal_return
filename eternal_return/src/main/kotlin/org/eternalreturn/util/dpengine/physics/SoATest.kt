package org.eternalreturn.util.dpengine.physics

import it.unimi.dsi.fastutil.doubles.DoubleArrayList
import it.unimi.dsi.fastutil.ints.IntArrayList
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class SoATest(size : Int) : SoAModule(size){
    val transformSoA = TransformSoA(size);
    val orientedBoxSoA = OrientedBoxSoA(size, transformSoA,
        UniformGrid(100.0, 100.0, 100.0, -100.0, -100.0, -100.0, 200.0, size));

    fun createTestEntity(x : Double, y : Double, z : Double, bx : Double, by : Double, bz : Double, qx : Double, qy : Double, qz : Double, qw : Double) : TestEntity{
        val tHandle = transformSoA.create(x, y, z);
        val bHandle = orientedBoxSoA.create(tHandle, bx, by, bz, qx, qy, qz, qw);
        return TestEntity(tHandle, bHandle);
    }

    fun updateUniformGrid(){
        orientedBoxSoA.rebuildGrid();
    }

}

class TestEntity(val transformHandle : Handle, val colliderHandle : Handle)

fun main(){

    val size = 512
    val module = SoATest(size)
    val entityList = ArrayList<TestEntity>(size)

    val rand = Random(42)

    // ---------------------------
    // 1. 엔티티 생성
    // ---------------------------
    val N = 300

    repeat(N){
        val x = rand.nextDouble(-100.0, 100.0)
        val y = rand.nextDouble(-100.0, 100.0)
        val z = rand.nextDouble(-100.0, 100.0)

        // collider half size (OBB)
        val bx = rand.nextDouble(0.5, 3.0);
        val by = rand.nextDouble(0.5, 3.0);
        val bz = rand.nextDouble(0.5, 3.0);

        // quaternion 랜덤 생성 (정규화)
        var qx = rand.nextDouble(-1.0, 1.0)
        var qy = rand.nextDouble(-1.0, 1.0)
        var qz = rand.nextDouble(-1.0, 1.0)
        var qw = rand.nextDouble(-1.0, 1.0)

        val norm = kotlin.math.sqrt(qx*qx + qy*qy + qz*qz + qw*qw)
        qx /= norm; qy /= norm; qz /= norm; qw /= norm

        entityList.add(
            module.createTestEntity(
                x, y, z,
                bx, by, bz,
                qx, qy, qz, qw
            )
        )
    }

    println("Created entities: ${entityList.size}")

    // ---------------------------
    // 2. 레이캐스트 테스트
    // ---------------------------

    val rayCount = 1000

    module.orientedBoxSoA.updatePosCache();
    module.orientedBoxSoA.updateRotCache();
    module.orientedBoxSoA.rebuildGrid();

    val posX = DoubleArrayList();
    val posY = DoubleArrayList();
    val posZ = DoubleArrayList();
    val dirX = DoubleArrayList();
    val dirY = DoubleArrayList();
    val dirZ = DoubleArrayList();

    for(i in 0 until rayCount) {

        // 레이 시작점
        posX.add(rand.nextDouble(-100.0, 100.0));
        posY.add(rand.nextDouble(-100.0, 100.0));
        posZ.add(rand.nextDouble(-100.0, 100.0));

        // 방향 벡터 정규화
        var dx = rand.nextDouble(-1.0, 1.0);
        var dy = rand.nextDouble(-1.0, 1.0);
        var dz = rand.nextDouble(-1.0, 1.0);
        val len = kotlin.math.sqrt(dx*dx + dy*dy + dz*dz)
        dx /= len; dy /= len; dz /= len
        dirX.add(dx)
        dirY.add(dy)
        dirZ.add(dz)
    }

    var totalHit = 0;

    totalHit = 0;
    val time0 = measureTimeMillis {
        for(i in 0 until rayCount){
            val hitList = IntArrayList(size)
            module.orientedBoxSoA.rayCast(
                hitList,
                posX.getDouble(i), posY.getDouble(i), posZ.getDouble(i),
                dirX.getDouble(i), dirY.getDouble(i), dirZ.getDouble(i)
            )

            // 히트 수 세기
            for(i in 0 until hitList.size){
                totalHit++;
                //for(id in hitList){ print("$id ") } println();

            }



        }
    }

    println("Ray count = $rayCount")
    println("Total hit = $totalHit")
    println("Elapsed = ${time0}ms")

    totalHit = 0;
    val time1 = measureTimeMillis {
        for(i in 0 until rayCount){
            val hitList = IntArrayList(size)
            module.orientedBoxSoA.rayCastGrid(
                hitList,
                posX.getDouble(i), posY.getDouble(i), posZ.getDouble(i),
                dirX.getDouble(i), dirY.getDouble(i), dirZ.getDouble(i)
            )



            // 히트 수 세기
            for(i in 0 until hitList.size){
                totalHit++;
                //for(id in hitList){ print("$id ") } println();

            }
        }
    }

    println("Ray count = $rayCount")
    println("Total hit = $totalHit")
    println("Elapsed = ${time1}ms")



}