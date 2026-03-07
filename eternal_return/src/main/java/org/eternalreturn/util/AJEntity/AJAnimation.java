package org.eternalreturn.util.AJEntity;

/**
 * 해당 AJEntity가 실행할 수 있는 애니메이션 정보를 저장한다. <br>
 * AJEntity의 생성과 동시에 한번만 생성되며, AJEntity.animationMap에 등록된다.
 * */
public record AJAnimation(
    String animation,
    long durationTicks){
    public AJAnimation(String animation, long durationTicks){
        this.animation = animation;
        this.durationTicks = durationTicks;
    }
}