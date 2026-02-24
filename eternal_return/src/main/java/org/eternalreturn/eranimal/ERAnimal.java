package org.eternalreturn.eranimal;

import org.eternalreturn.eranimal.animals.behavs.Battle;
import org.eternalreturn.eranimal.animals.behavs.Idle;
import org.eternalreturn.eranimal.animals.events.IdleEvent;
import org.eternalreturn.erentity.EREntity;
import org.eternalreturn.system.EREngine;
import org.eternalreturn.util.dpengine.DPEngine;
import org.eternalreturn.util.dpengine.geometry.Collider;
import org.eternalreturn.util.dpengine.geometry.OrientedBox;
import org.jetbrains.annotations.NotNull;

/**
 * MonobehaviourActor역할을 하는 야생동물 클래스.
 * */
public class ERAnimal extends EREntity {

    protected ERAJEntity ajEntity;
    /**
     * 야생동물 스킬 쿨다운
     * */
    protected long cooldownSeconds;

    public ERAnimal(@NotNull EREngine engine, @NotNull ERAJEntity ajEntity, @NotNull OrientedBox collider){
        super(engine, ajEntity.getRootEntity(), collider.getHalfX(), collider.getHalfY(), collider.getHalfZ());
        //System.out.println(collider.getClass());
        this.ajEntity = ajEntity;
        registerMonobehaviour(new Battle());
        registerMonobehaviour(new Idle());

        this.ajEntity.setDebugDisplay("T" + getTransformHandle().getEntityID() + " | O" + getObbHandle().getEntityID() + "\n\n\n\n");

        this.submitEvent(new IdleEvent());
    }

    public ERAJEntity getAJEntity(){
        return this.ajEntity;
    }

    public boolean isShown(){
        return this.ajEntity.isShown;
    }

}
