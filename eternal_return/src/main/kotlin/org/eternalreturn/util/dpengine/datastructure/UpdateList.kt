package org.eternalreturn.util.dpengine.datastructure

import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor

/**
 * 삽입 시 레퍼런스 카운트를 1 올리는 컨테이너 객체.
 *
 * 레퍼런스 카운트가 0인 MonobehaviourActor들을 제거한다.
 *
 * 제거하는 데에는 O(N)시간이 필요하다.
 * */
class UpdateList<E : MonobehaviourActor>{

    private val queue = Array<ArrayList<E>>(2) { ArrayList() };
    private var curIdx = 0;
    val curQueue : ArrayList<E>
        get() = queue[curIdx]

    /**
     * 레퍼런스 카운트를 한 개 올리며 삽입한다.
     * */
    fun add(actor : E){
        curQueue.add(actor);
        actor.refer();
    }

    /**
     * O(N) 시간에 돌면서 제거해야 할 Actor를 제거한다.
     * */
    fun update(){

        val prevQueue = curQueue;

        curIdx = curIdx xor 1
        val nextQueue = queue[curIdx]
        nextQueue.clear(); //나중에 바꿔야할 수도 있음. 성능 잡아먹음.

        for(actor in prevQueue){
            if(actor.referenceCount > 0){
                nextQueue.add(actor);
            }
        }
    }
}