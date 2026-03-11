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
import org.eternalreturn.util.dpengine.command.SetSpigotEntityVelocity

class Battle : ERAnimalMonobehaviour<EREntityAttackedEvent>() {

    var animalState: AnimalState = AnimalState.MOVE

    enum class AnimalState {
        ATTACK,
        MOVE,
        DEATH
    }

    var xSpawn : Double = 0.0;
    var ySpawn : Double = 0.0;
    var zSpawn : Double = 0.0;

    override fun start(event: EREntityAttackedEvent) {

        val erAnimal = actor as ERAnimal;
        val ajEntity = erAnimal.aJEntity;
        if (!ajEntity.isShown()) return

        val world = PluginInstance.getServerInstance().server.worlds.first();
        ajEntity.setActor(world.spawnEntity(ajEntity.location, EntityType.HUSK) as Husk);
        (ajEntity.getActor() as Husk).setAI(true)
        //println("attacked by a player")

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
        if(distSqr >= 25.0 * 25.0){
            //println("Too far");
            erAnimal.remove();
            stopMonobehav();
            return;
        }

        //사망 시
        if(erAnimal.hp <= 0.0){
            erAnimal.aJEntity.playAnimForce("death");
            //if(erAnimal.aJEntity.isPlaying("death")) return; <- BUG?
            erAnimal.remove();
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
        val isInDistance = isInDistance(3.0, actor, target)

        //상태 결정
        if (isInDistance) {//범위 내에 있으면 계속 공격 해야 함.
            attack(targetEREntity)
        } else if(!ajEntity.isPlaying("attack")) {
            move();
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

    var animTick = 0;
    fun attack(target : EREntity){

        val erEntity = (this.actor as ERAnimal);
        val ajEntity = erEntity.aJEntity

        //println("$animTick ticks, !ajEntity.isPlaying(\"attack\") == ${!ajEntity.isPlaying("attack")}");

        if(!ajEntity.isPlaying("attack")){
            //println(ajEntity.animationPlaying)
            ajEntity.playAnimForce("attack")
            animTick = 0;
            return;
        }

        if(animTick == erEntity.attackTicks[0] || animTick == erEntity.attackTicks[1]){
            target.damage(erEntity.damage, erEntity);
        }

        animTick++;

    }


}
