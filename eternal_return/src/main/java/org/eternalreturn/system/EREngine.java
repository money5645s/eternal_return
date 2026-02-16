package org.eternalreturn.system;

import org.eternalreturn.erentity.EREntity;
import org.eternalreturn.util.dpengine.DPEngine;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.eternalreturn.util.dpengine.datastructure.UpdateList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Bukkit 객체들과 유연하게 상호작용하기위한 엔진
 * */
public class EREngine extends DPEngine {
    
    // 이거 문제 있을 수도
    private HashMap<Entity, EREntity> erEntityMap = new HashMap<>();
    private UpdateList<EREntity> players = new UpdateList<>();

    @Override
    public void update(){

    }

    /**
     * Entity를 통해서 MonobehaviourActor를 접근하기 위해 필요한 함수
     * 일반 registerMonobehaviourActor를 통해 등록 시 Entity를 통해 접근이 불가해짐.
     * */
    public void registerBukkitActor(Entity entity, EREntity actor){
        var prevActor = erEntityMap.get(entity);
        if(prevActor != null){
            prevActor.remove(); //refCount 를 0으로 만듬 -> 완전 제거를 의미
        }
        if(entity instanceof Player p){
            players.add(actor);
        }
        erEntityMap.put(entity,actor);
    }

    /**
     * 해당 Entity 객체에 맞는 EREntity(extends from MonobehaviourActor)를 반환함.
     * */
    public EREntity getEREntity(Entity entity) {
        return erEntityMap.get(entity);
    }
}