package org.eternalreturn.ercharacter.character.jan

import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.eternalreturn.eranimal.ERAnimal
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.character.jan.event.JanActiveTimerEvent
import org.eternalreturn.ercharacter.character.jan.event.JanPassiveTimerEvent
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.erentity.events.EREntityStunEvent
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine
import org.eternalreturn.system.PluginInstance
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import kotlin.compareTo
import kotlin.io.normalize
import kotlin.text.set

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    override fun start(event: CharacterSwapHandEvent) {
        val jan = actor as Character_Jan
        val bukkitPlayer = jan.player;
        val loc = bukkitPlayer.location;
        val world = loc.world!!;
        val cd = jan.cooldown

        if (cd.isWaiting("Active")) {
            val remain = String.format("%.1f", cd.getLeft("Active"))
            jan.sendMessage("§c[!] §7쿨타임 중입니다. (${remain}초)")
            return
        }

        if (jan.Stack >= 5){
            jan.StackSkill = true
            jan.Stack = 0
        }

        var dir = jan.getDirection();
        dir.y(0.0);
        dir = dir * (1/magnitude(dir));
        val centerloc = dir * 2.0 + jan.getPosition()

        val dustOptions = Particle.DustOptions(Color.fromRGB(63, 255, 15), 1.0f)
        world.spawnParticle(
            Particle.DUST,
            Location(world, centerloc.x(), centerloc.y(), centerloc.z()),
            100,
            0.5,
            0.0,
            0.5,
            0.0,
            dustOptions
        )

        if(!jan.StackSkill) {
            player.playSound(player.location, Sound.ENTITY_PLAYER_BIG_FALL, 1f, 0.6f)
            player.playSound(player.location, Sound.BLOCK_LANTERN_PLACE, 1f, 1f)
        }
        if(jan.StackSkill) {
            player.playSound(player.location, Sound.ENTITY_PLAYER_BIG_FALL, 1f, 1f)
            player.playSound(player.location, Sound.BLOCK_LANTERN_BREAK, 1f, 1f)
        }

        // 2. 주변 엔티티 감지 (중심점에서 사방 1.0칸 = 가로 2칸, 세로 2칸 범위)
        // getNearbyEntities(x, y, z)는 중심으로부터의 '반지름' 개념이므로 1.0을 넣어야 2x2가 됩니다.
        for (victim in erEngine.entityList) {
            if(victim === jan)continue;

            if(magnitudeSqr(victim.getPosition() - jan.getPosition()) <= 1.0 * 1.0){
                jan.Stack ++
                if (jan.StackSkill && jan.SkillReuse == 0) jan.Stack --

                jan.submitEvent(JanPassiveTimerEvent())
                jan.sendMessage("현재 스택 ${jan.Stack}/5")

                // 4. 공격 적용
                victim.damageForce(0.0, jan);
                val bukkitEntity = when(victim){
                    is ERPlayer -> victim.player
                    is ERAnimal -> victim.aJEntity.actor
                    else -> victim.entity
                };

                if(bukkitEntity is LivingEntity){
                    bukkitEntity.addPotionEffect(PotionEffect(PotionEffectType.SLOWNESS, 20, 1, false, true))
                }

                if(jan.SkillReuse == 1){
                    victim.submitEvent(EREntityStunEvent(1 * 20)) //2초
                }
            }

        }

        if (!jan.StackSkill){
            jan.cooldown.set("Active", jan.ActiveCooldownSeconds)
        }

        if (jan.StackSkill){
            if (jan.SkillReuse == 0) {
                player.sendMessage("스킬")
                this.getEREntity().submitEvent(JanActiveTimerEvent())
                jan.SkillReuse++
            } else if (jan.SkillReuse == 1) {
                player.sendMessage("재사용")
                jan.SkillReuse = 0
                jan.StackSkill = false
                jan.cooldown.set("Active", jan.ActiveCooldownSeconds)
            }
        }
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        stopMonobehav()
    }
}