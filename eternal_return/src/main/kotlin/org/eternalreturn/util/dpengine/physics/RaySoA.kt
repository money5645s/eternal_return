package org.eternalreturn.util.dpengine.physics

import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import kotlin.math.cos
import kotlin.math.sin

class RaySoA(
    val erEngine: EREngine,
    bufferSize : Int) {

    val posX = DoubleArray(bufferSize);// 레이캐스팅용 객체
    val posY = DoubleArray(bufferSize);// 레이캐스팅용 객체
    val posZ = DoubleArray(bufferSize);// 레이캐스팅용 객체
    val dirX = DoubleArray(bufferSize);// 레이캐스팅용 객체
    val dirY = DoubleArray(bufferSize);// 레이캐스팅용 객체
    val dirZ = DoubleArray(bufferSize);// 레이캐스팅용 객체
    val actors = ArrayList<MonobehaviourActor>();

    var lastRay = 0;
    var rayGeneration : Int = 0;

    fun addRay(actor : MonobehaviourActor, px : Double, py : Double, pz : Double, rotX : Double, rotY : Double){

        val xz = cos(rotY)
        val dx = -xz * sin(rotX); val dy = -sin(rotY); val dz =  xz * cos(rotX)
        posX[lastRay] = px; posY[lastRay] = py; posZ[lastRay] = pz;
        dirX[lastRay] = dx; dirY[lastRay] = dy; dirZ[lastRay] = dz;
        actors.add(actor);
        lastRay++;
    }

    fun addRay(actor : MonobehaviourActor, px : Double, py : Double, pz : Double, dx : Double, dy : Double, dz : Double){
        posX[lastRay] = px; posY[lastRay] = py; posZ[lastRay] = pz;
        dirX[lastRay] = dx; dirY[lastRay] = dy; dirZ[lastRay] = dz;
        actors.add(actor);
        lastRay++;
    }

    fun freeRays(){
        actors.clear();
        rayGeneration = (rayGeneration + 1) //generation변경
        lastRay = 0; //ray들 초기화
    }








}