package org.eternalreturn.ercharacter.character.adriana

import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.World
import org.eternalreturn.erentity.events.EREntityBurnEvent
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.DPEngine
import org.eternalreturn.util.dpengine.behaviour.Monobehaviour
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class BurningGroundVirtualEntity(
    dpEngine: DPEngine,
    val caster: ERPlayer,
    val location: Location,
    val startTime: Long,
    val damage: Double,
    val duration : Long,
) : MonobehaviourActor(dpEngine) {

    override fun lateinit() {
    }

    init {
        registerMonobehaviour(BurningGroundMonobehav());
    }
}

class BurningGroundMonobehav : Monobehaviour<LetsBurnEvent>() {
    override fun start(event: LetsBurnEvent) {}
    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        val burningGround = actor as BurningGroundVirtualEntity;

        val loc: Location = burningGround.location
        val world : World = loc.world!!
        val caster = burningGround.caster as Character_Adriana

        val lifeTime = (System.currentTimeMillis() - burningGround.startTime) / 50

        if(lifeTime >= 100 || loc.world == null){ // 100ticks
            stopMonobehav()
            actor.referenceCount--
            return
        }

        if (lifeTime % 5 == 0L) {
            world.spawnParticle(Particle.FLAME, loc, 3, 0.2, 0.1, 0.2, 0.02)
            world.spawnParticle(Particle.SMOKE, loc, 1, 0.1, 0.1, 0.1, 0.02)
        }

        if(lifeTime % 20 == 0L){
            // 데미지 판정: 주변 1.2칸 내의 적에게 화상
            for (erEntity in (dpEngine as EREngine).entityList) {
                if(erEntity === caster) continue;

                val distSqr = magnitudeSqr(caster.getPosition() - erEntity.getPosition());

                if(distSqr <= 3.0 * 3.0){
                    erEntity.damageForcePierce(burningGround.damage, caster)
                    erEntity.submitEvent(EREntityBurnEvent(caster, burningGround.duration))
                }
            }
        }
    }
}