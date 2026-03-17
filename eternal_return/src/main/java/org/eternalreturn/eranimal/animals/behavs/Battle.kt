package org.eternalreturn.eranimal.animals.behavs

import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeInstance
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Husk
import org.eternalreturn.eranimal.ERAnimal
import org.eternalreturn.eranimal.ERAnimalMonobehaviour
import org.eternalreturn.eranimal.animals.events.GetBackEvent
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.erentity.events.EREntityDamagedEvent
import org.eternalreturn.system.EREngine
import org.eternalreturn.system.PluginInstance
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import java.util.*

class Battle : ERAnimalMonobehaviour<EREntityDamagedEvent>() {

    var animalState: AnimalState = AnimalState.MOVE

    enum class AnimalState {
        ATTACK,
        MOVE,
        DEATH
    }

    var xSpawn : Double = 0.0;
    var ySpawn : Double = 0.0;
    var zSpawn : Double = 0.0;

    var speed : Double = 0.0;

    override fun start(event: EREntityDamagedEvent) {
        val erAnimal = actor as ERAnimal;
        val ajEntity = erAnimal.aJEntity;
        if (!ajEntity.isShown()) return

        if(erAnimal.hp <= 0.0){
            stopMonobehav();
            return;
        }

        val world = PluginInstance.getServerInstance().server.worlds.first();
        ajEntity.setActor(world.spawnEntity(ajEntity.location, EntityType.HUSK) as Husk);
        (ajEntity.getActor() as Husk).setAI(true)
        //println("attacked by a player")

        xSpawn = ajEntity.location.x;
        ySpawn = ajEntity.location.y;
        zSpawn = ajEntity.location.z;
        speed = (ajEntity.actor as Husk).getAttribute(Attribute.MOVEMENT_SPEED)!!.baseValue;
        speed = speed * 1.2;
        (ajEntity.actor as Husk).getAttribute(Attribute.MOVEMENT_SPEED)!!.baseValue = speed;

    }

    var deathTick = 0;
    override fun update(eventMap: Map<Class<out MonobehaviourEvent>,MonobehaviourEvent>) {

        val engine = dpEngine as EREngine;
        val erAnimal = this.actor as ERAnimal;
        val ajEntity = (this.actor as ERAnimal).aJEntity;

        if(eventMap[GetBackEvent::class.java] != null){
            stopMonobehav();
            return;
        }


        val distFromSpawn = erAnimal.getPosition() - vec3(xSpawn, ySpawn, zSpawn);
        val distSqr = magnitudeSqr(distFromSpawn);
        if(distSqr >= 25.0 * 25.0){
            //println("Too far");
            erAnimal.setReturn(true);
            stopMonobehav();
            return;
        }

        //사망 시
        if(erAnimal.hp <= 0.0){
            if(deathTick == 0){
                deathTick++;
                ajEntity.playAnimForce("death");
                return;
            }

            if(deathTick < 10){
                deathTick++;
                return;
            }

            erAnimal.setDead(true);
            ajEntity.stopAnim();
            stopMonobehav();
            return;

        }

        if(ajEntity.actor == null)return;
        val actor = ajEntity.actor as Husk

        val target: Entity? = actor.target
        if (target == null) return;
        val targetEREntity = engine.getEREntity(target);
        if(targetEREntity == null) return;

        val root = ajEntity.getRootEntity()
        val actorLoc = actor.location
        root.setRotation(actorLoc.yaw, root.location.pitch)

        //범위 내에 있는가?

        //상태 결정
        val currentBaseSpeed =  erAnimal.movementSpeed

        if(ajEntity.isPlaying("attack")){
            attackAnimTick++;
        }


        if (isInDistance(5.0, actor, target)) {//범위 내에 있으면 계속 공격 해야 함.
            erAnimal.movementSpeed = 0.0;
            (ajEntity.actor as Husk).getAttribute(Attribute.MOVEMENT_SPEED)!!.baseValue = 0.0
            attack(targetEREntity)

        } else if(!ajEntity.isPlaying("attack")) {
            if(-1E-7 < currentBaseSpeed && currentBaseSpeed < 1E-7){
                erAnimal.movementSpeed = speed;
            }
            move();
        }

    }


    fun move(){
        val erEntity = (this.actor as ERAnimal);
        val ajEntity = erEntity.aJEntity
        ajEntity.playAnimForce("move")
    }

    var attackAnimTick = 0;
    fun attack(target : EREntity){

        val erEntity = (this.actor as ERAnimal);
        val ajEntity = erEntity.aJEntity

        if(!ajEntity.isPlaying("attack")){
            ajEntity.playAnimForce("attack");
            attackAnimTick = 0;
        }

        if(ajEntity.isPlaying("attack")){
            if(ajEntity.getCurrentTicks() == erEntity.attackTicks[0] || ajEntity.getCurrentTicks() == erEntity.attackTicks[1]){
                target.damage(erEntity.damage, erEntity);

            }

        }

    }

    fun isInDistance(r: Double, e0: Entity, e1: Entity): Boolean {
        val tx = e0.location.x
        val ty = e0.location.y
        val tz = e0.location.z

        val ax = e1.location.x
        val ay = e1.location.y
        val az = e1.location.z

        val dx = ax - tx
        val dy = ay - ty
        val dz = az - tz

        return (dx * dx + dy * dy + dz * dz <= r * r)
    }



}
