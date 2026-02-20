package org.eternalreturn.util.dpengine.behaviour

import org.eternalreturn.util.dpengine.DPEngine
import org.eternalreturn.util.dpengine.geometry.GeometryModule
import java.util.*

/**
 * Monobehaviour을 실행하기 위한 객체.
 *
 *
 * (MonobehaviourEvent, Monobehaviour)의 키 쌍을 유지한다.
 *
 *
 * 해당 객체로 이벤트가 전달되면, 전달된 이벤트들을 소비하여 정해진 Monobehaviour의 로직을 실행한다.
 *
 *
 * 해당 객체로 이벤트를 전달하고자 한다면
 *
 *
 * `MonobehaviourActor.submitEvent(MonobehaviourEvent)`
 *
 *
 * 메소드를 사용할 것.
 *
 *
 */
abstract class MonobehaviourActor(
    val dpEngine: DPEngine
) {
    val monobehaviourModule: MonobehaviourModule
        get() = dpEngine.monobehaviourModule

    val geometryModule: GeometryModule
        get() = dpEngine.geometryModule

    init{
        dpEngine.monobehaviourModule.register(this);
    }

    open var referenceCount = 0;

    inline fun isAlive() : Boolean {
        return referenceCount >= 1;
    }

    /**
     * 해당 객체의 레퍼런스 카운트를 0으로 설정하여 다음 틱 때 제거되도록 함.
     * */
    inline fun remove(){
        this.referenceCount = 0;
    }
    fun dereference(){
        this.referenceCount--;
    }
    fun refer(){
        this.referenceCount++;
    }


    /**
     * (MonobehaviourEvent, Monobehaviour)의 키 쌍
     */
    open var monobehaviourMap: HashMap<Class<out MonobehaviourEvent>, Monobehaviour<out MonobehaviourEvent>> =
        HashMap<Class<out MonobehaviourEvent>, Monobehaviour<out MonobehaviourEvent>>()

    /**
     * 제출된 엔티티를 담는 큐 ( LinkedList )
     */
    open var submittedEvent: ArrayDeque<MonobehaviourEvent> = ArrayDeque<MonobehaviourEvent>()

    /**
     * update(MonobehaviourEvent)를 호출할 Monobehaviour들을 스케줄링하기 위해 유지하는 링크드 리스트
     */
    open var runningBehaviours: LinkedList<Monobehaviour<out MonobehaviourEvent>> = LinkedList<Monobehaviour<out MonobehaviourEvent>>()


    /**
     * 외부에서 해당 객체에게 이벤트를 제출하기 위한 창구
     */
    fun submitEvent(event: MonobehaviourEvent) {
        println("Event submitted : ${event.javaClass.simpleName} \tto ${this.javaClass.simpleName}");
        submittedEvent.add(event)
        monobehaviourModule.submitActorWhoTriggeredEvent(this)
    }

    /**
     * 제출된 이벤트 디큐에서 하나씩 빼면서 dispatch,
     * 해당 이벤트는 다시 checkedEvent 내에 삽입됨.
     * 실행중인 Monobehaviour이면 무시됨.
     */
    protected var checkedEvent: ArrayDeque<MonobehaviourEvent> = ArrayDeque<MonobehaviourEvent>()

    fun dispatchEvents() {
        while (!submittedEvent.isEmpty()) {
            val event = submittedEvent.removeFirst()
            val monobehav = monobehaviourMap.get(event.javaClass)
            //System.out.println(event.getClass());
            if (monobehav != null && !monobehav.isRunning){
                runningBehaviours.add(monobehav)
                println("Event consumed :  ${event.javaClass.simpleName} \ton ${this.javaClass.simpleName}");
                monobehav.dispatchEvent(event)
            }
            checkedEvent.addLast(event)
        }
    }

    /**
     * 해당 Actor의 Monobehaviour을 update()함.
     * 만약 한개의 Monobehaviour도 Running이 아니라면 false를 반환
     * 그 외에는 true를 반환
     */
    fun updateMonobehaviour(): Boolean {
        if (runningBehaviours.isEmpty()) {
            return false
        }

        //println("Updating monobehaviours : ${runningBehaviours.size}");

        //monobehaviour update() 스케줄링
        val monobehavNode = runningBehaviours.iterator()
        while (monobehavNode.hasNext()) {
            val monobehaviour: Monobehaviour<*> = monobehavNode.next()
            monobehaviour.updateMonobehav(checkedEvent)

            if (!monobehaviour.isRunning) {
                monobehavNode.remove()
                println("removed : " + monobehaviour.javaClass.simpleName);
            }
        }
        checkedEvent.clear()
        return true
    }

    val thereAreNoRunningMonobehaviours: Boolean
        get() = runningBehaviours.isEmpty()

    /**
     * 해당 이벤트 클래스에 맞는 (MonobehaviourEvent, Monobehaviour) 쌍을 저장한다.
     * 또한 해당 Monobehaviour의 DPEngine객체 또한 저장한다.
     */
    protected fun registerMonobehaviour(monobehaviour: Monobehaviour<*>) {
        if (this.monobehaviourMap.get(monobehaviour.eventType) != null) {
            throw DuplicatedMonobehaviourRegisterException("Key " + monobehaviour.eventType + " is duplicated.")
        }
        this.monobehaviourMap.put(monobehaviour.eventType, monobehaviour)
        monobehaviour.setMonobehaviourActor(this)
        //System.out.println("register event " + monobehaviour.getClass());
    }

}