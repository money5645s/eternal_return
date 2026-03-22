package org.eternalreturn.ercharacter.character.fiora

import org.bukkit.Sound
import org.bukkit.attribute.Attribute
import org.bukkit.damage.DamageType
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.erentity.events.EREntityEvent
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent

class ERToucheCountEvent(val player: ERPlayer, val damage : Double) : EREntityEvent

/**
 * 이거는 EREntity에게 적용되는 Monobehaviour입니다.
 * 해당 Monobehaviour은 피오라에게 적용되어 있지 않고
 * EREntity에 적용되어 있습니다.
 * */
class ToucheCount : EREntityMonobehaviour<ERToucheCountEvent>() {

    var count = 0;
    val durationTicks : Long = 100;
    var startTime : Long = 0;

    override fun start(event: ERToucheCountEvent) {

        val victim = this.actor as EREntity
        /**
         * CraftPlayer
         * */
        val player = event.player.player;

        //println("CurrentCount : $count ");
        if(count >= 4) {
            //player.sendMessage("§f[피오라] §b§l뚜셰! §f적중");
            player.playSound(player.location, Sound.ITEM_MACE_SMASH_GROUND_HEAVY, 1f, 1.5f)

            victim.damage(event.damage, event.player, DamageType.PLAYER_ATTACK);
            val healAmount = 3.0
            val maxHealth = player.getAttribute(Attribute.MAX_HEALTH)?.value ?: 20.0
            val finalHealth = kotlin.math.min(player.health + healAmount, maxHealth)
            player.health = finalHealth

            count = 0;
//            this.getEREntity().submitEvent(ToucheEffectStartEvent(victim.location, durationTicks, count));
            stopMonobehav();
            return;
        }

        if(isNotEnd(startTime, durationTicks)){
            count ++;
            startTime = System.currentTimeMillis();
            //player.sendMessage("카운트 : $count");
            erEntity.submitEvent(ToucheEffectStartEvent(durationTicks, count));
            player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 1f, 1f + (count * 0.2f))
            stopMonobehav();
            return;
        }
        else{
            count = 1;
            //player.sendMessage("카운트 : $count");
            player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 1f, 1f + (count * 0.2f))
            startTime = System.currentTimeMillis();
            erEntity.submitEvent(ToucheEffectStartEvent(durationTicks, count));
            stopMonobehav();
            return;
        }
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav();
    }


}