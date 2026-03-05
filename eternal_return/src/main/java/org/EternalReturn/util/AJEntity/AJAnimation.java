package org.eternalreturn.util.AJEntity;

public record AJAnimation(
    String animation,
    long durationTicks){
    
    public AJAnimation(String animation, long durationTicks){
        this.animation = animation;
        this.durationTicks = durationTicks;
    }
}