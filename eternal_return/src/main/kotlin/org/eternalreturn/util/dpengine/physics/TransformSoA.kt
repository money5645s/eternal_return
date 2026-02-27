package org.eternalreturn.util.dpengine.physics

import it.unimi.dsi.fastutil.ints.IntArrayList
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import kotlin.collections.set
import kotlin.math.cos
import kotlin.math.sin

/**
 * SparseIdx와 GenerationIdx를 함께 저장
 * SparseIdx가 같아도 Gen이 다르면 바로 SoA모듈 상에서 resolve() 메소드 호출됨.
 * */
class Handle(val entityID : Int, val generation : Int, var actor : MonobehaviourActor? = null){

}


class TransformSoA(size : Int) : SoAModule(size){

    val position = Vec3SoA(size); //위치
    val rotation = Vec3SoA(size); //몸 자체의 회전
    val direction = Vec3SoA(size); //바라보는 방향

    /**
     * 위치벡터 [x, y, z], 각도(호도법 : -180 ~ 180) [rotX, rotY, rotZ]를 전달받아 Handle 생성 & 반환
     * */
    fun create(x : Double, y : Double, z : Double, rotX : Double, rotY : Double, rotZ : Double) : Handle{
        val (entityID, denseID, generation) = super.createHandle(); // (entityID, denseID, generation)
        position.allocSoA(denseID, x, y, z);
        rotation.allocSoA(denseID, rotX, rotY, rotZ);

        val radX = Math.toRadians(rotY)
        val radY = Math.toRadians(rotX)
        val xz = cos(radY)
        val dirX = -xz * sin(radX); val dirY =  -sin(radY); val dirZ =  xz * cos(radX)
        direction.allocSoA(denseID, dirX, dirY, dirZ);
        return Handle(entityID, generation);
    }

    fun setPosition(handle : Handle, x : Double, y : Double, z : Double){
        if(isValid(handle)){
            val denseID = sparse[handle.entityID];
            position.x[denseID] = x;
            position.y[denseID] = y;
            position.z[denseID] = z;
        }
    }
    
    /**
     * 라디안 각도 전달
     * */
    fun setRotation(handle : Handle, rx : Double, ry : Double, rz : Double){
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
    fun setDirection(handle : Handle, rx : Double, ry : Double, rz : Double){
        if(isValid(handle)){
            val denseID = sparse[handle.entityID];
            val xz = cos(ry)
            direction.x[denseID] = -xz * sin(rx);
            direction.y[denseID] = -sin(ry);
            direction.z[denseID] = xz * cos(rx);
        }
    }

    fun remove(handle : Handle){
        val pair = super.removeHandle(handle);
        position.overwrite(pair.first, pair.second);
        rotation.overwrite(pair.first, pair.second);
        direction.overwrite(pair.first, pair.second);
    }

    fun getDebugString(handle: Handle) : String{
        val denseID = super.sparse[handle.entityID];
        return position.getDebugString(denseID);
    }

}