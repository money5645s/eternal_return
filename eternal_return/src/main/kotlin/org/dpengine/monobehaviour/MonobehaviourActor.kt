package org.dpengine.monobehaviour

import org.dpengine.DPEngine
import org.dpengine.datastructure.ArrayListSet
import org.dpengine.event.MonobehavCreatedEvent
import org.dpengine.geometry.MatVecCalculator
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

    val matVecCalculator: MatVecCalculator
        get() = dpEngine.matVecCalculator


    open var referenceCount = 0;
    /**
     * DPEngine상에서 계속 사용되는 객체라면 true, 더 이상 사용되지 않는 객체라면 false를 반환한다.
     * */
    inline fun isAlive() : Boolean {
        return referenceCount >= 1;
    }

    /**
     * 해당 객체의 레퍼런스 카운트를 0으로 설정하여 다음 틱 때 제거되도록 함.
     * */
    open fun remove(){
        this.referenceCount = 0;
        monobehaviourModule.monobehaviourActorList.setModified();
    }
    fun refer(){
        this.referenceCount++;
    }

    var lateinitIsDone = false;
    abstract fun lateinit();

    /**
     * (MonobehaviourEvent, Monobehaviour)의 키 쌍
     */
    val monobehaviourMap: HashMap<Class<out MonobehaviourEvent>, Monobehaviour<out MonobehaviourEvent>> =
        HashMap<Class<out MonobehaviourEvent>, Monobehaviour<out MonobehaviourEvent>>()



    /**
     * 제출된 이벤트를 담는 큐 ( Array<ArrayDeque<MonobehaviourEvent>>> )
     */
    val submittedEvent = Array<ArrayDeque<MonobehaviourEvent>>(2){ArrayDeque<MonobehaviourEvent>()};
    var curEventIdx = 0;

    /**
     * 외부에서 해당 객체에게 이벤트를 제출하기 위한 창구
     */
    fun submitEvent(event: MonobehaviourEvent) {
        if(isAlive()){
            //println("Event 제출됨 : ${event.javaClass.simpleName} \t-> ${this.javaClass.simpleName}");
            submittedEvent[curEventIdx xor 1].addLast(event)
            monobehaviourModule.submitActorWhoWasSubmitedEvent(this)
        }
    }

    private val eventLessArg = EventLess();
    private val submittedMonobehav = Array(2){HashSet<Monobehaviour<EventLess>>()};
    private var curMonobehavIdx = 0;
    /**
     * 실행될 임시 Monobehaviour<EventLess>을 제출한다.
     */
    fun sumbitMonobehav(monobehaviour: Monobehaviour<EventLess>){
        val nextSet = submittedMonobehav[curMonobehavIdx xor 1]
        if(isAlive() && !runningBehaviours.contains(monobehaviour) && !nextSet.contains(monobehaviour)){
            //println("Monobehav 제출됨 : ${monobehaviour.javaClass.simpleName} \t-> ${this.javaClass.simpleName}");
            nextSet.add(monobehaviour);
            monobehaviour.setMonobehaviourActor(this);
            monobehaviourModule.submitActorWhoWasSubmitedEvent(this)
        }
    }

    /**
     * update에 매개변수로써 제출하기 위한 이벤트 맵
     */
    protected var checkedEvent = HashMap<Class<out MonobehaviourEvent>, MonobehaviourEvent>()

    /**
     * 이전 틱에서 제출된 Event들을 소비하는 함수.
     * MonobehaviourModule에 의해서만 호출되어야 하며, 이외의 위치에서 호출됨은 절대 허용하지 아니함.
     * */
    internal fun __dispatchEvents() {
        //바라보고 있는 큐를 교체한다.
        curEventIdx = curEventIdx xor 1;
        val currentEventQueue = submittedEvent[curEventIdx];

        while (currentEventQueue.isNotEmpty()) {
            val event = currentEventQueue.removeFirst()
            val monobehav = monobehaviourMap[event.javaClass]
            if (monobehav != null && (monobehav.state == Monobehaviour.State.STOP)) {
                runningBehaviours.add(monobehav)
                monobehav.dispatchEvent(event)
                //println("Event 소비됨 : ${event.javaClass.simpleName} \t<- ${this.javaClass.simpleName}");
            }
            checkedEvent.putIfAbsent(event.javaClass, event)
        }
        currentEventQueue.clear();
    }

    /**
     * 이전 틱에서 제출된 Monobehaviour들을 소비하는 함수.
     * MonobehaviourModule에 의해서만 호출되어야 하며, 이외의 위치에서 호출됨은 절대 허용하지 아니함.
     * */
    internal fun __dispatchMonobehavs(){
        //바라보고 있는 큐를 교체한다.
        curMonobehavIdx = curMonobehavIdx xor 1;
        val currentSubmittedMonobehav = submittedMonobehav[curMonobehavIdx];

        //해당 큐 내에 있는 Monobehav를 순차적으로 스케줄링한다.
        for(monobehav in currentSubmittedMonobehav){
            if (monobehav.state == Monobehaviour.State.STOP){
                //println("${monobehav.javaClass.simpleName} 가 스케줄링 되었습니다.")
                runningBehaviours.add(monobehav)
                monobehav.dispatchEvent(eventLessArg);
            }
        }
        currentSubmittedMonobehav.clear();

    }

    /**
     * update(MonobehaviourEvent)를 호출할 Monobehaviour들을 스케줄링하기 위해 유지하는 링크드 리스트
     */
    val runningBehaviours = ArrayListSet<Monobehaviour<out MonobehaviourEvent>>();

    /**
     * 해당 Actor의 Monobehaviour을 update()함.
     * 만약 한개의 Monobehaviour도 Running이 아니라면 false를 반환
     * 그 외에는 true를 반환
     */
    internal fun __updateMonobehaviour(): Boolean {

        if(!isAlive()){
            return false;
        }

        if (runningBehaviours.isEmpty()) {
            return false
        }

        //monobehaviour update() 스케줄링
        var idx = 0;
        while (idx < runningBehaviours.size) {
            val monobehaviour = runningBehaviours[idx];
            if (monobehaviour.state == Monobehaviour.State.STOP) {
                //println("${monobehaviour.javaClass.simpleName} is now STOP")
                runningBehaviours.remove(idx); //remove 시에는 idx를 올리면 안됨.
                continue;
            }
            monobehaviour.updateMonobehav(checkedEvent)
            idx++;
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
        if (this.monobehaviourMap[monobehaviour.eventType] != null) {
            throw DuplicatedMonobehaviourRegisterException("Key " + monobehaviour.eventType + " is duplicated.")
        }
        this.monobehaviourMap.put(monobehaviour.eventType, monobehaviour)
        monobehaviour.setMonobehaviourActor(this)
    }


    init{
        dpEngine.monobehaviourModule.register(this);
        this.submitEvent(MonobehavCreatedEvent())
    }

}

