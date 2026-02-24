package org.eternalreturn.util.dpengine.datastructure

import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor

open class UpdateContainer<E : MonobehaviourActor>{

    private val queue = Array<ArrayList<E>>(2) { ArrayList() };
    private var curIdx = 0;
    val curQueue : ArrayList<E>
        get() = queue[curIdx]


    /**
     * O(N) 시간에 돌면서 제거해야 할 Actor를 제거한다.
     * */
    open fun update(){
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