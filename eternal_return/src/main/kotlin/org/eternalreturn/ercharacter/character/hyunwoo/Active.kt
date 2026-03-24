package org.eternalreturn.ercharacter.character.hyunwoo

import org.bukkit.Sound
import org.eternalreturn.ercharacter.CooldownContext
import org.eternalreturn.ercharacter.ERCharacterSkillMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.erentity.ERHitboxEntity
import org.eternalreturn.erentity.events.EREntityEvent
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent
import kotlin.math.max

/**
 * 액티브 진행을 끄기 위해 전달하는 이벤트
 * */
class InterruptActiveEvent : EREntityEvent;

class Active(
    cooldownContext: CooldownContext
) : ERCharacterSkillMonobehaviour<CharacterSwapHandEvent, Character_Hyunwoo>(cooldownContext, durationTicks = 4, "ACD") {

    var dx : Double = 0.0;
    var dz : Double = 0.0;

    override fun skillStart(event: CharacterSwapHandEvent) {

        if (player.location.add(0.0, -0.5, 0.0).block.type.isAir()) {
            player.player.playSound(player.location, Sound.ENTITY_VILLAGER_NO, 1f, 1f)
            stopMonobehav();
            return
        }

        val velocity = player.getDirection();
        velocity.y(0.0);
        player.setVelocity(velocity);
        dx = velocity.x();
        dz = velocity.z();
    }

    private fun dash(){
        val velocity = player.getDirection();
        velocity.y(0.0)
        player.setVelocity(velocity);
    }

    override fun skillUpdate(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        if(eventMap[InterruptActiveEvent::class.java] != null){
            interruptSkill();
            return;
        }

        dash()

        //부채꼴 감지 기능
        val pPos = player.getPosition();
        val direction = vec3(dx, 0.0, dz);
        val lvec = direction.rotXZ(30.0);
        val rvec = direction.rotXZ(-30.0);

        for(victim in erEngine.entityList){
            if(victim !is ERHitboxEntity || victim === player) continue;

            val victimRadius = max(victim.obbHalfZ, victim.obbHalfX);

            val ePos = victim.getPosition();
            val dist = magnitude(pPos - ePos) - victimRadius;

            if(dist <= 1.0){
                val down = (lvec cross (ePos - pPos)).y(); // -> y값이 양수여야 함. (xy가 아니라 xz이므로)
                val up = (rvec cross (ePos - pPos)).y(); // -> y값이 음수여야 함 (xy가 아니라 xz이므로)

                if(down < 0 && up > 0){

                    val dmg = player.activeWallslamDamage.get();
                    victim.sumbitMonobehav(WallSlamDetector(dmg, player, dx, 0.0, dz, 100L, durationTicks))

                }

            }

        }



    }



}