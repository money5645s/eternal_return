package org.dpengine.physics

class Mat3x3SoA(size : Int) {
    val m00 = DoubleArray(size); val m01 = DoubleArray(size); val m02 = DoubleArray(size);
    val m10 = DoubleArray(size); val m11 = DoubleArray(size); val m12 = DoubleArray(size);
    val m20 = DoubleArray(size); val m21 = DoubleArray(size); val m22 = DoubleArray(size);

    fun allocSoA( id : Int,
        v00 : Double, v01 : Double, v02 : Double,
        v10 : Double, v11 : Double, v12 : Double,
        v20 : Double, v21 : Double, v22 : Double
    ){
        m00[id] = v00; m01[id] = v01; m02[id] = v02;
        m10[id] = v10; m11[id] = v11; m12[id] = v12;
        m20[id] = v20; m21[id] = v21; m22[id] = v22;
    }

    fun overwrite(idx0 : Int, idx1 : Int){
        m00[idx0] = m00[idx1]; m01[idx0] = m01[idx1]; m02[idx0] = m02[idx1];
        m10[idx0] = m10[idx1]; m11[idx0] = m11[idx1]; m12[idx0] = m12[idx1];
        m20[idx0] = m20[idx1]; m21[idx0] = m21[idx1]; m22[idx0] = m22[idx1];
    }

    fun getDebugString(idx: Int) : String{
        return "[${m00[idx]}, ${m01[idx]}, ${m02[idx]}]\n [${m10[idx]}, ${m11[idx]}, ${m12[idx]}]\n [${m20[idx]}, ${m21[idx]}, ${m22[idx]}]";
    }

}