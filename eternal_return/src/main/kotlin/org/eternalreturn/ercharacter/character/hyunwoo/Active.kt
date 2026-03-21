package org.eternalreturn.ercharacter.character.hyunwoo

import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.util.Vector
import org.eternalreturn.eranimal.ERAnimal
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.events.EREntityStunEvent
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.eternalreturn.util.dpengine.geometry.Vector3
import kotlin.properties.Delegates


class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    var direction: Vector by Delegates.notNull();
    var hitEntities: HashMap<EREntity, Int> = HashMap<EREntity, Int>()
    private var isWallSlam = false
    private var isOnGround = false
    private var tick = 0

    override fun start(event: CharacterSwapHandEvent) {

        if(erCharacter.activeCooldown > 0 || erCharacter.activeLevel == 0){
            stopMonobehav();
            return;
        }

        if (player.location.add(0.0, -0.5, 0.0).block.type.isAir()) {
            player.playSound(player.location, Sound.ENTITY_VILLAGER_NO, 1f, 1f)
            isOnGround = true
            stopMonobehav();
            return
        }

        val hyunwoo = actor as Character_Hyunwoo
        this.direction = hyunwoo.location.direction;
        this.direction.y = 0.0;
        this.isWallSlam = false
        tick = 0
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        val attacker = actor as Character_Hyunwoo

        tick ++

        if (!isWallSlam && tick < 7){

            val velocity = attacker.getDirection()
            velocity.y(0.0)
            attacker.setVelocity(velocity);

            // 2. 주변 적 감지
            for (victim in erEngine.entityList) {
                if(victim === attacker) continue;
                val p = attacker.getPosition();
                val t = victim.getPosition();

                val distSqr = magnitudeSqr(p - t);
                val isInDistance = when(victim){ //우히히
                    is ERAnimal          -> distSqr <= 3.0 * 3.0;
                    else /*is ERPlayer*/ -> distSqr <= 0.8 * 0.8;
                }

                if(isInDistance){
                    hitEntities[victim] = 0;
                }
            }

            for (victim in hitEntities.keys) {
                if (hitEntities[victim] == 0) {
                    hitEntities[victim] = 1;
                    victim.damage(attacker.activeExtraDamageForEachLevel[attacker.activeLevel], attacker);
                }
                victim.setVelocity(velocity * 1.2);
            }

            // 4. 레이캐스팅 벽꿍 판정
            val startRay = player.location.add(0.0, 0.8, 0.0)
            val rayDir = direction.clone().normalize()

            // 1.2칸 앞의 블록 탐색 (거리가 너무 짧으면 인식이 안 됨)
            val result = player.world.rayTraceBlocks(startRay, rayDir, 1.2)

            if (result != null && result.hitBlock != null) {
                isWallSlam = true
                handleWallSlamSuccess(attacker)
            }
        }

        if (tick > 6){
            hitEntities.clear()
            erCharacter.activeCooldown = erCharacter.activeCoolForEachLevel[erCharacter.activeLevel];
            stopMonobehav()
        }

        if (isWallSlam){
            hitEntities.clear()
            erCharacter.activeCooldown = erCharacter.activeCoolForEachLevel[erCharacter.activeLevel];
            stopMonobehav()
        }
    }

    private fun handleWallSlamSuccess(player: ERPlayer) {

        val hyunwoo = erPlayer as Character_Hyunwoo

        for (victim in hitEntities.keys) {

            // 벽꿍 추가 피해 (10.0)
            victim.damage(hyunwoo.activeWallslamDamageForEachLevel[hyunwoo.activeLevel], player)

            victim.submitEvent(EREntityStunEvent(1 * 20))

            player.sendMessage("§b[현우] §f벽꿍 성공!")
            val p = player.entity as Player
            p.playSound(p.location, Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1f, 1f)

        }
    }
}