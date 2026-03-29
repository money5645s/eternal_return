package org.dpengine.monobehaviour

import org.dpengine.DPEngine
import org.dpengine.geometry.GeometryCalculatable
import java.lang.reflect.ParameterizedType


/**
 * 대부분의 수정 사항은 MonobehaviourActor.registerMonobehaviour()에서 이루어짐.
 *
 * 주의 : update 상태인 Monobehaviour은 구독 중인 Event를 전달받아도 강제로 start 상태로 넘어가지 않으며, update()가 stopMonobehav()로 인해 명시적으로 종료되었을 경우에만
 * 다시 start 상태로 넘어갈 수 있음. 이는 의도한 것임.
 *
 */
abstract class Monobehaviour<T : MonobehaviourEvent> protected constructor() : GeometryCalculatable() {
    open lateinit var eventType: Class<T>
    open lateinit var actor: MonobehaviourActor
    lateinit var dpEngine: DPEngine;

    var state: State = State.STOP

    val isRunning: Boolean
        get() = this.state == State.RUNNING

    /**
     * RUNNING : update가 실행되는 상태
     * STOP : update가 종료된 상태
     */
    enum class State { START, RUNNING, STOP }

    /**
     * MonobehaiourActor.registerMonobehaviour()의 인자로 넣기 위해서만 instantiate할 것.
     */
    init {
        this.eventType = __getGenericClass()
    }

    private fun __getGenericClass() : Class<T>{
        //Generic의 superClass를 얻어옴 (CharacterEvent)
        val superType = javaClass.getGenericSuperclass()

        //superType을 ParameterizedType으로 캐스팅하여 제네릭 정보를 얻어오기 위한 준비를 함.
        check(superType is ParameterizedType) { "Monobehaviour must be directly parameterized" }
        //바로 T에 대한 정보를 가져온다.
        return superType.actualTypeArguments[0] as Class<T>
        //System.out.println(eventType);
    }


    fun dispatchEvent(event: MonobehaviourEvent) {
        this.state = State.START;
        check(eventType.isInstance(event)) { "Wrong event type: " + event.javaClass };
        startMonobehav(eventType.cast(event));
        //println("DispatchedEvent : " + event);
    }

    fun startMonobehav(event: T) {
        dpEngine.matVecCalculator.setVecScope().use { scope ->
            start(event)
        }
    }

    /**
     * 해당 Monobehaviour가 구독중인 Event가 update 중에 들어온 경우에 true로 설정됨.
     * */
    var gotSubscribedEvent = false;
    fun updateMonobehav(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {
        this.state = State.RUNNING;
        val eventClass = __getGenericClass();
        gotSubscribedEvent = (eventMap[eventClass] != null)
        dpEngine.matVecCalculator.setVecScope().use { scope ->
            update(eventMap)
        }
    }

    abstract fun start(event: T)
    abstract fun update(eventMap: Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>)

    /**
     * MonobehaviourActor 내 스케줄러로 하여금 해당 Monobehaviour을 제거하도록 마킹합니다. <br></br>
     * 스케줄러에서 제거된 Monobehaviour은 update() 대상에서 제외됩니다. <br></br>
     * 다시 update()를 재개시키려면 해당 Monobehaviour에 맞는 MonobehaviourEvent를 해당 객체에 submit()해야 합니다.
     */
    open fun stopMonobehav() {
        this.state = State.STOP
    }

    fun runMonobehav() {
        this.state = State.RUNNING
    }

    /**
     * 해당 Monobehaviour을 실행할 Actor을 적용함과 동시에 <br></br>
     * 해당 Monobehaviour에서 사용할 GeometryEngine을 적용하는 함수 <br></br>
     * (MonobehaviourActor 과 Monobehaviour은 같은 엔진을 공유하고 있어야 함)
     */
    fun setMonobehaviourActor(actor: MonobehaviourActor) {
        this.actor = actor
        this.dpEngine = actor.monobehaviourModule.dpEngine
        geometryCalculatableInit(dpEngine.matVecCalculator)
    }

    override fun equals(other: Any?): Boolean {
        if(other is Monobehaviour<T>){
            return other.javaClass == this.javaClass
        }
        return false;
    }

    override fun hashCode(): Int {
        return this.javaClass.hashCode()
    }

}