package org.eternalreturn.erentity

import org.bukkit.entity.Entity
import org.eternalreturn.ercharacter.character.fiora.ToucheCount
import org.eternalreturn.ercharacter.character.fiora.ToucheEffect
import org.eternalreturn.ercharacter.character.hart.Passive_Timer
import org.eternalreturn.ercharacter.character.isaac.PassiveCount
import org.eternalreturn.ercharacter.character.lidailin.LiDailinPassiveTimer
import org.eternalreturn.erentity.globalmonobehav.EntityRayCastingMeleeAttack
import org.eternalreturn.erentity.globalmonobehav.Stun
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import org.eternalreturn.util.dpengine.command.SetSpigotEntityPosition
import org.eternalreturn.util.dpengine.command.SetSpigotEntityVelocity
import org.eternalreturn.util.dpengine.geometry.Vector3
import org.eternalreturn.util.dpengine.physics.Handle
import kotlin.math.cos
import kotlin.math.sin

/**
 * 모든 EREntity의 Subclass에게 동시에 통용되는 성질을 저장하는 곳.
 * 자신의 위치를 저장하는 Handle을 소유한다.
 */
abstract class EREntity( // extends MonobehaviourActor()
    erEngine: EREngine,
    val entity : Entity,
    obbHalfX : Double, obbHalfY : Double, obbHalfZ : Double,
    obbLocX : Double, obbLocY : Double, obbLocZ : Double
    /**
     * 해당 MonobehaviourActor의 Collider 설정
     */
) : MonobehaviourActor(erEngine) {

    val erEngine : EREngine
        get(){
            return dpEngine as EREngine;
        }

    var maxRange : Double = 5.0; //바뀔 수 있음

    val transformHandle : Handle = erEngine.transformSoA.create(
        entity.location.x, entity.location.y,entity.location.z,
        0.0, 0.0, 0.0 );

    val obbHandle : Handle = erEngine.orientedBoxSoA.create(transformHandle,
        obbHalfX, obbHalfY, obbHalfZ,
        obbLocX, obbLocY, obbLocZ
    );

    init{
        transformHandle.actor = this;
        obbHandle.actor = this;
        println("[SoA CREATE] ${this.javaClass.simpleName} T${transformHandle.entityID} | O${obbHandle.entityID}")
    }

    private var shootRay : Boolean = false;
    fun isShootingRay() : Boolean{
        val ret = shootRay;
        shootRay = false;
        return ret;
    }
    fun shootRay(){
        shootRay = true;
    }

    /**
     * 해당 객체를 소유하고 있는 EREngine의 삭제 리스트에 해당 객체를 삽입한다.
     * */
    override fun remove(){
        if(referenceCount == 0)return;
        super.remove();
        erEngine.remove(this);
    }

    init {
        //Monobehaviour 등록
        this.registerMonobehaviour(Stun())
        this.registerMonobehaviour(ToucheCount())
        this.registerMonobehaviour(ToucheEffect())
        this.registerMonobehaviour(Passive_Timer())
        this.registerMonobehaviour(LiDailinPassiveTimer())
        this.registerMonobehaviour(PassiveCount())
        this.registerMonobehaviour(EntityRayCastingMeleeAttack())
    }

    /**
     * 플레이어의 위치벡터 + 방향벡터를 얻어온다.
     * No Scoping
     */
    fun getDirection(): Vector3 {
        val location = entity.location
        val radX = Math.toRadians(location.yaw.toDouble())
        val radY = Math.toRadians(location.pitch.toDouble())
        val xz = cos(radY)
        return this.geometryModule.vec3(-xz * sin(radX), -sin(radY), xz * cos(radX));
    }

    fun getPosition(): Vector3 {
        val location = entity.location
        return this.geometryModule.vec3(location.x, location.y, location.z)
    }

    fun setPosition(pos : Vector3) {
        val x = geometryModule.x(pos);
        val y = geometryModule.y(pos);
        val z = geometryModule.z(pos);
        this.geometryModule.dpEngine.appendCommandQueue(SetSpigotEntityPosition(entity!!, x, y, z))
    }

    fun setVelocity(pos : Vector3){
        val x = geometryModule.x(pos);
        val y = geometryModule.y(pos);
        val z = geometryModule.z(pos);
        this.geometryModule.dpEngine.appendCommandQueue(SetSpigotEntityVelocity(entity!!, x, y, z))
    }


}