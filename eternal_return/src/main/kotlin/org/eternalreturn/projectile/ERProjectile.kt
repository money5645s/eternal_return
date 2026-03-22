package org.eternalreturn.projectile

import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.projectile.events.ProjectileInitEvent
import org.eternalreturn.projectile.globalmonobehav.ProjectileFlight
import org.eternalreturn.projectile.globalmonobehav.ProjectileRayCastingAttack
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourActor

class ERProjectile(
    engine : EREngine,
    var x : Double, var y : Double, var z : Double,
    var dx : Double, var dy : Double, var dz : Double,
    val owner : ERPlayer
) : MonobehaviourActor(engine) {
    val erEngine : EREngine
        get() {
            return dpEngine as EREngine;
        }

    var damage : Double = 10.0;

    override fun lateinit() {
    }

    init{
        monobehaviourModule.register(this);
        erEngine.projectile.add(this);
        registerMonobehaviour(ProjectileFlight());
        registerMonobehaviour(ProjectileRayCastingAttack())
        this.submitEvent(ProjectileInitEvent())
    }

}
