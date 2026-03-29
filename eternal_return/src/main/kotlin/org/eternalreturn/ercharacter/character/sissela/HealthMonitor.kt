package org.eternalreturn.ercharacter.character.sissela

import net.kyori.adventure.sound.Sound
import org.bukkit.attribute.Attribute
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.erentity.EREntityMonobehavCreatedEvent
import org.dpengine.monobehaviour.MonobehaviourEvent

/**
 * 체력에 따라 로직을 진행하는 패시브
 * */
class HealthMonitor : ERCharacterMonobehaviour<EREntityMonobehavCreatedEvent>() {

    val growPowerSound = Sound.sound(org.bukkit.Sound.ENTITY_ALLAY_HURT, Sound.Source.MASTER, 1f, 1f);

    override fun start(event: EREntityMonobehavCreatedEvent) {

    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        if(erCharacter.passiveCooldown > 0 || erCharacter.passiveLevel == 0){
            //stopMonobehav(); 한번만 켜지는 거니까 이건 꺼야 함.
            return;
        }

        val sissela = actor as Sissela

        val maxHealth = player.getAttribute(Attribute.MAX_HEALTH)!!.baseValue
        val curHealth = player.health;

        val ratio = curHealth / maxHealth


        if(ratio <= 0.4 && sissela.burserkMode != 2){
            sissela.burserkMode = 2;
            player.playSound(growPowerSound, player)
            player.removePotionEffect(PotionEffectType.STRENGTH)
            player.addPotionEffect(PotionEffect(PotionEffectType.STRENGTH, PotionEffect.INFINITE_DURATION, 0, true))

        }else if( 0.4 < ratio && ratio <= 0.8 && sissela.burserkMode != 1){
            sissela.burserkMode = 1;
            player.playSound(growPowerSound, player)
            player.removePotionEffect(PotionEffectType.STRENGTH)
            player.addPotionEffect(PotionEffect(PotionEffectType.STRENGTH, PotionEffect.INFINITE_DURATION, 1, true))

        }else if(0.8 < ratio && sissela.burserkMode != 0){
            sissela.burserkMode = 0;
            player.removePotionEffect(PotionEffectType.STRENGTH)
        }



    }
}