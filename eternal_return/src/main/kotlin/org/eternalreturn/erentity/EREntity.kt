package org.eternalreturn.erentity

import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.util.Vector
import org.eternalreturn.ercharacter.character.fiora.ToucheCount
import org.eternalreturn.ercharacter.character.hart.Passive_Timer
import org.eternalreturn.ercharacter.character.hyunwoo.HyunwooPassiveTimer
import org.eternalreturn.ercharacter.character.isaac.PassiveCount
import org.eternalreturn.ercharacter.character.jan.JanActiveTimer
import org.eternalreturn.ercharacter.character.jan.JanPassiveTimer
import org.eternalreturn.ercharacter.character.lidailin.LiDailinPassiveTimer
import org.eternalreturn.ercharacter.event.CharacterAttackEvent
import org.eternalreturn.erentity.globalmonobehav.EntityRayCastingMeleeAttack
import org.eternalreturn.erentity.globalmonobehav.Stun
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourModule
import org.eternalreturn.util.dpengine.command.SetSpigotEntityPosition
import org.eternalreturn.util.dpengine.geometry.Vector3
import org.eternalreturn.util.dpengine.physics.Handle
import kotlin.math.cos
import kotlin.math.sin

/**
 * 모든 EREntity의 Subclass에게 동시에 통용되는 성질을 저장하는 곳.
 * 자신의 위치를 저장하는 Handle을 소유한다.
 */
abstract class EREntity(
    erEngine : EREngine,
    val entity : Entity
) : MonobehaviourActor(erEngine){

    var maxRange : Double = 5.0;

    val erEngine : EREngine
        get(){
            return dpEngine as EREngine;
        }

    val transformHandle : Handle = erEngine.transformSoA.create(
        entity.location.x, entity.location.y,entity.location.z,
        0.0, 0.0, 0.0 );

    init {
        transformHandle.actor = this;

        //Monobehaviour 등록
        this.registerMonobehaviour(Stun())
        this.registerMonobehaviour(Passive_Timer())
        this.registerMonobehaviour(LiDailinPassiveTimer())
        this.registerMonobehaviour(PassiveCount())
        this.registerMonobehaviour(JanPassiveTimer())
        this.registerMonobehaviour(JanActiveTimer())
        this.registerMonobehaviour(HyunwooPassiveTimer())
        this.registerMonobehaviour(ToucheCount())
        this.registerMonobehaviour(EntityRayCastingMeleeAttack())
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

    open fun getPosition(): Vector3 {
        val location = entity.location
        return this.geometryModule.vec3(location.x, location.y, location.z)
    }

    open fun setPosition(pos : Vector3) {
        val x = geometryModule.x(pos);
        val y = geometryModule.y(pos);
        val z = geometryModule.z(pos);
        this.geometryModule.dpEngine.appendCommandQueue(SetSpigotEntityPosition(entity, x, y, z))
    }

    open fun setVelocity(vec : Vector3){
        val x = geometryModule.x(vec);
        val y = geometryModule.y(vec);
        val z = geometryModule.z(vec);
        erEngine.transformSoA.setVelocity(transformHandle, x, y, z);
    }

    open fun addVelocity(vec : Vector3){
        val x = geometryModule.x(vec);
        val y = geometryModule.y(vec);
        val z = geometryModule.z(vec);
        erEngine.transformSoA.addVelocity(transformHandle, x, y, z);
    }

    open fun setVelocity(x : Double, y : Double, z : Double){
        erEngine.transformSoA.setVelocity(transformHandle, x, y, z);
    }

    open fun addVelocity(x : Double, y : Double, z : Double){
        erEngine.transformSoA.addVelocity(transformHandle, x, y, z);
    }

    open fun setPosition(x : Double, y : Double, z : Double){
        erEngine.transformSoA.setPosition(transformHandle, x, y, z);
    }

    open fun addPosition(x : Double, y : Double, z : Double){
        erEngine.transformSoA.addPosition(transformHandle, x, y, z);
    }

    open fun applyBukkitVelocityOnMainThread(x: Double, y: Double, z: Double) {
        val velocity = Vector(x, y, z);
        entity.velocity = velocity;
    }

    open fun isNotTranslating(): Boolean {
        return erEngine.transformSoA.isNotTranslating(transformHandle);
    }

    open fun damage(amount : Double, attacker : EREntity){
        if(entity is LivingEntity){
            attacker.submitEvent(CharacterAttackEvent(attacker, this))
            entity.damage(amount, attacker.entity); //이것도 특수한 SoA 함수로 뺄 것
        }
    }
}

