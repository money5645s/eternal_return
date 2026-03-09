package org.eternalreturn.erentity

import org.bukkit.entity.Entity
import org.eternalreturn.erentity.globalmonobehav.EntityRayCastingMeleeAttack
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.physics.Handle

/**
 * EREntity 중 유효한 Hitbox를 가져야 하는 Entity인 경우
 * */
abstract class ERHitboxEntity( // extends MonobehaviourActor()
    erEngine: EREngine,
    entity : Entity,
    obbHalfX : Double, obbHalfY : Double, obbHalfZ : Double,
    obbLocX : Double, obbLocY : Double, obbLocZ : Double
    /**
     * 해당 MonobehaviourActor의 Collider 설정
     */
) : EREntity(erEngine, entity) {

    val obbHandle : Handle = erEngine.orientedBoxSoA.create(transformHandle,
        obbHalfX, obbHalfY, obbHalfZ,
        obbLocX, obbLocY, obbLocZ
    );

    /**
     * 해당 객체를 소유하고 있는 EREngine의 삭제 리스트에 해당 객체를 삽입한다.
     * */
    override fun remove(){
        if(referenceCount == 0)return;
        super.remove();
        erEngine.addRemoveList(this);
    }

    init {
        obbHandle.actor = this;
        println("[SoA CREATE] ${this.javaClass.simpleName} T${transformHandle.entityID} | O${obbHandle.entityID}")
        this.registerMonobehaviour(EntityRayCastingMeleeAttack())
    }

}