package org.EternalReturn.ERCharacter.character.adriana.monobehavs

import org.EternalReturn.ERCharacter.ERCharacterMonobehaviour
import org.EternalReturn.ERCharacter.event.CharacterAttackEvent
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.entity.Entity
import java.util.*

class LitFireOnAttack : ERCharacterMonobehaviour<CharacterAttackEvent>() {
    public override fun start(event: CharacterAttackEvent) {
        Objects.requireNonNull<Entity>(event.victim!!.entity).setFireTicks(100)
    }

    public override fun update(event: MutableCollection<MonobehaviourEvent>) {
    }
}
