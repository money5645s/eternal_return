package org.eternalreturn.util.dpengine.physics

import it.unimi.dsi.fastutil.ints.IntArrayList

open class SoAModule(size : Int) {

    val dense = IntArray(size); // denseID --f--> entityID
    val sparse = IntArray(size) { -1 }; //entityID --f--> denseID
    val generation = IntArray(size) { 0 };

    /**
     * 엔티티 핸들의 인덱스를 발행하는 함수
     * */
    var lastRemovedEntityID = -1; //freeHead
    var lastSparseIdx = 0;
    internal fun allocID() : Int{
        var id = -1;
        if(lastRemovedEntityID > -1){
            // sparse set의 빈 공간들을 리스트처럼 엮어 사용.
            // n0 := sparse[lastRemovedEntityID] n1 := lastRemovedEntityID
            // therefore n1->next == n0 관계가 성립
            id = lastRemovedEntityID;
            lastRemovedEntityID = sparse[lastRemovedEntityID];
        }else{
            id = lastSparseIdx++;//same as -> id=lastIdx; lastIdx++;
        }
        return id;
    }

    /**
     * 해당 세대가 맞는 경우 true
     * 아닌 경우 false
     * */
    fun isValid(handle : Handle) : Boolean{
        return generation[handle.entityID] == handle.generation;
    }

    /**
     * dense아이디들에 대한 인덱스
     * */
    var lastDenseIdx = 0;
    fun getNumOfEntities() : Int{return lastDenseIdx}
    internal fun createHandle() : Triple<Int, Int, Int>{
        //핵심 사실 : 상호 쿼리가 가능해야 함
        //sparse[dense[i]] == i
        //dense[sparse[e]] == e
        //따라서 dense[i] == e, sparse[e] == i
        //즉 연결리스트의 배열적 진화형 정도라고 생각하면 될 듯.

        val entityId = allocID();
        val i = lastDenseIdx;

        dense[i] = entityId;
        sparse[entityId] = i;
        lastDenseIdx++;
        return Triple(entityId, i, generation[entityId]);
        //return Handle(entityId, generation[entityId]); //generation까지 같이 반환해야 handle이 됨
    }

    // id -> sparse -> dense -> data
    /**
     * 핸들을 제거하는 함수
     * */
    internal fun removeHandle(handle: Handle) : Pair<Int, Int>{

        /**
         * 삭제될 엔티티 아이디
         * */
        val entityId  = handle.entityID; //제거할 인덱스 : rmvIdx

        //예외처리
        if(entityId >= lastSparseIdx) throw RuntimeException("삭제할 핸들의 아이디가 저장된 엔티티 수보다 큽니다.");
        if(generation[entityId] != handle.generation) {
            println("")
            throw RuntimeException("세대가 다릅니다. 즉 이미 삭제된 개체입니다. ID : $entityId")
        }

        val rmvDenseIdx = sparse[entityId]
        if(rmvDenseIdx == -1) throw RuntimeException("해당 개체는 존재하지 않습니다. sparseIdx 탐색 결과의 값이 -1입니다.")

        val migrator = lastDenseIdx - 1
        val lastEntityId = dense[migrator]

        val pair = Pair(rmvDenseIdx, migrator);

        dense[rmvDenseIdx] = lastEntityId
        sparse[lastEntityId] = rmvDenseIdx

        sparse[entityId] = lastRemovedEntityID;
        lastRemovedEntityID = entityId;
        generation[entityId]++

        lastDenseIdx--

        return pair;

    }

}
