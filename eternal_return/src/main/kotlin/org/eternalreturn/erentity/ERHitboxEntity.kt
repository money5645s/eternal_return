package org.eternalreturn.erentity

import org.bukkit.entity.Entity
import org.eternalreturn.erentity.globalmonobehav.EntityRayCastingMeleeAttack
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.physics.Handle
import kotlin.properties.Delegates

/**
 * EREntity 중 유효한 Hitbox를 가져야 하는 Entity인 경우
 * */
abstract class ERHitboxEntity( // extends MonobehaviourActor()
    erEngine: EREngine,
    entity : Entity,
    val obbHalfX : Double, val obbHalfY : Double, val obbHalfZ : Double,
    val obbLocX : Double, val obbLocY : Double, val obbLocZ : Double
    /**
     * 해당 MonobehaviourActor의 Collider 설정
     */
) : EREntity(erEngine, entity) {

    var obbHandle : Handle by Delegates.notNull()

    /**
     * Multi-thread 상에서 동작하는 객체를 생성하기 위한 함수.
     * MonobehaviourModule의 갱신 단계에서 호출된다.
     * */
    override fun lateinit() {
        super.lateinit()
        obbHandle = erEngine.orientedBoxSoA.create(transformHandle,
            obbHalfX, obbHalfY, obbHalfZ,
            obbLocX, obbLocY, obbLocZ);
        obbHandle.actor = this;
    }


    /**
     * 해당 객체를 소유하고 있는 EREngine의 삭제 리스트에 해당 객체를 삽입한다.
     * */
    override fun remove(){
        if(referenceCount == 0) {
            return;
        }
        super.remove();
        if(lateinited){
            erEngine.addRemoveList(this);
        }
    }

    init {
        //println("[SoA CREATE] ${this.javaClass.simpleName} T${transformHandle.entityID} | O${obbHandle.entityID}")
        this.registerMonobehaviour(EntityRayCastingMeleeAttack())
    }

}