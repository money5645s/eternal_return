package org.eternalreturn.ercharacter.globalmonobehav

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterParabolicFlyEvent
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourEvent
import org.eternalreturn.util.dpengine.command.SetSpigotEntityVelocity

class ParabolicFly : ERCharacterMonobehaviour<CharacterParabolicFlyEvent>() {

    var height = 0.0
    var dx = 0.0
    var dz = 0.0
    var etaSeconds = 0.0

    var t = 0.0

    var vx: Double = 0.0;
    var vz: Double = 0.0;
    var v0y: Double = 0.0;
    var g: Double = 0.0;

    override fun start(event: CharacterParabolicFlyEvent) {
        height = event.height
        dx = event.dx
        dz = event.dz
        etaSeconds = event.etaSeconds

        t = 0.0

        // 수평 속도
        vx = dx / etaSeconds
        vz = dz / etaSeconds

        // 포물선 보정
        v0y = (4 * height) / etaSeconds
        g = (8 * height) / (etaSeconds * etaSeconds)
    }

    override fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        if (t <= etaSeconds) {

            val vy = v0y - g * t

            dpEngine.appendCommand(
                SetSpigotEntityVelocity(
                    this.entity,
                    vx / 20,
                    vy / 20,
                    vz / 20
                )
            )

            t += 0.05
            return
        }

        stopMonobehav()
    }
}