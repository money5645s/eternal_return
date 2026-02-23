package org.eternalreturn.util.dpengine.physics

import it.unimi.dsi.fastutil.ints.IntArrayList
import kotlin.collections.set

/**
 * SparseIdx와 GenerationIdx를 함께 저장
 * SparseIdx가 같아도 Gen이 다르면 바로 SoA모듈 상에서 resolve() 메소드 호출됨.
 * */
class Handle(val entityID : Int, val generation : Int){

}


class TransformSoA(size : Int) : SoAModule(size){

    val position = Vec3SoA(size);

    fun create(x : Double, y : Double, z : Double) : Handle{
        val triple = super.createHandle(); // (entityID, denseID, generation)
        val entityID = triple.first;
        val denseID = triple.second;
        val generation = triple.third;
        position.allocSoA(denseID, x, y, z);
        return Handle(entityID, generation)
    }

    fun remove(handle : Handle){
        val pair = super.removeHandle(handle);
        position.overwrite(pair.first, pair.second);
    }

    fun getDebugString(handle: Handle) : String{
        val denseID = super.sparse[handle.entityID];
        return position.getDebugString(denseID);
    }

}

fun main(){
    val module = TransformSoA(512);

    val handleList = ArrayList<Handle>();
    for(i in 0 .. 4){
        val x = Math.random();
        val y = Math.random();
        val z = Math.random();
        handleList.add(module.create(x, y, z));
        println("$x, $y, $z")
    }

    module.remove(handleList[3]);
    println(module.getDebugString(handleList[4]));
}