package org.eternalreturn.util.dpengine.datastructure

import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourActor

/**
 * 삽입 시 레퍼런스 카운트를 올리지 않는, View의 역할만을 하는 객체.
 * 해당 객체를 관리하는 UpdateList의 update()메소드 호출 시 레퍼런스 카운터가 0인 객체들이 자동으로 정리된다.
 * */
class UpdateView<E : MonobehaviourActor> : UpdateContainer<E>() {
    /**
     * 객체를 삽입하나 레퍼런스 카운터는 올리지 않는다.
     * 레퍼런스 카운트가 0인 객체를 넣는다면 DeadActorException을 던진다
     * 이후 update()메소드 호출 시에 해당 삽입이 완전히 반영된다.
     * @throws DeadActorException
     * */
    override fun add(obj : E){
        if(obj.referenceCount <= 0){
            throw DeadActorException(this.javaClass.simpleName);
        }
        waitListToInsert.add(obj);
    }
}