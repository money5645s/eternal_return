package org.eternalreturn.util.AJEntity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.scoreboard.Objective;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;


/**
 *
 * */
public abstract class AJEntity{

    private static final String NO_ANIM = "NO_ANIM";

    protected Location location;

    protected long animationStartTime;

    public enum ANIMATION_STATE{
        PLAY,
        PAUSE,
        STOP
    };

    protected ItemDisplay rootEntity;

    protected String name;

    protected HashMap<String, AJAnimation> animationMap;

    /**
     * 현재 실행 중인 애니메이션의 상태 <br>
     * <blockquote><pre>
     * ANIMATION_STATE.PLAY, ANIMATION_STATE.PAUSE, ANIMATION_STATE.STOP
     * </pre></blockquote>
     * */
    protected ANIMATION_STATE animationState;

    /**
     * 현재 실행 중인 애니메이션의 이름
     * <blockquote><pre>
     * registerAnimation(String,String)
     * </pre></blockquote>메소드를 통해 등록해야 함.
     * */
    protected String animationPlaying = NO_ANIM;

    protected long animationEndTime;

    public AJEntity(String name, Location location) {
        this.name = name;
        this.location = location;
        this.animationMap = new HashMap<>();
    }

    protected abstract void afterSummoning();

    protected abstract void afterSpawnEvent(ItemDisplay spawnedRootEntity);

    /**
     * 해당 월드에서 AJEntity를 제거한다. <br>
     * <blockquote><pre>
     *     AJEntity.remove(AJEntity ajEntity, Entity rootEntity){...}
     * </pre></blockquote>
     * 를 내부적으로 호출한다.<br>
     * */
    public void remove(){
        AJEntityManager.sendCommand(getExecuteAsRunFuncPrefix() + "animated_java:" + this.name + "/remove/this");
        EXECUTE_AS_UUID_RUN_FUNCTION = null;
    }

    /**
     * 애니메이션을 등록하는 메소드.<br>
     * HashMap<>에 다음과 같이 등록된다
     * <blockquote><pre>
     *     .put(animationState,"animated_java:"+this.name+"animations"+animationState);
     * </pre></blockquote>
     * */
    public void registerAnimation(String animationState,double durationSeconds){
        animationMap.put(
                animationState,
                new AJAnimation(
                         "animated_java:"+this.name+"/animations/"+animationState,
                        (long)durationSeconds * 20
                )
        );
    }

    /**
     * 애니메이션을 등록하는 메소드.<br>
     * HashMap<>에 다음과 같이 등록된다
     * <blockquote><pre>
     *     .put(animationState,"animated_java:"+this.name+"animations"+animationState);
     * </pre></blockquote>
     * */
    public void registerAnimation(String animationState,long durationTicks){
        animationMap.put(
                animationState,
                new AJAnimation(
                        "animated_java:"+this.name+"/animations/"+animationState,
                        durationTicks
                )
        );
    }

    /**
     * 현재 실행되고 있는 애니메이션의 프레임에 대응되는 tick 값을 해당 함수의 호출 시점 기준으로 쿼리한다.
     * <p>
     * 아래 상황에서 반환값은 2이다.
     *
     * <blockquote><pre>
     *     Animated JAVA   :                  v playing()
     *     Animation frame : [frame0][frame1][frame2][frame3][frame4][frame5]
     *     this function   :                  ^ call()
     *     ticks           :  0       1       2       3       4       5
     * </pre></blockquote>
     *
     * */
    public int getCurrentTicks(){
        if(this.rootEntity == null){
            return -1;
        }
        return (int)(System.currentTimeMillis() - this.animationStartTime) / 50;
    }

    /**
     * 애니에이션을 실행하는 메소드 <br>
     * 현재 이 메소드를 통해 실행하려는 애니메이션과 실행 중인 애니메이션이 같은 경우, 아무 일도 일어나지 않음. <br>
     * 다를 경우. 해당 애니메이션으로 바로 진행됨. <br>
     * @throws : AJAnimationNotFoundException
     * */
    public void playAnimForce(String selectedAnimation)throws AJAnimationNotFoundException{

        AJAnimation acb = this.animationMap.get(selectedAnimation);
        long durationTicks = acb.durationTicks();
        long currentTime = System.currentTimeMillis();
        String animation = acb.animation();

        if(animation.equals(NO_ANIM)){
            throw new AJAnimationNotFoundException(
                    "AJAnimation is not found : \"" + selectedAnimation + "\"\n"
                            +"Solution : Check the animated_java project name and your JAVA code");
        }

        if(animationEndTime > currentTime && animation.equals(animationPlaying)){
            return;
        }

        __setAnim(animation, durationTicks, currentTime);
    }

    /**
     * 애니에이션을 실행하는 메소드 <br>
     * 현재 이 메소드를 통해 실행하려는 애니메이션과 실행 중인 애니메이션이 같은 경우, 아무 일도 일어나지 않음. <br>
     * 다를 경우. 해당 애니메이션이 다 끝난 후에 다음 애니메이션 실행을 허용함. <br>
     * @throws : AJAnimationNotFoundException
     * */
    public void playAnim(String selectedAnimation)throws AJAnimationNotFoundException{

        AJAnimation acb = this.animationMap.get(selectedAnimation);
        long currentTime = System.currentTimeMillis();
        long durationTicks = acb.durationTicks();
        String animation = acb.animation();

        if(animationEndTime > currentTime){
            return;
        }

        if(animation.equals(NO_ANIM)){
            throw new AJAnimationNotFoundException(
                    "AJAnimation is not found : \"" + selectedAnimation + "\"\n"
                            +"Solution : Check the animated_java project name and your JAVA code");
        }

        __setAnim(animation, durationTicks, currentTime);
    }

    //요청받은 애니메이션을 설정 & 실행한다.
    private StringBuilder cmdBuilder = new StringBuilder();
    private void __setAnim(String animation, long durationTicks, long currentTime)throws AJAnimationNotFoundException{
        //현재 실행하는 애니메이션의 이름과 경로
        this.animationPlaying = animation;
        this.animationEndTime = durationTicks * 50 + currentTime;
        this.animationStartTime = currentTime;
        this.animationState = ANIMATION_STATE.PLAY;
        cmdBuilder.append(getExecuteAsRunFuncPrefix()).append(this.animationPlaying).append("/play");
        AJEntityManager.sendCommand(cmdBuilder.toString());
        cmdBuilder.delete(0, cmdBuilder.length());
    }

    /**
     * 실행 중인 해당 애니메이션을 일시 정지한다.
     * getAnimationState()메소드로 얻을 수 있는 값은 ANIMATION_PAUSE의 값이 된다.
     * */
    public void pauseAnim(){
        this.animationState = ANIMATION_STATE.PAUSE;
        cmdBuilder.append(getExecuteAsRunFuncPrefix()).append(this.animationPlaying).append("/pause");
        AJEntityManager.sendCommand(cmdBuilder.toString());
        cmdBuilder.delete(0, cmdBuilder.length());
    }

    /**
     * 현재 애니메이션의 종류와는 무관하게 애니메이션이 실행 중인지만 파악하는 쿼리함수.
     * */
    public boolean isCurrentAnimEnd(){
        return animationEndTime < System.currentTimeMillis();
    }

    /**
     * 해당하는 애니메이션이 실행 중인지 확인하는 쿼리함수
     * */
    public boolean isPlaying(String animation){
        AJAnimation acb = this.animationMap.get(animation);
        boolean thisAnimIsExist = acb.animation().equals(this.animationPlaying);
        boolean thisAnimIsNotEnd = !isCurrentAnimEnd();
        return  thisAnimIsNotEnd && thisAnimIsExist;
    }

    /**
     * 실행 중인 해당 애니메이션을 완전히 끈다.<br>
     * getAnimationPlaying()메소드로 얻을 수 있는 값은 NO_ANIM 이 되며,<br>
     * getAnimationState()메소드로 얻을 수 있는 값은 ANIMATION_STOP의 값이 된다.
     * */
    public void stopAnim(){
        if(!animationPlaying.equals(NO_ANIM) && animationState == ANIMATION_STATE.PLAY){

            cmdBuilder.append(getExecuteAsRunFuncPrefix()).append(this.animationPlaying).append("/stop");
            AJEntityManager.sendCommand(cmdBuilder.toString());
            cmdBuilder.delete(0, cmdBuilder.length());

            this.animationState = ANIMATION_STATE.STOP;
            this.animationPlaying = NO_ANIM;
        }
    }

    /**
     * 다음 문자열을 반환한다.
     * <p>
     * "execute as "+ rootEntity.getUniqueId() +" run function "
     * <p>
     * */
    private static String EXECUTE_AS_UUID_RUN_FUNCTION = null;
    private String getExecuteAsRunFuncPrefix(){
//        if(EXECUTE_AS_UUID_RUN_FUNCTION == null){
//            EXECUTE_AS_UUID_RUN_FUNCTION = "execute as " + rootEntity.getUniqueId() + " run function ";
//        }
//        return EXECUTE_AS_UUID_RUN_FUNCTION;
        return "execute as " + rootEntity.getUniqueId() + " run function ";
    }

    //getter

    /**
     * 현재 실행 중인 애니메이션의 상태를 얻어온다.
     * */
    public ANIMATION_STATE getAnimationState(){
        return this.animationState;
    }

    /**
     * 현재 실행 중인 애니메이션의 이름(name)을 얻어온다.
     * */
    public String getAnimationPlaying(){
        return this.animationPlaying;
    }

    /**
     * 현재 AJEntity의 이름을 얻어온다.
     * */
    public String getName(){
        return this.name;
    }

    /**
     * 현재 AJEntity의 rootEntity를 얻어온다.
     * */
    public Entity getRootEntity(){
        return this.rootEntity;
    }

    public boolean isValid(){
        return (this.rootEntity != null);
    }

    /**
     * 현재 AJEntity가 처리되고 있는 World를 가져온다.
     * */
    public Location getLocation(){
        return this.location;
    }

    //setter

    public void setRootEntity(ItemDisplay entity){
        this.rootEntity = entity;
    }


}
