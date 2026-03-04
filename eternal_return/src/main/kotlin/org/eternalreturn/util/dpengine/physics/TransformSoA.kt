package org.eternalreturn.util.dpengine.physics

import it.unimi.dsi.fastutil.ints.IntArrayList
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import kotlin.collections.set
import kotlin.math.cos
import kotlin.math.sin




class TransformSoA(size : Int) : SoAModule(size){

    val position = Vec3SoA(size); //위치
    val rotation = Vec3SoA(size); //몸 자체의 회전
    val direction = Vec3SoA(size); //바라보는 방향
    val velocity = Vec3SoA(size);
    val isModifiedVelocity = BooleanArray(size){false};
    val isModifiedPosition = BooleanArray(size){false};

    /**
     * 위치벡터 [x, y, z], 각도(호도법 : -180 ~ 180) [rotX, rotY, rotZ]를 전달받아 Handle 생성 & 반환
     * */
    fun create(x : Double, y : Double, z : Double, rotX : Double, rotY : Double, rotZ : Double) : Handle{
        val (entityID, denseID, generation) = super.createHandle(); // (entityID, denseID, generation)
        position.allocSoA(denseID, x, y, z);
        rotation.allocSoA(denseID, rotX, rotY, rotZ);
        velocity.allocSoA(denseID, 0.0, 0.0, 0.0);

        val radX = Math.toRadians(rotY)
        val radY = Math.toRadians(rotX)
        val xz = cos(radY)
        val dirX = -xz * sin(radX); val dirY =  -sin(radY); val dirZ =  xz * cos(radX)
        direction.allocSoA(denseID, dirX, dirY, dirZ);
        return Handle(entityID, generation);
    }

    fun cachePosition(handle : Handle, x : Double, y : Double, z : Double){
        if(isValid(handle)){
            val denseID = sparse[handle.entityID];
            position.x[denseID] = x;
            position.y[denseID] = y;
            position.z[denseID] = z;
            isModifiedPosition[denseID] = false;
        }
    }
    
    /**
     * 라디안 각도 전달
     * */
    fun cacheRotation(handle : Handle, rx : Double, ry : Double, rz : Double){
        if(isValid(handle)){
            val denseID = sparse[handle.entityID];
            rotation.x[denseID] = rx;
            rotation.y[denseID] = ry;
            rotation.z[denseID] = rz;
        }
    }

    /**
     * 라디안 각도 전달
     * */
    fun cacheDirection(handle : Handle, rx : Double, ry : Double, rz : Double){
        if(isValid(handle)){
            val denseID = sparse[handle.entityID];
            val xz = cos(ry)
            direction.x[denseID] = -xz * sin(rx);
            direction.y[denseID] = -sin(ry);
            direction.z[denseID] = xz * cos(rx);
        }
    }

    fun remove(handle : Handle){
        val (idx0, idx1) = super.removeHandle(handle);
        position.overwrite(idx0, idx1);
        rotation.overwrite(idx0, idx1);
        direction.overwrite(idx0, idx1);
        velocity.overwrite(idx0, idx1);
        isModifiedVelocity[idx0] = isModifiedVelocity[idx1];
        isModifiedPosition[idx0] = isModifiedPosition[idx1];
    }

    fun cacheVelocity(handle: Handle, x: Double, y: Double, z: Double) {
        if(isValid(handle)){
            val denseID = sparse[handle.entityID];
            velocity.x[denseID] = x;
            velocity.y[denseID] = y;
            velocity.z[denseID] = z;
            isModifiedVelocity[denseID] = false;
        }
    }

    fun setPosition(handle: Handle, x: Double, y: Double, z: Double) {
        if(isValid(handle)){
            val denseID = sparse[handle.entityID];
            position.x[denseID] = x;
            position.y[denseID] = y;
            position.z[denseID] = z;
            isModifiedPosition[denseID] = true;
        }
    }

    fun addPosition(handle: Handle, x: Double, y: Double, z: Double) {
        if(isValid(handle)){
            val denseID = sparse[handle.entityID];
            position.x[denseID] += x;
            position.y[denseID] += y;
            position.z[denseID] += z;
            isModifiedPosition[denseID] = true;
        }
    }

    fun setVelocity(handle: Handle, x: Double, y: Double, z: Double) {
        if(isValid(handle)){
            val denseID = sparse[handle.entityID];
            velocity.x[denseID] = x;
            velocity.y[denseID] = y;
            velocity.z[denseID] = z;
            isModifiedVelocity[denseID] = true;
        }
    }

    fun addVelocity(handle: Handle, x: Double, y: Double, z: Double) {
        if(isValid(handle)){
            val denseID = sparse[handle.entityID];
            velocity.x[denseID] += x;
            velocity.y[denseID] += y;
            velocity.z[denseID] += z;
            isModifiedVelocity[denseID] = true;
        }
    }

    val eps7 = 1E-7;
    fun isNotTranslating(handle: Handle): Boolean {
        if(isValid(handle)){
            val denseID = sparse[handle.entityID];
            val vx = velocity.x[denseID];
            val vy = velocity.y[denseID];
            val vz = velocity.z[denseID];
            return (eps7 > vx && vx > -eps7) && (eps7 > vy && vy > -eps7) && (eps7 > vz && vz > -eps7)
        }
        return true;
    }


    fun getDebugString(handle: Handle) : String{
        val denseID = super.sparse[handle.entityID];
        return position.getDebugString(denseID);
    }


}