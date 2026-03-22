package org.eternalreturn.util.dpengine.physics

import org.eternalreturn.util.dpengine.monobehaviour.MonobehaviourActor

/**
 * SparseIdx와 GenerationIdx를 함께 저장
 * SparseIdx가 같아도 Gen이 다르면 바로 SoA모듈 상에서 resolve() 메소드 호출됨.
 * */
class Handle(val entityID : Int, val generation : Int, var actor : MonobehaviourActor? = null){

}