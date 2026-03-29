package org.dpengine.physics

/**
 * struct Vec3{double x, double y, double z}; 와 같은 역할을 하는
 * SoA 방식으로 저장된 컨테이너
 * */
class Vec3SoA(size : Int) {
    val x = DoubleArray(size);
    val y = DoubleArray(size);
    val z = DoubleArray(size);

    fun allocSoA(idx : Int, xval : Double, yval : Double, zval : Double ){
        x[idx] = xval;
        y[idx] = yval;
        z[idx] = zval;
    }

    fun overwrite(idx0 : Int, idx1 : Int){
        x[idx0] = x[idx1];
        y[idx0] = y[idx1];
        z[idx0] = z[idx1];
    }



    fun getDebugString(idx: Int) : String{
        return "[${x[idx]}, ${y[idx]}, ${z[idx]}]"
    }


}