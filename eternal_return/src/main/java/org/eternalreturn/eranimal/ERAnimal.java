package org.eternalreturn.eranimal;

import org.eternalreturn.eranimal.animals.behavs.Battle;
import org.eternalreturn.eranimal.animals.behavs.Ready;
import org.eternalreturn.eranimal.animals.events.ERAnimalReadyEvent;
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
        registerMonobehaviour(new Ready());
        registerMonobehaviour(new Battle());

    }

    public ERAJEntity getAJEntity(){
        return this.ajEntity;
    }

    public boolean isShown(){
        return this.ajEntity.isShown;
    }

}
