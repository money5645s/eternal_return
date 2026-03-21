package org.eternalreturn.ercharacter.character.hart

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.util.Vector
import org.eternalreturn.ercharacter.character.hart.event.HartActiveEvent
import org.eternalreturn.ercharacter.event.CooldownEvent

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {

    private var skillTicks = 0;
    override fun start(event: CharacterSwapHandEvent) {

        if(erCharacter.activeCooldown > 0 || erCharacter.activeLevel == 0){
            stopMonobehav();
            return;
        }

        val velocity = erCharacter.getDirection() * 2.0;
        velocity.y(0.0)
        erCharacter.setVelocity(velocity);
        skillTicks = 100;
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        if(skillTicks > 0){
            if((100 - 1) >= skillTicks && gotSubscribedEvent){ //다음 틱부터 사용 가능
                val velocity = erCharacter.getDirection() * 2.0;
                velocity.y(0.0)
                erCharacter.setVelocity(velocity);
                erCharacter.activeCooldown = erCharacter.activeCoolForEachLevel[erCharacter.activeLevel] * 20;
                stopMonobehav();
                return;
            }
            skillTicks--;
        }



    }
}