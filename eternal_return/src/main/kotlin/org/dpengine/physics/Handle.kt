package org.dpengine.physics

import org.dpengine.monobehaviour.MonobehaviourActor

/**
 * SparseIdx와 GenerationIdx를 함께 저장
 * */
@JvmInline value class Handle(val data : Long) {

    /**
     * SoA 모듈에서 ```sparse[this.entityID]```를 통해 dense의 위치를 알 수 있다
     * 해당 값 자체만으로는 SoA모듈 내의 다른 필드에 접근해야 한다. 반드시 위의 변환을 마친 뒤
     * 접근해야 유효한 값을 얻을 수 있다.
     * */
    val entityID: Int get() = (data shr 32).toInt();

    /**
     * 해당 Handle이 SoA모듈 내에서 유효한 객체를 참조하고 있는지 알아보기 위한 값이다.
     * 만약 참조 시에 해당 ```generation```값이 더 작은 경우, 유효하지 않은 객체이다.
     * */
    val generation: Int get() = (data and 0xFFFFFFFFL).toInt()

    companion object{

        /**
         * 해당 클래스로 해석될 수 있는 Long값을 반환함.
         * */
        fun createHandle(entityID : Int, generation : Int) : Long{
            return (entityID.toLong() shl 32) or generation.toLong()
        }


    }

}