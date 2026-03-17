package org.eternalreturn.erentity

import org.bukkit.Location
import org.bukkit.attribute.Attributable
import org.bukkit.attribute.Attribute
import org.bukkit.damage.DamageSource
import org.bukkit.damage.DamageType
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.util.Vector
import org.eternalreturn.ercharacter.character.fiora.ToucheCount
import org.eternalreturn.ercharacter.character.fiora.ToucheEffect
import org.eternalreturn.ercharacter.character.hart.Passive_Timer
import org.eternalreturn.ercharacter.character.isaac.PassiveCount
import org.eternalreturn.ercharacter.character.lidailin.LiDailinPassiveTimer
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erentity.events.EREntityDamagedEvent
import org.eternalreturn.erentity.globalmonobehav.Burn
import org.eternalreturn.erentity.globalmonobehav.EREntityMonobehavCreatedEvent
import org.eternalreturn.erentity.globalmonobehav.Stun
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import org.eternalreturn.util.dpengine.command.*
import org.eternalreturn.util.dpengine.geometry.Vector3
import org.eternalreturn.util.dpengine.physics.Handle
import kotlin.math.cos
import kotlin.math.sin
import kotlin.properties.Delegates

/**
 * 모든 EREntity의 Subclass에게 동시에 통용되는 성질을 저장하는 곳.
 * 자신의 위치를 저장하는 Handle을 소유한다.
 */
abstract class EREntity(
    erEngine : EREngine,
    val entity : Entity
) : MonobehaviourActor(erEngine){

    var maxRange : Double = 5.0;

    open val location : Location
        get() = entity.location;

    val erEngine : EREngine
        get() = dpEngine as EREngine;


    var transformHandle : Handle by Delegates.notNull()

    /**
     * Multi-thread 상에서 동작하는 객체를 생성하기 위한 함수.
     * MonobehaviourModule의 갱신 단계에서 호출된다.
     * */
    override fun lateinit(){
        transformHandle = erEngine.transformSoA.create(
            entity.location.x, entity.location.y,entity.location.z,
            0.0, 0.0, 0.0 );
        transformHandle.actor = this;
    }

    init {
        //Monobehaviour 등록
        this.submitEvent(EREntityMonobehavCreatedEvent())
        this.registerMonobehaviour(Stun())
        this.registerMonobehaviour(Burn())
        this.registerMonobehaviour(ToucheCount())
        this.registerMonobehaviour(ToucheEffect())
        this.registerMonobehaviour(Passive_Timer())
        this.registerMonobehaviour(LiDailinPassiveTimer())
        this.registerMonobehaviour(PassiveCount())
    }

    private var shootRay : Boolean = false;
    fun isShootingRay() : Boolean{
        val ret = shootRay;
        shootRay = false;
        return ret;
    }

    open fun shootRay(){
        shootRay = true;
    }

    /**
     * 방향벡터를 얻어온다.
     */
    fun getDirection(): Vector3 {
        val location = entity.location
        val radX = Math.toRadians(location.yaw.toDouble())
        val radY = Math.toRadians(location.pitch.toDouble())
        val xz = cos(radY)
        return this.geometryModule.vec3(-xz * sin(radX), -sin(radY), xz * cos(radX));
    }

    open var movementSpeed : Double
        get(){
            require(entity is Attributable)
            return entity.getAttribute(Attribute.MOVEMENT_SPEED)!!.baseValue
        }
        set(value) {
            require(entity is Attributable)
            val attrInst = entity.getAttribute(Attribute.MOVEMENT_SPEED)!!;
            dpEngine.appendCommand(SetBukkitAttributeBaseValue(attrInst, value))
        }

    /**
     * 위치벡터
     */
    open fun getPosition(): Vector3 {
        val location = entity.location
        return this.geometryModule.vec3(location.x, location.y, location.z)
    }

    open fun setPosition(pos : Vector3) {
        val x = geometryModule.x(pos);
        val y = geometryModule.y(pos);
        val z = geometryModule.z(pos);
        setPosition(x, y, z);
    }

    /**
     * 속도벡터
     */
    open fun getVelocity() : Vector3{
        val v = entity.velocity;
        return this.geometryModule.vec3(v.x, v.y, v.z);
    }

    open fun setVelocity(vec : Vector3){
        val x = geometryModule.x(vec);
        val y = geometryModule.y(vec);
        val z = geometryModule.z(vec);
        setVelocity(x, y, z);
    }

    open fun addVelocity(vec : Vector3){
        val x = geometryModule.x(vec);
        val y = geometryModule.y(vec);
        val z = geometryModule.z(vec);
        addVelocity(x, y, z);
    }

    /**
     * 물리 적용
     * */
    open fun setVelocity(x : Double, y : Double, z : Double){
        this.dpEngine.appendCommand(SetSpigotEntityVelocity(entity, x, y, z))
    }

    open fun addVelocity(x : Double, y : Double, z : Double){
        this.dpEngine.appendCommand(AddSpigotEntityVelocity(entity, x, y, z))
    }

    open fun setPosition(x : Double, y : Double, z : Double){
        this.dpEngine.appendCommand(SetSpigotEntityPosition(entity, x, y, z))
    }

    open fun addPosition(x : Double, y : Double, z : Double){
        this.dpEngine.appendCommand(AddSpigotEntityPosition(entity, x, y, z))
    }

    open fun applyBukkitVelocityOnMainThread(x: Double, y: Double, z: Double) {
        val velocity = Vector(x, y, z);
        entity.velocity = velocity;
    }

    open fun isNotTranslating(): Boolean {
        return erEngine.transformSoA.isNotTranslating(transformHandle);
    }


    /**
     * 피해를 주는 메소드, 무적시간에 영향 받음.
     * */
    val source = DamageSource.builder(DamageType.PLAYER_ATTACK).withDirectEntity(this.entity);
    open fun damage(amount : Double, attacker : EREntity){
        if(entity is LivingEntity){
            this.submitEvent(EREntityDamagedEvent(attacker))
            attacker.submitEvent(EREntityAttackEvent(attacker, this))
            source.withCausingEntity(attacker.entity)
            entity.damage(amount, source.build()); //이것도 특수한 SoA 함수로 뺄 것
        }
    }

    /**
     * 무적시간에 관계 없이 강제적으로 피해를 주는 메소드
     * */
    open fun damageForce(amount : Double, attacker : EREntity){
        if(entity is LivingEntity){
            entity.noDamageTicks = 0;
            this.submitEvent(EREntityDamagedEvent(attacker))
            attacker.submitEvent(EREntityAttackEvent(attacker, this))
            source.withCausingEntity(attacker.entity)
            entity.damage(amount, source.build()); //이것도 특수한 SoA 함수로 뺄 것
        }
    }


    /**
     * 피해를 주는 메소드, 무적시간에 영향 받음, MonobehaviourEvent를 전달하지 않음.
     * */
    open fun damageNotSendEvent(amount : Double, attacker : EREntity){
        if(entity is LivingEntity){
            entity.damage(amount, attacker.entity); //이것도 특수한 SoA 함수로 뺄 것
        }
    }


}

