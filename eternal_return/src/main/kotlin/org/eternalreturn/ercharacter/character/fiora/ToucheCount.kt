package org.eternalreturn.ercharacter.character.fiora

import org.eternalreturn.ercharacter.character.fiora.event.ERToucheCountEvent
import org.eternalreturn.ercharacter.character.fiora.event.ToucheEffectStartEvent
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.eternalreturn.erentity.EREntity

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

        val victim : EREntity = this.getEREntity();
        val bukkitPlayer : Player = event.player.entity as Player;

        println("CurrentCount : $count ");
        if(count >= 4) {
            bukkitPlayer.sendMessage("§f[피오라] §b§l뚜셰! §f적중");
            bukkitPlayer.playSound(bukkitPlayer.location, Sound.ITEM_MACE_SMASH_GROUND_HEAVY, 1f, 1.5f)

            victim.damage(20.0, event.player);
            val healAmount = 3.0
            val maxHealth = bukkitPlayer.getAttribute(Attribute.MAX_HEALTH)?.value ?: 20.0
            val finalHealth = Math.min(bukkitPlayer.health + healAmount, maxHealth)
            bukkitPlayer.health = finalHealth

            count = 0;
//            this.getEREntity().submitEvent(ToucheEffectStartEvent(victim.location, durationTicks, count));
            stopMonobehav();
            return;
        }

        if(isNotEnd(startTime, durationTicks)){
            count ++;
            startTime = System.currentTimeMillis();
            event.player.sendMessage("카운트 : $count");
//            this.getEREntity().submitEvent(ToucheEffectStartEvent(victim.location, durationTicks, count));
            bukkitPlayer.playSound(bukkitPlayer.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 1f, 1f + (count * 0.2f))
            stopMonobehav();
            return;
        }
        else{
            count = 1;
            event.player.sendMessage("카운트 : $count");
            bukkitPlayer.playSound(bukkitPlayer.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 1f, 1f + (count * 0.2f))
            startTime = System.currentTimeMillis();
//            this.getEREntity().submitEvent(ToucheEffectStartEvent(victim.location, durationTicks, count));
            stopMonobehav();
            return;
        }
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {
        stopMonobehav();
    }


}