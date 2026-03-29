package org.eternalreturn.ercharacter.character.isaac
import org.eternalreturn.ercharacter.character.isaac.event.PassiveCountEvent
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.dpengine.monobehaviour.MonobehaviourEvent
import org.bukkit.Sound
import org.bukkit.attribute.Attribute
import org.bukkit.damage.DamageType
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.eternalreturn.erentity.DPEntity
import org.eternalreturn.erplayer.DPlayer

/**
 * 이거는 EREntity에게 적용되는 Monobehaviour입니다.
 * 해당 Monobehaviour은 아이작에게 적용되어 있지 않고
 * EREntity에 적용되어 있습니다.
 * */
class PassiveCount : EREntityMonobehaviour<PassiveCountEvent>() {

    var count = 0;
    val durationTicks : Long = 100;
    var startTime : Long = 0;

    override fun start(event: PassiveCountEvent) {

        val player : DPlayer = event.player;
        val playerBukkit : Player = player.player
        val victim : DPEntity =  DPEntity

        if(count >= 2) {
            //playerBukkit.sendMessage("§f[아이작] 착취 발동");
            playerBukkit.playSound(event.player.location, Sound.ITEM_MACE_SMASH_GROUND_HEAVY, 1f, 1.5f)

            victim.damageForce(5.0, event.player, DamageType.PLAYER_ATTACK);
            val healAmount = 3.0
            val maxHealth = playerBukkit.getAttribute(Attribute.MAX_HEALTH)!!.value
            val finalHealth = kotlin.math.min(playerBukkit.health + healAmount, maxHealth)
            playerBukkit.health = finalHealth
            playerBukkit.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 20, 0, false, false))

            count = 0;
            stopMonobehav();
            return;
        }

        if(isNotEnd(startTime, durationTicks)){
            count ++;
            startTime = System.currentTimeMillis();
            //playerBukkit.sendMessage("카운트 : $count");
            playerBukkit.playSound(event.player.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 1f, 1f + (count * 0.2f))
            stopMonobehav();
            return;
        }
        else{
            count = 1;
            //playerBukkit.sendMessage("타임아웃 ! 카운트 : $count");
            playerBukkit.playSound(event.player.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 1f, 1f + (count * 0.2f))
            startTime = System.currentTimeMillis();
            stopMonobehav();
            return;
        }
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav();
    }


}