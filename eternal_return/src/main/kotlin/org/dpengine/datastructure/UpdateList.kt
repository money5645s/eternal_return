package org.dpengine.datastructure

import org.dpengine.monobehaviour.MonobehaviourActor

/**
 * 삽입 시 레퍼런스 카운트를 1 올리는 컨테이너 객체.
 * 레퍼런스 카운트가 0인 MonobehaviourActor들은 제거한다.
 * 제거하는 데에는 O(N)시간이 필요하다.
 * 해당 리스트가 보유한 요소들의 일부를 보유한 UpdateView들을 등록할 수 있으며, 해당 객체가 update()될 때
 * UpdateView들도 함께 업데이트 된다.
 * */
class UpdateList<E : MonobehaviourActor> : UpdateContainer<E>() {
    /**
     * 레퍼런스 카운트를 한 개 올리며 waitList에 삽입한다.
     * 이후 update()메소드 호출 시에 해당 삽입이 완전히 반영된다.
     * */
    override fun add(obj : E){
        waitListToInsert.add(obj);
        obj.refer();
    }

    /**
     * 해당 리스트가 업데이트 될 때마다 함께 수정될 View들을 등록한다.
     * 해당 View들은 UpdateList가 update를 통해 collecting을 할 때마다
     * 함께 반영된다.
     * */
    val viewList = ArrayList<UpdateView<out MonobehaviourActor>>();
    fun registerView(view : UpdateView<out MonobehaviourActor>){
        viewList.add(view);
    }

    /**
     * Override된 기능. 원래의 update를 실행 후 종속된 View들의 컨테이너들도 함께 업데이트한다.
     * Update-propagate 기능임. 주의 : Thread-safe하지 않음.
     * */
    override fun update(){
        super.update();
        for(view in viewList){
            view.update();
        }
    }

}