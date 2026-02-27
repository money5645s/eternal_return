package org.eternalreturn.eranimal

import org.bukkit.Location
import org.eternalreturn.eranimal.animals.behavs.Battle
import org.eternalreturn.eranimal.animals.behavs.Idle
import org.eternalreturn.eranimal.animals.events.IdleEvent
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.system.EREngine

/**
 * MonobehaviourActor역할을 하는 야생동물 클래스.
 */
open class ERAnimal(
    engine: EREngine,
    var aJEntity: ERAJEntity,
    obbHalfX: Double, obbHalfY: Double,
    obbHalfZ: Double, obbLocX: Double, obbLocY: Double, obbLocZ: Double
) : EREntity(
    engine,
    aJEntity.rootEntity,
    obbHalfX, obbHalfY, obbHalfZ,
    obbLocX, obbLocY, obbLocZ
) {
    /**
     * 야생동물 스킬 쿨다운
     */
    protected var cooldownSeconds: Long = 0

    init {
        //System.out.println(collider.getClass());
        registerMonobehaviour(Battle())
        registerMonobehaviour(Idle())

        this.aJEntity.setDebugDisplay("T" + transformHandle.entityID + " | O" + obbHandle.entityID + "\n\n\n\n")

        this.submitEvent(IdleEvent())

    }

    val isShown: Boolean
        get() = this.aJEntity.isShown
}
