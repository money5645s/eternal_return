package org.eternalreturn.eranimal.animals.behavs;

import org.eternalreturn.eranimal.ERAJEntity;
import org.eternalreturn.eranimal.ERAnimalMonobehaviour;
import org.eternalreturn.eranimal.animals.events.ERAnimalAttackedByPlayerEvent;
import org.eternalreturn.eranimal.animals.events.ERAnimalReadyEvent;
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent;

import java.util.Collection;

public class Ready extends ERAnimalMonobehaviour<ERAnimalReadyEvent> {

    private ERAJEntity ajEntity;

    @Override
    public void start(ERAnimalReadyEvent event) {
        ajEntity = getERAJEntity();
    }

    @Override
    public void update(Collection<MonobehaviourEvent> eventList) {

        for(MonobehaviourEvent event : eventList){

            if(event instanceof ERAnimalAttackedByPlayerEvent){
                ajEntity.stopAnim();
                stopMonobehav();
                return;
            }

            if(event instanceof ERAnimalReadyEvent){
                ajEntity.playAnim("ready");
            }

        }

    }

}
