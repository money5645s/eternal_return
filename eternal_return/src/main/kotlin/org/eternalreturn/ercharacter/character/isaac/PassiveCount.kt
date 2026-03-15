package org.eternalreturn.ercharacter.character.isaac
import org.eternalreturn.ercharacter.character.isaac.event.PassiveCountEvent
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.attribute.Attribute
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

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

        val victim : LivingEntity =  this.getEREntity().entity as LivingEntity;

        //println("CurrentCount : $count ");
        if(count >= 2) {
            event.player.sendMessage("§f[아이작] 착취 발동");
            event.player.playSound(event.player.location, Sound.ITEM_MACE_SMASH_GROUND_HEAVY, 1f, 1.5f)

            victim.noDamageTicks = 0
            victim.damage(5.0, event.player);
            val healAmount = 3.0
            val maxHealth = event.player.getAttribute(Attribute.MAX_HEALTH)!!.value
            val finalHealth = Math.min(event.player.health + healAmount, maxHealth)
            event.player.health = finalHealth
            event.player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 20, 0, false, false))

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
            event.player.playSound(event.player.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 1f, 1f + (count * 0.2f))
            stopMonobehav();
            return;
        }
        else{
            count = 1;
            event.player.sendMessage("타임아웃 ! 카운트 : $count");
            event.player.playSound(event.player.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 1f, 1f + (count * 0.2f))
            startTime = System.currentTimeMillis();
//            this.getEREntity().submitEvent(ToucheEffectStartEvent(victim.location, durationTicks, count));
            stopMonobehav();
            return;
        }
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav();
    }


}