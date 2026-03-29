package org.eternalreturn.ercharacter

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.eternalreturn.ercharacter.datastructure.CoolTableSeconds
import org.eternalreturn.erentity.EREntityMonobehaviour
import org.eternalreturn.erentity.events.EREntityEvent
import org.eternalreturn.erplayer.DPlayer
import org.dpengine.monobehaviour.MonobehaviourEvent

abstract class ERCharacterMonobehaviour<T : EREntityEvent> : EREntityMonobehaviour<T>() {
    val erCharacter: DPCharacter
        get() = actor as DPCharacter
    val erPlayer: DPlayer
        get() = actor as DPlayer
    val player: Player
        get() = erPlayer.entity as Player
}

/**
 * 외부 캐릭터의 getLevel() 메소드를 가져와 쿨다운을 관리하는 객체
 *
 * */
class CooldownContext(
    @JvmField val cooldownListForEachLevel : CoolTableSeconds,
){
    inline val level : Int get() = cooldownListForEachLevel.getLevel()
    @JvmField var cooldown : Long = 0;
    fun resetCooldown(){
        cooldown = cooldownListForEachLevel.get()
        //println("currentLevel = ${getLevel()}, cooldown = ${cooldown}, cooldown = ${cooldownListForEachLevel[getLevel()]}")
    }
}


abstract class ERCharacterSkillMonobehaviour<T : EREntityEvent, E : DPCharacter>(
    val cooldownCtx : CooldownContext, val durationTicks : Long, val scoreboardNameToMonitor : String = "UNNAMED"
) : EREntityMonobehaviour<T>(){

    @Suppress("UNCHECKED_CAST")
    val player: E
        get() = actor as E


    val objToMonitor = Bukkit.getScoreboardManager().mainScoreboard.getObjective(scoreboardNameToMonitor)

    override fun start(event: T) {
        if(cooldownCtx.cooldown > 0 || cooldownCtx.level == -1){
            stopMonobehav();
            return;
        }
        skillStart(event);
    }abstract fun skillStart(event : T);

    enum class State(val idx : Int){
        COOLDOWN(0),
        SKILL_PROCESS(1),
    }

    private var skillState = State.SKILL_PROCESS;

    private var skillTicks = 0;
    val t : Int get() = skillTicks;
    override fun update(eventMap : Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>) {

        if(skillState == State.COOLDOWN){

            if(cooldownCtx.cooldown == 0L){
                super.stopMonobehav();
                skillState = State.SKILL_PROCESS
                return;
            }
            cooldownCtx.cooldown--;
            objToMonitor?.getScoreFor(entity)?.score = (cooldownCtx.cooldown).toInt();

        }
        else if(skillState == State.SKILL_PROCESS){
            if(skillTicks >= durationTicks){
                interruptSkill();
                return;
            }
            skillUpdate(eventMap);// 얘를 어떻게 하는 방법을 알아야 할 텐데
            skillTicks++;
        }
    }abstract fun skillUpdate(eventMap : Map<Class<out MonobehaviourEvent>, MonobehaviourEvent>)

    /**
     * 스킬의 진행 자체를 끄고 쿨다운 상태로 되돌리기 위한 stopMonobehav()의 호출은 예상치 못한 결과를 초래할 수 있음.
     * 대신 ```interruptSkill()``` 메소드를 사용할 것.
     * */
    override fun stopMonobehav(){
        interruptSkill();
    }

    /**
     * 해당 스킬을 완전히 끝낸 후, 쿨다운 상태로 되돌린다.
     * */
    fun interruptSkill(){
        skillTicks = 0;
        skillState = State.COOLDOWN;
        cooldownCtx.resetCooldown();
    }

}

