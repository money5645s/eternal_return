package org.eternalreturn.util.dpengine.physics

class TransformSoA(size : Int) {


    private val px = DoubleArray(size);
    private val py = DoubleArray(size);
    private val pz = DoubleArray(size);
    private val sparseIndex = IntArray(size);
    private val denseIndex = IntArray(size);

    var lastIdx = 0;

    fun newIdx(entity : Int, x : Double, y : Double, z : Double){
        px[lastIdx] = x; py[lastIdx] = y; pz[lastIdx] = z;
        denseIndex[lastIdx] = entity;
        sparseIndex[entity] = lastIdx; // sparse -> dense -> datas
        lastIdx++;
    }

    fun remove(id : Int){
        val lastId = lastIdx - 1;
        px[id] = px[lastId];
        py[id] = py[lastId];
        pz[id] = pz[lastId];

    }


}

class TestEntity(val ){

}

fun main(){
    val module = TransformSoA();
    val e0 =
    module.newIdx(0.0, 0.0, 0.0);



}