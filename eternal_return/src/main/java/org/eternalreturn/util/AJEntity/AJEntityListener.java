package org.eternalreturn.util.AJEntity;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.server.ServerCommandEvent;

import static org.eternalreturn.util.AJEntity.AJEntityManager.*;

public class AJEntityListener implements Listener {

    //Listener code
    @EventHandler
    public void event(EntitySpawnEvent e){

        Entity entity = e.getEntity();

        //ajEntitySummonQueue가 비어있는지 확인한다.
        //비어있지 않는 경우, 해당 엔티티가 root entity 인지 확인한다.
        // root entity 라면, ajEntityMap에 등록한다.

        if(ajEntitySummonQueue.isEmpty()){
            return;
        }

        if(entity instanceof ItemDisplay root && AJEntityManager.isAJEntityRoot(entity)){
            //System.out.println(entity);

            AJEntity ajEntity = ajEntitySummonQueue.removeLast();

            //System.out.println(ajEntity +" 에서 소환 명세를 받았습니다. 길이 : " + (ajEntitySummonQueue.size() + 1));

            assert ajEntity != null : "ajEntity가 Null 입니다. AJEntityManager.summon(AJEntity, Location) AJEntity에 잘못된 값이 들어갔습니다.";

            ajEntityMap.put(root, ajEntity);
            ajEntities.add(ajEntity);
            ajEntity.afterSpawnEvent(root);

            //System.out.println("ajEntity가 생성되었습니다. : " + ajEntity.getRootEntity().getUniqueId());
        }


    }

}
