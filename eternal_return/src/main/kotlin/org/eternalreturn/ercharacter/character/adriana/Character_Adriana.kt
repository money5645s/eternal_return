package org.eternalreturn.ercharacter.character.adriana

import org.bukkit.Location
import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.CooldownContext
import org.eternalreturn.ercharacter.ERCharacterSkillMonobehaviour
import org.eternalreturn.ercharacter.datastructure.CoolTableSeconds
import org.eternalreturn.ercharacter.datastructure.DamageTable
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.erentity.events.EREntityAttackEvent
import org.eternalreturn.erentity.events.EREntityBurnEvent
import org.eternalreturn.erplayer.DPlayer
import org.eternalreturn.system.EREngine
import org.dpengine.monobehaviour.MonobehaviourEvent




class Character_Adriana(erEngine : EREngine, player: Player) : DPlayer(player, erEngine) {

    override val activeCoolForEachLevel = CoolTableSeconds( this::activeLevel, 30, 28, 26, 24, 20)
    override val passiveCoolForEachLevel = CoolTableSeconds( this::passiveLevel, 5, 4, 3, 2, 1)

    val fireFootprintDamage = DamageTable(this::activeLevel, 1.0, 2.0, 2.0, 3.0, 3.0, 4.0);
    val fireDuration = CoolTableSeconds(this::activeLevel, 4, 5, 6, 7, 8, 9);

    val activeCooldownCtx = CooldownContext(activeCoolForEachLevel);
    val passiveCooldownCtx = CooldownContext(passiveCoolForEachLevel);

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
            System.currentTimeMillis(), player.fireFootprintDamage.get(), player.fireDuration.get())

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

