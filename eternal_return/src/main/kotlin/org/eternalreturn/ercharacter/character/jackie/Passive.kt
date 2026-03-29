package org.eternalreturn.ercharacter.character.jackie

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterKillEvent
import org.dpengine.monobehaviour.MonobehaviourEvent
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Passive : ERCharacterMonobehaviour<CharacterKillEvent>() {

    var endTimeMillis : Long = 0;

    override fun start(event: CharacterKillEvent) {

        if(erCharacter.passiveCooldown > 0 || erCharacter.passiveLevel == 0){
            stopMonobehav();
            return;
        }
        endTimeMillis = System.currentTimeMillis() + 100 * 50;

    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        if(endTimeMillis >= System.currentTimeMillis()){
            val player = erPlayer.player
            val loc : Location = player.location;
            val world = player.world;
            loc.y = loc.y + 2.5;
            world.spawnParticle(Particle.FLAME, loc, 3, 0.2, 0.1, 0.2, 0.02)
            player.addPotionEffect(PotionEffect(PotionEffectType.STRENGTH, 20 * 20, 0, false, true))
        }else{
            stopMonobehav();
        }
    }
}