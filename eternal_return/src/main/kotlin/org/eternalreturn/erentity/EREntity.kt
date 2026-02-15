package org.eternalreturn.erentity

import org.eternalreturn.ercharacter.character.fiora.ToucheCount
import org.eternalreturn.ercharacter.character.fiora.ToucheEffect
import org.eternalreturn.ercharacter.character.hart.Passive_Timer
import org.eternalreturn.ercharacter.character.isaac.PassiveCount
import org.eternalreturn.ercharacter.character.lidailin.LiDailinPassiveTimer
import org.eternalreturn.erentity.events.EREntityUpdateColliderEvent
import org.eternalreturn.erentity.globalmonobehav.Stun
import org.eternalreturn.erentity.globalmonobehav.UpdateCollider
import org.eternalreturn.system.PluginInstance
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import org.eternalreturn.util.dpengine.command.SetSpigotEntityPosition
import org.eternalreturn.util.dpengine.command.SetSpigotEntityVelocity
import org.eternalreturn.util.dpengine.geometry.Collider
import org.eternalreturn.util.dpengine.geometry.GeometryModule
import org.eternalreturn.util.dpengine.geometry.Vector3
import org.bukkit.entity.Entity
import kotlin.math.cos
import kotlin.math.sin

/**
 * 모든 EREntity의 Subclass에게 동시에 통용되는 성질을 저장하는 곳.
 *  - 생성자에 위험한 구문이 껴 있음. 그것만큼은 알아둘 것.
 *  - Entity는 처음에 init 되어있지 않음. 수동적으로 set 해주어야 함.
 */
abstract class EREntity( // extends MonobehaviourActor()

    val entity : Entity,
    /**
     * 해당 MonobehaviourActor의 Collider 설정
     */
    val collider: Collider
) : MonobehaviourActor() {


    val geometryModule : GeometryModule;

    init {


        /**이거 존나 위험한 구문임. 나중에 어떻게든 수정해야 할 것. */
        val monobehaviourModule = PluginInstance.getEREngine().monobehaviourModule;
        monobehaviourModule.register(this);
        this.monobehaviourModule = monobehaviourModule;
        geometryModule = monobehaviourModule.dpEngine.geometryModule;
        /**이거 존나 위험한 구문임. 나중에 어떻게든 수정해야 할 것.
         * 해당 구문의 책임은 DPEngine에게 위임해야 할 것.
         * */

        //Monobehaviour 등록
        this.registerMonobehaviour(Stun())
        this.registerMonobehaviour(ToucheCount())
        this.registerMonobehaviour(ToucheEffect())
        this.registerMonobehaviour(Passive_Timer())
        this.registerMonobehaviour(LiDailinPassiveTimer())
        this.registerMonobehaviour(PassiveCount())
        this.registerMonobehaviour(UpdateCollider())

        //콜라이더 위치, 각도를 업데이트하도록 지시. 한번만 submit하면 됨.
        submitEvent(EREntityUpdateColliderEvent())
    }

    /**
     * 플레이어의 위치벡터 + 방향벡터를 얻어온다.
     * No Scoping
     */

    fun getDirection(): Vector3 {
        val location = entity.location
        val radX = Math.toRadians(location.yaw.toDouble())
        val radY = Math.toRadians(location.pitch.toDouble())
        val xz = cos(radY)
        return this.geometryModule.vec3(-xz * sin(radX), -sin(radY), xz * cos(radX));
    }

    fun getPosition(): Vector3 {
        val location = entity.location
        return this.geometryModule.vec3(location.x, location.y, location.z)
    }

    fun setPosition(pos : Vector3) {
        val x = geometryModule.x(pos);
        val y = geometryModule.y(pos);
        val z = geometryModule.z(pos);
        this.geometryModule.dpEngine.appendCommandQueue(SetSpigotEntityPosition(entity!!, x, y, z))
    }

    fun setVelocity(pos : Vector3){
        val x = geometryModule.x(pos);
        val y = geometryModule.y(pos);
        val z = geometryModule.z(pos);
        this.geometryModule.dpEngine.appendCommandQueue(SetSpigotEntityVelocity(entity!!, x, y, z))
    }


}