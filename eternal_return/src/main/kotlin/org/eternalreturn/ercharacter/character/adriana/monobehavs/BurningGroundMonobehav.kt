package org.eternalreturn.ercharacter.character.adriana.monobehavs

import org.eternalreturn.util.dpengine.behaviour.Monobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.eternalreturn.ercharacter.character.adriana.entities.BurningGroundVirtualEntity
import org.eternalreturn.ercharacter.character.adriana.events.LetsBurn

class BurningGroundMonobehav : Monobehaviour<LetsBurn>() {



    override fun start(event: LetsBurn) {

    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        val burningGround = actor as BurningGroundVirtualEntity;

        val loc: Location = burningGround.location
        val caster = burningGround.caster

        val lifeTime = (System.currentTimeMillis() - burningGround.startTime) / 50

        if(lifeTime >= 100 || loc.world == null){ // 100ticks
            stopMonobehav()
            actor.referenceCount--
            return
        }

        loc.getWorld()!!.spawnParticle(Particle.FLAME, loc, 3, 0.2, 0.1, 0.2, 0.02)
        if (lifeTime % 5 == 0L) {
            loc.getWorld()!!.spawnParticle(Particle.SMOKE, loc, 1, 0.1, 0.1, 0.1, 0.02)
        }

        // 데미지 판정: 주변 1.2칸 내의 적에게 화상
        for (entity in loc.getWorld()!!.getNearbyEntities(loc, 1.2, 1.2, 1.2)) {
            if (entity is LivingEntity && entity != caster) {
                entity.setFireTicks(100) // 2초간 불타게 함
            }
        }
    }


}