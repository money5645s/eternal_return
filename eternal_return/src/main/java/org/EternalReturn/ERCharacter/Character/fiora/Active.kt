package org.EternalReturn.ERCharacter.Character.fiora

import org.EternalReturn.ERCharacter.ERCharacterMonobehaviour
import org.EternalReturn.ERCharacter.Event.CharacterSwapHandEvent
import org.EternalReturn.ERCharacter.Character.fiora.event.ERToucheCountEvent
import org.EternalReturn.ERCharacter.Character.fiora.Character_Fiora
import org.EternalReturn.System.PluginInstance
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    private var skillTimer = 0

    override fun start(event: CharacterSwapHandEvent) {
        val fiora = actor as Character_Fiora
        val cd = fiora.cooldown

        skillTimer = 0

        if (cd.isWaiting("Active")) {
            val remain = String.format("%.1f", cd.getLeft("Active"))
            getPlayer().sendMessage("§c[!] §7쿨타임 중입니다. (${remain}초)")
            return
        }

        skillTimer = 1
        getPlayer().sendMessage("§f[피오라] §b아따끄 꽁뽀제!")
        getPlayer().addPotionEffect(PotionEffect(PotionEffectType.RESISTANCE, 14, 2, false, false))
        // 쿨타임 등록
        fiora.cooldown.set("Active", fiora.ActiveCooldownSeconds)
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {

        if(skillTimer < 1){
            stopMonobehav()
            return
        }

        skillTimer++
        val player = getPlayer()

        // 디버깅 용도
//        player.sendMessage("${skillTimer}")

        // 1타 (4틱)
        if (skillTimer == 4) {
            handleHit(player, 3.0, false) // 1타는 자연스럽게 이벤트로 스택 쌓기
        }

        // 2타 (10틱)
        if (skillTimer >= 10) {
            handleHit(player, 4.0, true) // 2타는 무적 시간 때문에 강제로 스택 쌓기

            // 스킬 종료 처리
            stopMonobehav()
        }
    }

    // 공격 로직 통합
    private fun handleHit(player: Player, damage: Double, forceStack: Boolean) {
        player.playSound(player.location, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, if (forceStack) 1.4f else 1.0f)
        player.world.spawnParticle(Particle.SWEEP_ATTACK, player.eyeLocation.add(player.location.direction.multiply(1.5)), 1)

        for (entity in player.getNearbyEntities(2.5, 2.5, 2.5)) {
            if (entity is LivingEntity && entity != player) {

                val victim = PluginInstance.getEREngine().getEREntity(entity)
                if (victim == null) continue

                val toTarget = entity.location.toVector().subtract(player.location.toVector()).normalize()
                if (player.location.direction.dot(toTarget) > 0.7) {

                    entity.noDamageTicks = 0
                    entity.damage(damage, player)
                    entity.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 40, 100, false, true))

                    val victim = PluginInstance.getEREngine().getEREntity(entity);
                    if(victim == null)return;
                    victim.submitEvent(ERToucheCountEvent(player));
                }
            }
        }
    }
}