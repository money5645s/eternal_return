package org.eternalreturn.util.dpengine.physics

import it.unimi.dsi.fastutil.doubles.DoubleArrayList
import it.unimi.dsi.fastutil.ints.IntArrayList
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class SoATest(size : Int) : SoAModule(size){
    val transformSoA = TransformSoA(size);
    val orientedBoxSoA = OrientedBoxSoA(size, transformSoA,
        UniformGrid(100.0, 100.0, 100.0, -100.0, -100.0, -100.0, 10.0, size));

    fun createTestEntity(x : Double, y : Double, z : Double, bx : Double, by : Double, bz : Double, rx : Double, ry : Double, rz : Double) : TestEntity{
        val tHandle = transformSoA.create(x, y, z, rx, ry, rz);
        val bHandle = orientedBoxSoA.create(tHandle, bx, by, bz, 0.0, 0.0, 0.0);
        return TestEntity(tHandle, bHandle);
    }

    fun updateUniformGrid(){
        orientedBoxSoA.rebuildGrid();
    }

}

class TestEntity(val transformHandle : Handle, val colliderHandle : Handle)

fun main(){

    val size = 1024
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
        val rx = Math.toRadians(rand.nextDouble(-180.0, 180.0));
        val ry = Math.toRadians(rand.nextDouble(-180.0, 180.0));
        val rz = Math.toRadians(rand.nextDouble(-180.0, 180.0));

        //val norm = kotlin.math.sqrt(rx*rx + ry*ry + rz*rz)
        //rx /= norm; ry /= norm; rz /= norm;

        entityList.add(
            module.createTestEntity(
                x, y, z,
                bx, by, bz,
                rx, ry, rz
            )
        )
    }

    println("Created entities: ${entityList.size}")

    // ---------------------------
    // 2. 레이캐스트 테스트
    // ---------------------------

    val rayCount = 1000

    module.orientedBoxSoA.updateCacheFromTransform();
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


    for(i in 0 until rayCount){
        val hitList0 = IntArrayList(size)
        val hitList1 = IntArrayList(size)



        module.orientedBoxSoA.rayCast(
            0, i, hitList0,
            posX.getDouble(i), posY.getDouble(i), posZ.getDouble(i),
            dirX.getDouble(i), dirY.getDouble(i), dirZ.getDouble(i)
        )


        module.orientedBoxSoA.rayCastGridOptim(
            1, i, hitList1,
            posX.getDouble(i), posY.getDouble(i), posZ.getDouble(i),
            dirX.getDouble(i), dirY.getDouble(i), dirZ.getDouble(i)
        )

        if(hitList0.size != hitList1.size){
            print("ray : $i,  ${hitList0.size}, ${hitList1.size} ");
            for(id in hitList0){ print("$id "); }; for(id in hitList1){ print("$id "); }; println();
        }

    }



}