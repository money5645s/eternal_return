package org.eternalreturn.util.dpengine.physics

/**
 * 1차원으로 저장되는 3차원 Integer 배열
 * x의 접근이 가장 빠르고, z의 접근이 가장 느리다.
 * */
class IntArray3D(val sizeX : Int, val sizeY : Int, val sizeZ : Int, initialValue : Int) {
    val flatArr = IntArray(sizeX * sizeY * sizeZ) {initialValue};

    operator fun get(ix : Int, iy : Int, iz : Int) : Int{
        return flatArr[sizeX * sizeY * iz + sizeZ * iy + ix];
    }

    operator fun set(ix : Int, iy : Int, iz : Int, value : Int){
        flatArr[sizeX * sizeY * iz + sizeZ * iy + ix] = value;
    }

}