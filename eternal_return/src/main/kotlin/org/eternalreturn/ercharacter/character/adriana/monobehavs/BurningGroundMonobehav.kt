package org.eternalreturn.ercharacter.character.adriana.monobehavs

import org.eternalreturn.util.dpengine.behaviour.Monobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.World
import org.bukkit.entity.LivingEntity
import org.eternalreturn.ercharacter.character.adriana.entities.BurningGroundVirtualEntity
import org.eternalreturn.ercharacter.character.adriana.events.LetsBurn
import org.eternalreturn.erentity.events.EREntityBurnEvent
import org.eternalreturn.system.EREngine

class BurningGroundMonobehav : Monobehaviour<LetsBurn>() {



    override fun start(event: LetsBurn) {

    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        val burningGround = actor as BurningGroundVirtualEntity;

        val loc: Location = burningGround.location
        val world : World = loc.world!!
        val caster = burningGround.caster

        val lifeTime = (System.currentTimeMillis() - burningGround.startTime) / 50

        if(lifeTime >= 100 || loc.world == null){ // 100ticks
            stopMonobehav()
            actor.referenceCount--
            return
        }

        world.spawnParticle(Particle.FLAME, loc, 3, 0.2, 0.1, 0.2, 0.02)
        if (lifeTime % 5 == 0L) {
            world.spawnParticle(Particle.SMOKE, loc, 1, 0.1, 0.1, 0.1, 0.02)
        }

        // 데미지 판정: 주변 1.2칸 내의 적에게 화상
        for (erEntity in (dpEngine as EREngine).entityList) {
            if(erEntity === caster) continue;

            val distSqr = magnitudeSqr(caster.getPosition() - erEntity.getPosition());

            if(distSqr <= 3.0 * 3.0){
                erEntity.submitEvent(EREntityBurnEvent(caster, 100))
            }
        }
    }


}