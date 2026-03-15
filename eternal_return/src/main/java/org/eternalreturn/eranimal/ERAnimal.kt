package org.eternalreturn.eranimal

import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import org.bukkit.Location
import org.bukkit.attribute.Attributable
import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import org.bukkit.util.Vector
import org.eternalreturn.eranimal.animals.behavs.Battle
import org.eternalreturn.eranimal.animals.behavs.EnhanceStatByDay
import org.eternalreturn.eranimal.animals.behavs.Idle
import org.eternalreturn.eranimal.animals.events.IdleEvent
import org.eternalreturn.eranimal.animals.events.StatEnhanceEvent
import org.eternalreturn.ercharacter.event.CharacterKillEvent
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.ERHitboxEntity
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erentity.events.EREntityDamagedEvent
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.command.*

/**
 * MonobehaviourActor역할을 하는 야생동물 클래스.
 */
abstract class ERAnimal(
    engine: EREngine,
    val aJEntity: ERAJEntity,
    obbHalfX: Double, obbHalfY: Double,
    obbHalfZ: Double, obbLocX: Double, obbLocY: Double, obbLocZ: Double
) : ERHitboxEntity(
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
    abstract val maxLevel: Int

    abstract var hp0 : Double;
    abstract var hp : Double;
    abstract var hpInc : Double;

    abstract var damage0 : Double;
    abstract var damage : Double;
    abstract var damageInc : Double;

    abstract val attackTicks : Array<Int>;

    init {
        registerMonobehaviour(EnhanceStatByDay())
        registerMonobehaviour(Idle())
        registerMonobehaviour(Battle())
        this.submitEvent(IdleEvent())
        this.submitEvent(StatEnhanceEvent())
    }

    fun updateHPBar(){
        val str = Component.text(String.format("LV : %d | HP : %d\n\n\n\n", this.level, kotlin.math.max(1, this.hp.toInt())))
        str.font(Key.key("haesu/8"));
        this.aJEntity.setDebugDisplay(str);
    }

    private fun isActorNotValid() : Boolean{
        return (aJEntity.actor == null) || !aJEntity.actor.isValid
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
        this.dpEngine.appendCommand(SetSpigotEntityVelocity(aJEntity.actor, x, y, z))
    }

    /**
     * ERAJEntity의 속도를 변경한다. 그러나 Actor가 없다면 아무런 작용도 하지 않는다.
     * */
    override fun addVelocity(x : Double, y : Double, z : Double){
        if(isActorNotValid()) return;
        this.dpEngine.appendCommand(AddSpigotEntityVelocity(aJEntity.actor, x, y, z))
    }

    /**
     * ERAJEntity의 속도를 변경한다. 그러나 Actor가 없다면 아무런 작용도 하지 않는다.
     * */
    override fun setPosition(x : Double, y : Double, z : Double){
        if(isActorNotValid()) return;
        this.dpEngine.appendCommand(SetSpigotEntityPosition(aJEntity.actor, x, y, z))
    }

    /**
     * ERAJEntity의 속도를 변경한다. 그러나 Actor가 없다면 아무런 작용도 하지 않는다.
     * */
    override fun addPosition(x : Double, y : Double, z : Double){
        if(isActorNotValid()) return;
        this.dpEngine.appendCommand(AddSpigotEntityPosition(aJEntity.actor, x, y, z))
    }

    override fun applyBukkitVelocityOnMainThread(x: Double, y: Double, z: Double) {
        aJEntity.actor.velocity = Vector(x, y, z);
    }

    override fun remove() {
        super.remove()
        aJEntity.remove();
    }

    private fun __damage(amount: Double, attacker: EREntity){
        this.hp -= amount;
        if(this.hp <= 0){
            if(attacker is ERPlayer){
                attacker.submitEvent(CharacterKillEvent(this))
            }
            dpEngine.appendCommand(AddTagToSpigotEntity(attacker.entity, "kill_" + this.aJEntity.name))
        }

        val sound = Sound.sound().type(org.bukkit.Sound.ENTITY_GENERIC_HURT).build()
        updateHPBar()
        attacker.entity.playSound(sound);
    }

    var invulnerableTime : Long = 0
    override fun damage(amount: Double, attacker: EREntity) {
        val currentTime = System.currentTimeMillis();
        if(invulnerableTime < currentTime){
            invulnerableTime = currentTime + (1000 shr 2);
            this.submitEvent(EREntityDamagedEvent(attacker))
            attacker.submitEvent(EREntityAttackEvent(attacker, this))
            __damage(amount, attacker);
        }
    }

    override fun damageForce(amount : Double, attacker : EREntity){
        invulnerableTime = 0;
        damage(amount, attacker);
    }

    override fun damageNotSendEvent(amount : Double, attacker : EREntity){
        val currentTime = System.currentTimeMillis();
        if(invulnerableTime < currentTime){
            invulnerableTime = currentTime  + (1000 shr 2);
            __damage(amount, attacker);
        }
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


    val isShown: Boolean
        get() = this.aJEntity.isShown
}
