package org.eternalreturn.ercharacter.character.sissela

import net.kyori.adventure.sound.Sound
import org.bukkit.Particle
import org.bukkit.damage.DamageType
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent

class DamageToAll : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {

    var cooldown : Long = 0;
    val sisselaWitchMagicCastSound0 = Sound.sound(org.bukkit.Sound.ENTITY_ALLAY_HURT, Sound.Source.MASTER, 1f, 1f);
    val sisselaWitchMagicCastSound1 = Sound.sound(org.bukkit.Sound.ITEM_MACE_SMASH_AIR, Sound.Source.MASTER, 1f, 1f);


    override fun start(event: CharacterSwapHandEvent) {

        val sissela = actor as Sissela;

        if(sissela.burserkMode != 2){
            sissela.sendMessage("§c[!] §7 조건이 충족되지 않았습니다!");
            stopMonobehav();
            return;
        }

        if(erCharacter.activeCooldown > 0 || erCharacter.activeLevel == 0){
            stopMonobehav();
            return;
        }
        erCharacter.activeCooldown = erCharacter.activeCoolForEachLevel.get();

        val orangeLightBeamParticle = Particle.DUST.builder()
            .location(sissela.location)
            .color(org.bukkit.Color.ORANGE)
            .offset(0.5, 0.5, 0.5)
            .count(3)
            .extra(0.03)
            .receivers(32, true)

        val world = player.world
        player.playSound(sisselaWitchMagicCastSound0)
        player.playSound(sisselaWitchMagicCastSound1)

        for(victim in erEngine.entityList){

            if(victim === sissela)continue;
            val bukkitVictim = victim.entity;
            val distSqr = magnitudeSqr(victim.getPosition() - sissela.getPosition());
            if(distSqr <= 40.0 * 40.0){

                val loc = bukkitVictim.location;
                val x = loc.x; val y = loc.y; val z = loc.z;
                for(dy in 0 until 10){
                    orangeLightBeamParticle.location(world, x, y + dy, z).spawn();
                }

                victim.damageForce(sissela.activeExtraDamage.get(), sissela, DamageType.PLAYER_ATTACK);
                bukkitVictim.playSound(sisselaWitchMagicCastSound0, victim.entity)
                bukkitVictim.playSound(sisselaWitchMagicCastSound1, victim.entity)
            }
        }



    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav();
    }
}