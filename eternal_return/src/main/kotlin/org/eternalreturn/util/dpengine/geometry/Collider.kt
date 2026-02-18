package org.eternalreturn.util.dpengine.geometry

abstract class Collider(var engine: GeometryModule) {
    abstract fun setPosition(x: Double, y: Double, z: Double)
    abstract fun setDirection(x: Double, y: Double, z: Double)
    abstract fun rayCasting(out: Vector3, pos: Vector3, dir: Vector3): Boolean
}