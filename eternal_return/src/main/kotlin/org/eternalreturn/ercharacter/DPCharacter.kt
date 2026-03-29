package org.eternalreturn.ercharacter

import org.bukkit.entity.Entity
import org.eternalreturn.ercharacter.datastructure.CoolTableSeconds
import org.eternalreturn.ercharacter.event.CharacterRunTimerEvent
import org.eternalreturn.ercharacter.globalmonobehav.ERCharacterUpdate
import org.eternalreturn.ercharacter.globalmonobehav.ParabolicFly
import org.eternalreturn.ercharacter.globalmonobehav.ShootProjectile
import org.eternalreturn.erentity.DPHitboxEntity
import org.eternalreturn.system.EREngine



abstract class DPCharacter(
    player : Entity,
    erEngine: EREngine,
) : DPHitboxEntity(erEngine, player, 0.5, 1.0, 0.5, 0.0, 1.0, 0.0) {

    @JvmField
    var ActiveCooldownSeconds: Long = 0

    @JvmField
    var PassiveCooldownSeconds: Long = 0

    val cooldown = SkillCooldown();

    var activeLevel : Int = -1;
    var activeCooldown: Long = -1;
    abstract val activeCoolForEachLevel : CoolTableSeconds;

    var passiveCooldown: Long = -1
    var passiveLevel : Int = -1;
    abstract val passiveCoolForEachLevel : CoolTableSeconds;

    init {

        registerMonobehaviour(ERCharacterUpdate())
        registerMonobehaviour(ParabolicFly())
        registerMonobehaviour(ShootProjectile())

        this.submitEvent(CharacterRunTimerEvent());
    }

    abstract val name: String

}
