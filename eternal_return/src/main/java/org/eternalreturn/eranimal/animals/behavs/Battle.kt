package org.eternalreturn.eranimal.animals.behavs

import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Husk
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.eternalreturn.eranimal.ERAJEntity
import org.eternalreturn.eranimal.ERAnimal
import org.eternalreturn.eranimal.ERAnimalMonobehaviour
import org.eternalreturn.eranimal.animals.events.GetBackEvent
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.events.EREntityAttackedEvent
import org.eternalreturn.system.EREngine
import org.eternalreturn.system.PluginInstance
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent

class Battle : ERAnimalMonobehaviour<EREntityAttackedEvent>() {

    var animalState: AnimalState = AnimalState.MOVE

    enum class AnimalState {
        ATTACK,
        MOVE,
        DEATH
    }

    val stateFuncTable = HashMap<AnimalState, () -> Unit>();

    var xSpawn : Double = 0.0;
    var ySpawn : Double = 0.0;
    var zSpawn : Double = 0.0;

    override fun start(event: EREntityAttackedEvent) {
        stateFuncTable[AnimalState.MOVE] = this::move;
        stateFuncTable[AnimalState.ATTACK] = this::attack;

        val erAnimal = actor as ERAnimal;
        val ajEntity = erAnimal.aJEntity;
        if (!ajEntity.isShown()) return

        val world = PluginInstance.getServerInstance().server.worlds.first();
        ajEntity.setActor(world.spawnEntity(ajEntity.location, EntityType.HUSK) as Husk);
        (ajEntity.getActor() as Husk).setAI(true)
        println("attacked by a player")

        xSpawn = ajEntity.location.x;
        ySpawn = ajEntity.location.y;
        zSpawn = ajEntity.location.z;

    }


    override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {

        if(eventMap[GetBackEvent::class.java] != null){
            stopMonobehav();
            return;
        }
        val engine = dpEngine as EREngine;
        val erAnimal = this.actor as ERAnimal;
        val ajEntity = (this.actor as ERAnimal).aJEntity;

        val distFromSpawn = erAnimal.getPosition() - vec3(xSpawn, ySpawn, zSpawn);
        val distSqr = magnitudeSqr(distFromSpawn);
        if(distSqr >= 10.0 * 10.0){
            println("Too far");
            erAnimal.remove();
            stopMonobehav();
            return;
        }

        //사망 시
        if(erAnimal.hp <= 0.0){
            erAnimal.aJEntity.playAnimForce("death");
            erAnimal.remove();
            stopMonobehav();
            return;
        }

        val actor = ajEntity.getActor() as Husk
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

        stateFuncTable[animalState]!!();

    }

    fun move(){
        val erEntity = (this.actor as ERAnimal);
        val ajEntity = erEntity.aJEntity
        val velocity = erEntity.getDirection();
        if(erEntity.isNotTranslating()){
            val sideStep = vec3(-z(velocity), 0.0, x(velocity));
            (this.actor as EREntity).addVelocity(3.0 * sideStep)
        }
        ajEntity.playAnim("move")
    }

    fun attack(){
        val erEntity = (this.actor as ERAnimal);
        val ajEntity = erEntity.aJEntity
        ajEntity.playAnimForce("attack")
    }


}
