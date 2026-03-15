package org.eternalreturn.eranimal

import net.kyori.adventure.sound.Sound
import org.bukkit.Location
import org.bukkit.util.Vector
import org.eternalreturn.eranimal.animals.behavs.Battle
import org.eternalreturn.eranimal.animals.behavs.EnhanceStatByDay
import org.eternalreturn.eranimal.animals.behavs.Idle
import org.eternalreturn.eranimal.animals.events.IdleEvent
import org.eternalreturn.eranimal.animals.events.StatEnhanceEvent
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.ERHitboxEntity
import org.eternalreturn.erentity.events.EREntityAttackEvent
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
        this.aJEntity.setDebugDisplay("LV : ${this.level} | HP : ${this.hp}\n\n\n\n")
    }

    private fun isActorNotValid() : Boolean{
        return (aJEntity.actor == null) || !aJEntity.actor.isValid
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

    override fun damage(amount: Double, attacker: EREntity) {

        var damage = amount;

        this.hp -= amount;
        attacker.submitEvent(EREntityAttackEvent(attacker, this))
        if(this.hp <= 0){
            dpEngine.appendCommand(AddTagToSpigotEntity(attacker.entity, "kill_" + this.aJEntity.name))
        }

        val sound = Sound.sound().type(org.bukkit.Sound.ENTITY_GENERIC_HURT).build()
        aJEntity.setDebugDisplay("LV : ${this.level} | HP : ${this.hp}\n\n\n\n")
        attacker.entity.playSound(sound);
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
