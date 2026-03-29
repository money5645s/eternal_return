package org.dpengine.geometry

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


open class GeometryCalculatable{

    public lateinit var matVecCalculator : MatVecCalculator;

    fun geometryCalculatableInit(matVecCalculator: MatVecCalculator){
        this.matVecCalculator = matVecCalculator
    }


    fun magnitudeSqr(distVec: Vector3) : Double{
        val x = matVecCalculator.x(distVec);
        val y = matVecCalculator.y(distVec);
        val z = matVecCalculator.z(distVec);
        return x*x + y*y + z*z;
    }

    fun magnitude(distVec: Vector3) : Double{
        val x = matVecCalculator.x(distVec);
        val y = matVecCalculator.y(distVec);
        val z = matVecCalculator.z(distVec);
        return sqrt(x*x + y*y + z*z);
    }

    infix fun Vector3.dot(b: Vector3): Double {
        return matVecCalculator.dotprd(b, this)
    }

    /**
     * = 과 같음.
     * */
    infix fun Vector3.assign(b: Vector3): Vector3 {
        val out = matVecCalculator.vec3()
        matVecCalculator.assign(out, this)
        return out
    }

    infix fun Vector3.cross(b: Vector3): Vector3 {
        val out = matVecCalculator.vec3()
        matVecCalculator.cross(out, b, this)
        return out
    }

    operator fun Vector3.plus(b: Vector3): Vector3 {
        val out = matVecCalculator.vec3()
        matVecCalculator.add(out, this, b)
        return out
    }

    operator fun Vector3.minus(b: Vector3): Vector3 {
        val out = matVecCalculator.vec3()
        matVecCalculator.sub(out, this, b)
        return out
    }

    operator fun Vector3.times(scalar: Double): Vector3 {
        val out = matVecCalculator.vec3()
        matVecCalculator.scalarProd(out, scalar, this)
        return out
    }

    operator fun Double.times(vector: Vector3): Vector3 {
        val out = matVecCalculator.vec3()
        matVecCalculator.scalarProd(out, this, vector)
        return out
    }

    operator fun Vector3.unaryMinus(): Vector3 {
        val out = matVecCalculator.vec3()
        matVecCalculator.scalarProd(out, -1.0, this)
        return out
    }

    fun Vector3.rotXZ(roatation : Double) : Vector3{
        val rx = this.x() * cos(Math.toRadians(roatation)) - this.z() * sin(Math.toRadians(roatation))
        val rz = this.x() * sin(Math.toRadians(roatation)) + this.z() * cos(Math.toRadians(roatation))
        return vec3(rx, 0.0, rz);
    }

    fun Vector3.x(value : Double){
        matVecCalculator.setX(this, value);
    }

    fun Vector3.y(value : Double){
        matVecCalculator.setY(this, value);
    }

    fun Vector3.z(value : Double){
        matVecCalculator.setZ(this, value);
    }


    fun Vector3.x(): Double{
        return matVecCalculator.x(this)
    }

    fun Vector3.y(): Double{
        return matVecCalculator.y(this)
    }

    fun Vector3.z(): Double{
        return matVecCalculator.z(this)
    }


    /**
     * +=, -= 부분
     * */
    operator fun Vector3.plusAssign(b: Vector3) {
        matVecCalculator.add(this, this, b)
    }

    operator fun Vector3.minusAssign(b: Vector3) {
        matVecCalculator.sub(this, this, b)
    }

    fun vec3(): Vector3 = matVecCalculator.vec3()

    fun vec3(x : Double, y : Double, z : Double): Vector3 = matVecCalculator.vec3(x, y, z)

}