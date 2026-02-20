package org.eternalreturn.eranimal;

import org.eternalreturn.eranimal.animals.behavs.Battle;
import org.eternalreturn.eranimal.animals.behavs.Idle;
import org.eternalreturn.eranimal.animals.events.IdleEvent;
import org.eternalreturn.erentity.EREntity;
import org.eternalreturn.util.dpengine.DPEngine;
import org.eternalreturn.util.dpengine.geometry.Collider;
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

    public ERAnimal(@NotNull DPEngine engine,@NotNull ERAJEntity ajEntity, @NotNull Collider collider){
        super(engine, ajEntity.getActor(), collider);
        //System.out.println(collider.getClass());
        this.ajEntity = ajEntity;
        registerMonobehaviour(new Battle());
        registerMonobehaviour(new Idle());

        this.submitEvent(new IdleEvent());
    }

    public ERAJEntity getAJEntity(){
        return this.ajEntity;
    }

    public boolean isShown(){
        return this.ajEntity.isShown;
    }

}
