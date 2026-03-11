package org.eternalreturn.eranimal

import net.kyori.adventure.sound.Sound
import org.bukkit.util.Vector
import org.eternalreturn.eranimal.animals.behavs.Battle
import org.eternalreturn.eranimal.animals.behavs.Idle
import org.eternalreturn.eranimal.animals.events.IdleEvent
import org.eternalreturn.ercharacter.event.CharacterAttackEvent
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.ERHitboxEntity
import org.eternalreturn.system.EREngine

/**
 * MonobehaviourActor역할을 하는 야생동물 클래스.
 */
abstract class ERAnimal(
    engine: EREngine,
    var aJEntity: ERAJEntity,
    obbHalfX: Double, obbHalfY: Double,
    obbHalfZ: Double, obbLocX: Double, obbLocY: Double, obbLocZ: Double
) : ERHitboxEntity(
    engine,
    aJEntity.rootEntity,
    obbHalfX, obbHalfY, obbHalfZ,
    obbLocX, obbLocY, obbLocZ
) {
    /**
     * 야생동물 스킬 쿨다운
     */
    var cooldownSeconds: Long = 0

    abstract var hp : Double;
    abstract var damage : Double;
    abstract val attackTicks : Array<Int>;

    init {
        registerMonobehaviour(Idle())
        registerMonobehaviour(Battle())
        this.aJEntity.setDebugDisplay("T" + transformHandle.entityID + " | O" + obbHandle.entityID + "\n\n\n\n")
        this.submitEvent(IdleEvent())
    }

    private fun isActorNotValid() : Boolean{
        return (aJEntity.actor == null) || !aJEntity.actor.isValid
    }

    /**
     * ERAJEntity의 속도를 변경한다. 그러나 Actor가 없다면 아무런 작용도 하지 않는다.
     * */
    override fun setVelocity(x : Double, y : Double, z : Double){
        if(isActorNotValid()) return;
        erEngine.transformSoA.setVelocity(transformHandle, x, y, z);
    }

    /**
     * ERAJEntity의 속도를 변경한다. 그러나 Actor가 없다면 아무런 작용도 하지 않는다.
     * */
    override fun addVelocity(x : Double, y : Double, z : Double){
        if(isActorNotValid()) return;
        erEngine.transformSoA.addVelocity(transformHandle, x, y, z);
    }

    /**
     * ERAJEntity의 속도를 변경한다. 그러나 Actor가 없다면 아무런 작용도 하지 않는다.
     * */
    override fun setPosition(x : Double, y : Double, z : Double){
        if(isActorNotValid()) return;
        erEngine.transformSoA.setPosition(transformHandle, x, y, z);
    }

    /**
     * ERAJEntity의 속도를 변경한다. 그러나 Actor가 없다면 아무런 작용도 하지 않는다.
     * */
    override fun addPosition(x : Double, y : Double, z : Double){
        if(isActorNotValid()) return;
        erEngine.transformSoA.addPosition(transformHandle, x, y, z);
    }

    override fun applyBukkitVelocityOnMainThread(x: Double, y: Double, z: Double) {
        aJEntity.actor.velocity = Vector(x, y, z);
    }

    override fun remove() {
        super.remove()
        aJEntity.remove();
    }

    override fun damage(amount: Double, attacker: EREntity) {
        this.hp -= amount;
        attacker.submitEvent(CharacterAttackEvent(attacker, this))
        val sound = Sound.sound().type(org.bukkit.Sound.ENTITY_GENERIC_HURT).build()
        aJEntity.setDebugDisplay("HP : ${this.hp}\n\n\n\n")
        attacker.entity.playSound(sound);
    }

    val isShown: Boolean
        get() = this.aJEntity.isShown
}
