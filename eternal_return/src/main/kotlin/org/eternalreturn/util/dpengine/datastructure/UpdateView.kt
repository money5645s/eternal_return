package org.eternalreturn.util.dpengine.datastructure

import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor

/**
 * 삽입 시 레퍼런스 카운트를 1 올리는 컨테이너 객체.
 *
 * 레퍼런스 카운트가 0인 MonobehaviourActor들은 제거한다.
 *
 * 제거하는 데에는 O(N)시간이 필요하다.
 * */
class UpdateView<E : MonobehaviourActor> : UpdateContainer<E>() {
    /**
     * 객체를 삽입하나 레퍼런스 카운터는 올리지 않는다.
     * 레퍼런스 카운트가 0인 객체를 넣는다면 DeadActorException을 던진다
     * @throws DeadActorException
     * */
    fun add(actor : E){
        if(actor.referenceCount <= 0){
            throw DeadActorException(this.javaClass.simpleName);
        }
        curQueue.add(actor);
    }
}