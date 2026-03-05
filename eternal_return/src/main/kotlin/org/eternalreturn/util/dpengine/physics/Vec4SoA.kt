package org.eternalreturn.util.dpengine.physics


/**
 * struct Vec4{double x, double y, double z, double w}; 와 같은 역할을 하는
 * SoA 방식으로 저장된 컨테이너
 * */
class Vec4SoA(size : Int) {
    val x = DoubleArray(size);
    val y = DoubleArray(size);
    val z = DoubleArray(size);
    val w = DoubleArray(size);

    fun allocSoA(idx : Int, xval : Double, yval : Double, zval : Double, wval : Double){
        x[idx] = xval;
        y[idx] = yval;
        z[idx] = zval;
        w[idx] = wval;
    }

    fun overwrite(idx0 : Int, idx1 : Int){
        x[idx0] = x[idx1];
        y[idx0] = y[idx1];
        z[idx0] = z[idx1];
        w[idx0] = w[idx1];
    }

    fun getDebugString(idx: Int) : String{
        return "[${x[idx]}, ${y[idx]}, ${z[idx]}, ${w[idx]}]"
    }


}