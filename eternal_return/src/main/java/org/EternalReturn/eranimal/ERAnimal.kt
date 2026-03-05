package org.eternalreturn.eranimal

import org.bukkit.Location
import org.eternalreturn.eranimal.animals.behavs.Battle
import org.eternalreturn.eranimal.animals.behavs.Idle
import org.eternalreturn.eranimal.animals.events.IdleEvent
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.command.AddSpigotEntityVelocity
import org.eternalreturn.util.dpengine.command.SetSpigotEntityVelocity

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

        registerMonobehaviour(Battle())
        registerMonobehaviour(Idle())

        this.aJEntity.setDebugDisplay("T" + transformHandle.entityID + " | O" + obbHandle.entityID + "\n\n\n\n")

        this.submitEvent(IdleEvent())

    }

    /**
     * ERAJEntity의 속도를 변경한다. 그러나 Actor가 없다면 아무런 작용도 하지 않는다.
     * */
    override fun setVelocity(x : Double, y : Double, z : Double){
        if(aJEntity.actor == null){
            return;
        }
        if(!aJEntity.actor.isValid){
            return;
        }
        this.geometryModule.dpEngine.appendCommandQueue(SetSpigotEntityVelocity(aJEntity.actor, x, y, z))
    }

    /**
     * ERAJEntity의 속도를 변경한다. 그러나 Actor가 없다면 아무런 작용도 하지 않는다.
     * */
    override fun addVelocity(x : Double, y : Double, z : Double){
        if(aJEntity.actor == null){
            return;
        }
        if(!aJEntity.actor.isValid){
            return;
        }
        this.geometryModule.dpEngine.appendCommandQueue(AddSpigotEntityVelocity(aJEntity.actor, x, y, z))
    }

    val isShown: Boolean
        get() = this.aJEntity.isShown
}
