package org.eternalreturn.ercharacter.character.adriana

import org.bukkit.Location
import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.CooldownContext
import org.eternalreturn.ercharacter.ERCharacterSkillMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erentity.events.EREntityBurnEvent
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent




class Character_Adriana(erEngine : EREngine, player: Player) : ERPlayer(player, erEngine) {

    override val activeCoolForEachLevel: LongArray = longArrayOf(30 * 20, 28 * 20, 26 * 20, 24 * 20, 20 * 20) //get from json
    override val passiveCoolForEachLevel: LongArray = longArrayOf(5 * 20, 4 * 20, 3 * 20, 2 * 20, 100) //get from json

    val fireFootprintDamageList : DoubleArray = doubleArrayOf(1.0, 2.0, 2.0, 3.0, 3.0);
    val fireFootprintDamage : Double get() = fireFootprintDamageList[this.activeLevel - 1];
    val fireDurationList : LongArray = longArrayOf(4 * 20, 5 * 20, 6 * 20, 7 * 20, 8 * 20)
    val fireDuration : Long get() = fireDurationList[this.passiveLevel - 1];

    val activeCooldownCtx = CooldownContext(activeCoolForEachLevel, this::activeLevel);
    val passiveCooldownCtx = CooldownContext(passiveCoolForEachLevel, this::passiveLevel);

    init {
        this.ActiveCooldownSeconds = 5
        this.PassiveCooldownSeconds = 5
        this.registerMonobehaviour(Active(activeCooldownCtx))
        this.registerMonobehaviour(Passive(passiveCooldownCtx))
    }

    override val name: String
        get() = "adriana"
}

class Active(activeCooldownCtx : CooldownContext) :
    ERCharacterSkillMonobehaviour<CharacterSwapHandEvent, Character_Adriana>(activeCooldownCtx, durationTicks = 5, "ACD") {
    override fun skillStart(event: CharacterSwapHandEvent) {
        var dir = player.getDirection();

        dir = vec3(dir.x(), 0.0, dir.z());
        dir = dir * (1/magnitude(dir))

        // 4. 속도 곱하기 (1.5 정도가 약 5칸 돌진에 적당함)
        player.setVelocity(dir * 1.5)
    }

    override fun skillUpdate(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val pos = player.getPosition();

        val burningGroundEntity = BurningGroundVirtualEntity(dpEngine, player,
            Location(player.player.world, pos.x(), pos.y(), pos.z()),
            System.currentTimeMillis(), player.fireFootprintDamage, player.fireDuration)

        dpEngine.monobehaviourModule.register(burningGroundEntity);
        burningGroundEntity.submitEvent(LetsBurnEvent()); //태우기 실행
    }

}

class Passive(passiveCooldownCtx : CooldownContext) :
    ERCharacterSkillMonobehaviour<EREntityAttackEvent, Character_Adriana>(passiveCooldownCtx, durationTicks = 0, "PCD") {
    override fun skillStart(event: EREntityAttackEvent) {
        event.victim.submitEvent(EREntityBurnEvent(player, 100))
    }

    override fun skillUpdate(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
    }
}

