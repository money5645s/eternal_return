package org.eternalreturn.eranimal

import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import org.bukkit.Location
import org.bukkit.attribute.Attributable
import org.bukkit.attribute.Attribute
import org.bukkit.damage.DamageType
import org.bukkit.util.Vector
import org.eternalreturn.eranimal.animals.behavs.Battle
import org.eternalreturn.eranimal.animals.behavs.EnhanceStatByDay
import org.eternalreturn.eranimal.animals.behavs.Idle
import org.eternalreturn.eranimal.animals.events.IdleEvent
import org.eternalreturn.eranimal.animals.events.StatEnhanceEvent
import org.eternalreturn.eranimal.manager.ERAnimalManager
import org.eternalreturn.ercharacter.event.CharacterKillEvent
import org.eternalreturn.erentity.DPEntity
import org.eternalreturn.erentity.DPHitboxEntity
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erentity.events.EREntityDamagedEvent
import org.eternalreturn.erplayer.DPlayer
import org.eternalreturn.system.EREngine
import org.dpengine.command.*
import kotlin.math.max

/**
 * MonobehaviourActor역할을 하는 야생동물 클래스.
 */
abstract class DPAnimal(
    engine: EREngine,
    val aJEntity: ERAJEntity,
    obbHalfX: Double, obbHalfY: Double,
    obbHalfZ: Double, obbLocX: Double, obbLocY: Double, obbLocZ: Double
) : DPHitboxEntity(
    engine,
    aJEntity.rootEntity,
    obbHalfX, obbHalfY, obbHalfZ,
    obbLocX, obbLocY, obbLocZ
) {

    override val location : Location
        get(){
            return if(aJEntity.actor == null){
                aJEntity.rootEntity.location
            }else{
                aJEntity.actor!!.location;
            }
        }

    /**
     * 야생동물 스킬 쿨다운
     */
    var cooldownSeconds: Long = 0

    var level = 1;
    var maxLevel: Int

    var hp0 : Double;
    var hp : Double;
    var hpMax : Double;
    var hpInc : Double;

    var damage0 : Double;
    var damage : Double;
    var damageInc : Double;

    var attackTicks : IntArray;


    init {
        registerMonobehaviour(EnhanceStatByDay())
        registerMonobehaviour(Idle())
        registerMonobehaviour(Battle())
        this.submitEvent(IdleEvent())
        this.submitEvent(StatEnhanceEvent())


        val stat = ERAnimalManager.Companion.animalMap[aJEntity.name]!!
        this.maxLevel = stat.maxLevel;

        this.hp0 = stat.hp0;
        this.hp = stat.hpMax;
        this.hpMax = stat.hpMax;
        this.hpInc = stat.hpInc;

        this.damage0 = stat.damage0;
        this.damage = stat.damage;
        this.damageInc = stat.damageInc;
        this.attackTicks = stat.attackTicks;
        updateHPBar()
    }

    /**
     * 해당 객체가 참조하고 있는 AJEntity의 HPBar을 수정한다.
     *
     * isShown이 true여야 수정되며, true가 아니면 false를 반환한다.
     * */
    fun updateHPBar() : Boolean{
        if(this.aJEntity.isShown){
            val str = Component.text(String.format("LV : %d | HP : %d\n\n\n\n", this.level, max(1, this.hp.toInt())))
            str.font(Key.key("haesu/8"));
            this.aJEntity.setDebugDisplay(str);
        }
        return this.aJEntity.isShown;
    }

    private fun isActorNotValid() : Boolean{
        return (aJEntity.actor == null) || !aJEntity.actor!!.isValid
    }

    override var movementSpeed : Double
        get(){
            if(isActorNotValid()) return 0.0;
            val actor = aJEntity.actor;
            require(actor is Attributable)
            return actor.getAttribute(Attribute.MOVEMENT_SPEED)!!.baseValue
        }
        set(value) {
            if(isActorNotValid()) return;
            val actor = aJEntity.actor;
            require(actor is Attributable)
            val attrInst = actor.getAttribute(Attribute.MOVEMENT_SPEED)!!;
            dpEngine.appendCommand(SetBukkitAttributeBaseValue(attrInst, value))
        }

    /**
     * ERAJEntity의 속도를 변경한다. 그러나 Actor가 없다면 아무런 작용도 하지 않는다.
     * */
    override fun setVelocity(x : Double, y : Double, z : Double){
        if(isActorNotValid()) return;
        this.dpEngine.appendCommand(SetSpigotEntityVelocity(aJEntity.actor!!, x, y, z))
    }

    /**
     * ERAJEntity의 속도를 변경한다. 그러나 Actor가 없다면 아무런 작용도 하지 않는다.
     * */
    override fun addVelocity(x : Double, y : Double, z : Double){
        if(isActorNotValid()) return;
        this.dpEngine.appendCommand(AddSpigotEntityVelocity(aJEntity.actor!!, x, y, z))
    }

    /**
     * ERAJEntity의 위치를 변경한다. 그러나 Actor가 없다면 아무런 작용도 하지 않는다.
     * */
    override fun setPosition(x : Double, y : Double, z : Double){
        if(!isActorNotValid())this.dpEngine.appendCommand(SetSpigotEntityPosition(aJEntity.actor!!, x, y, z))
        else if(this.aJEntity.rootEntity != null)this.dpEngine.appendCommand(SetSpigotEntityPosition(aJEntity.rootEntity, x, y, z))
    }

    /**
     * ERAJEntity의 속도를 변경한다. 그러나 Actor가 없다면 아무런 작용도 하지 않는다.
     * */
    override fun addPosition(x : Double, y : Double, z : Double){
        if(!isActorNotValid())this.dpEngine.appendCommand(AddSpigotEntityPosition(aJEntity.actor!!, x, y, z))
        else if(this.aJEntity.rootEntity != null)this.dpEngine.appendCommand(AddSpigotEntityPosition(aJEntity.rootEntity, x, y, z))
    }

    override fun applyBukkitVelocityOnMainThread(x: Double, y: Double, z: Double) {
        aJEntity.actor!!.velocity = Vector(x, y, z);
    }

    val hurtSound = Sound.sound().type(org.bukkit.Sound.ENTITY_GENERIC_HURT);
    private fun __damage(amount: Double, attacker: DPEntity){
        this.hp -= amount;
        if(this.hp <= 0){
            if(attacker is DPlayer && !this.isDead){
                attacker.submitEvent(CharacterKillEvent(this))
                this.isDead = true;
                dpEngine.appendCommand(AddTagToSpigotEntity(attacker.entity, "kill_" + this.aJEntity.name))
            }
        }
        attacker.entity.playSound(hurtSound.build(), entity);
        updateHPBar();
    }

    var invulnerableTime : Long = 0
    override fun damage(amount: Double, attacker: DPEntity, damageType : DamageType) {
        val currentTime = System.currentTimeMillis();
        if(invulnerableTime < currentTime){
            invulnerableTime = currentTime + (1000 shr 2);
            this.submitEvent(EREntityDamagedEvent(attacker))
            attacker.submitEvent(EREntityAttackEvent(attacker, this))
            __damage(amount, attacker);
        }
    }

    override fun damageForce(amount : Double, attacker : DPEntity, damageType : DamageType){
        this.submitEvent(EREntityDamagedEvent(attacker))
        attacker.submitEvent(EREntityAttackEvent(attacker, this))
        __damage(amount, attacker);
    }

    override fun damageNotSendEvent(amount : Double, attacker : DPEntity){
        val currentTime = System.currentTimeMillis();
        if(invulnerableTime < currentTime){
            invulnerableTime = currentTime  + (1000 shr 2);
            __damage(amount, attacker);
        }
    }

    /**
     * 피해를 주는 메소드, 무적시간에 영향 받지 않음, MonobehaviourEvent를 전달하지 않음.
     * */
    override fun damageNotSendEventPierce(amount : Double, attacker : DPEntity){
        invulnerableTime = 0;
        __damage(amount, attacker); //이것도 특수한 SoA 함수로 뺄 것
    }

    private var isDead = false;
    fun isDead() : Boolean{
        return isDead;
    }
    fun setDead(b : Boolean){
        isDead = b;
    }

    private var returnToPoint = false;
    fun setReturn(bool: Boolean) {
        returnToPoint = true;
    }
    fun haveToReturnToPoint() : Boolean{
        return returnToPoint;
    }

    fun setNotBeShown() {
        val spwloc = aJEntity.spawnLocation.clone();
        if(aJEntity.actor != null){
            aJEntity.actor!!.removePassenger(aJEntity.rootEntity);
            aJEntity.actor!!.remove();
            spwloc.y += 2;
            aJEntity.rootEntity.teleport(spwloc)
        }
        aJEntity.setNotBeShown();
    }

    val isShown: Boolean
        get() = this.aJEntity.isShown
}
