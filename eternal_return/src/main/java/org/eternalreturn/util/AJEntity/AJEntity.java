package org.eternalreturn.util.AJEntity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.HashMap;


/**
 *
 * */
public abstract class AJEntity{

    private static final String NO_ANIM = "NO_ANIM";

    protected Location location;

    public enum ANIMATION_STATE{
        PLAY,
        PAUSE,
        STOP
    };

    protected Entity rootEntity;

    protected String name;

    protected HashMap<String, AJAnimation> animationMap;

    /**
     * 현재 실행 중인 애니메이션의 상태 <br>
     * ANIMATION_STATE.PLAY, ANIMATION_STATE.PAUSE, ANIMATION_STATE.STOP
     * */
    protected ANIMATION_STATE animationState;

    /**
     * 현재 실행 중인 애니메이션의 이름<br>
     * registerAnimation(String,String) 메소드를 통해 등록해야 함.
     * */
    protected String animationPlaying = NO_ANIM;

    protected long animationEndTime;

    public AJEntity(String name, Location location) {
        this.name = name;
        this.location = location;
        this.animationMap = new HashMap<>();
    }

    protected abstract void afterSummoning();

    protected abstract void afterSpawnEvent(Entity spawnedRootEntity);

    /**
     * 해당 월드에서 AJEntity를 제거한다. <br>
     * AJEntity.remove(AJEntity ajEntity, Entity rootEntity){...}를 내부적으로 호출한다.<br>
     *
     * */
    public void remove(){
        AJEntityManager.sendCommand(getExecuteAsRunFuncPrefix() + "animated_java:" + this.name + "/remove/this");
    }

    /**
     * 애니메이션을 등록하는 메소드.<br>
     * HashMap<>에 다음과 같이 등록된다
     * <code>
     *     .put(animationState,"animated_java:"+this.name+"/animations/"+animationState);
     * <code/>
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
     * <code>
     *     .put(animationState,"animated_java:"+this.name+"/animations/"+animationState);
     * <code/>
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

        long currentTime = System.currentTimeMillis();
        if(animationEndTime > currentTime){
            return;
        }

        AJAnimation acb = this.animationMap.get(selectedAnimation);
        long durationTicks = acb.durationTicks();
        String animation = acb.animation();

        if(animation.equals(NO_ANIM)){
            throw new AJAnimationNotFoundException(
                    "AJAnimation is not found : \"" + selectedAnimation + "\"\n"
                            +"Solution : Check the animated_java project name and your JAVA code");
        }
        __setAnim(animation, durationTicks, currentTime);
    }

    //요청받은 애니메이션을 설정 & 실행한다.
    private void __setAnim(String animation, long durationTicks, long currentTime)throws AJAnimationNotFoundException{
        //현재 실행하는 애니메이션의 이름과 경로
        this.animationPlaying = animation;
        this.animationEndTime = durationTicks * 50 + currentTime;
        this.animationState = ANIMATION_STATE.PLAY;
        String command = getExecuteAsRunFuncPrefix() + this.animationPlaying + "/play";
        AJEntityManager.sendCommand(command);
    }

    /**
     * 실행 중인 해당 애니메이션을 일시 정지한다.
     * getAnimationState()메소드로 얻을 수 있는 값은 ANIMATION_PAUSE의 값이 된다.
     * */
    public void pauseAnim(){
        this.animationState = ANIMATION_STATE.PAUSE;
        AJEntityManager.sendCommand(getExecuteAsRunFuncPrefix() + animationPlaying + "/pause");
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
            AJEntityManager.sendCommand(getExecuteAsRunFuncPrefix() + animationPlaying + "/stop");
            this.animationState = ANIMATION_STATE.STOP;
            this.animationPlaying = NO_ANIM;
        }
    }

    /**
     * 다음 문자열을 반환한다.
     * @return "execute as "+ rootEntity.getUniqueId() +" run function "
     * */
    private String getExecuteAsRunFuncPrefix(){
        return "execute as "+ rootEntity.getUniqueId() +" run function ";
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

    public void setRootEntity(Entity entity){
        this.rootEntity = entity;
    }


}
