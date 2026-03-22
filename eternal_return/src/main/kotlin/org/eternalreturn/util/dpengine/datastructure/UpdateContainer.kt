package org.eternalreturn.util.dpengine.datastructure

import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourActor
import java.util.Vector

/**
 * 엔진 객체들을 관리하기 위한 컨테이너.
 *
 * 레퍼런스 카운터가 0인 객체들을 제거하는 기능을 기본적으로 보유함.
 *
 * 또한 생성된 객체를 해당 컨테이너에 편입시키며, lateinit 하는 기능도 보유함.
 * */
abstract class UpdateContainer<E : MonobehaviourActor>{

    private val queue = Array<ArrayList<E>>(2) { ArrayList() };

    val waitListToInsert = Vector<E>();

    @Volatile
    private var curIdx = 0;

    val curQueue : ArrayList<E>
        get() = queue[curIdx]

    /**
     * waitList에 오브젝트를 삽입하는 메소드. Thread-safe하다.
     * */
    abstract fun add(obj : E);

    /**
     * O(N) 시간에 돌면서 제거해야 할 Actor를 제거한다.
     * 주의 : 해당 메소드의 경우 Thread-safe하지 않다.
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

        for(actor in waitListToInsert){
            if(!actor.lateinitIsDone){
                actor.lateinitIsDone = true;
                actor.lateinit();
            }
            nextQueue.add(actor);
        }
        waitListToInsert.clear();

    }
}