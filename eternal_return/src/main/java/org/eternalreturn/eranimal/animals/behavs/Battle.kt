package org.eternalreturn.eranimal.animals.behavs

import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Husk
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.eternalreturn.eranimal.ERAJEntity
import org.eternalreturn.eranimal.ERAnimalMonobehaviour
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.events.EREntityAttackedEvent
import org.eternalreturn.system.PluginInstance
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class Battle : ERAnimalMonobehaviour<EREntityAttackedEvent>() {
    var ajEntity: ERAJEntity? = null

    var animalState: AnimalState = AnimalState.MOVE

    enum class AnimalState {
        ATTACK,
        MOVE
    }

    override fun start(event: EREntityAttackedEvent) {
        ajEntity = eRAJEntity
        if (!ajEntity!!.isShown()) return

        val world = PluginInstance.getServerInstance().server.worlds.first();
        ajEntity!!.setActor(world.spawnEntity(ajEntity!!.location, EntityType.HUSK) as Husk);
        (ajEntity!!.getActor() as Husk).setAI(true)
        println("attacked by a player")
    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {

        val ajEntity = this.ajEntity
        val actor = ajEntity!!.getActor() as Husk
        val target: Entity? = actor.target
        if (target == null) return

        val root = ajEntity.getRootEntity()
        val actorLoc = actor.location
        root.setRotation(actorLoc.yaw, root.location.pitch)

        //범위 내에 있는가?
        val isInDistance = isInDistance(3.0, actor, target)

        //상태 결정
        if (isInDistance) {//범위 내에 있으면 계속 공격 해야 함.
            animalState = AnimalState.ATTACK
        } else if (!ajEntity.isPlaying("attack")) {
            animalState = AnimalState.MOVE
        }

        //상태에 따라 행동 : MOVE
        if (animalState == AnimalState.MOVE) {
            ajEntity.playAnim("move")
        }

        //상태에 따라 행동 : ATTACK
        if (animalState == AnimalState.ATTACK) {
            ajEntity.playAnimForce("attack")
        }
    }
}
