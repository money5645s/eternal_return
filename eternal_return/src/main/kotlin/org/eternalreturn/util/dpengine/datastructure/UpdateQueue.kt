package org.eternalreturn.util.dpengine.datastructure

import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor

class UpdateQueue<E : MonobehaviourActor>{

    private val queue = Array<ArrayList<E>>(2) { ArrayList() };
    private var curIdx = 0;
    val curQueue : ArrayList<E>
        get() = queue[curIdx]

    fun updateQueue(){

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