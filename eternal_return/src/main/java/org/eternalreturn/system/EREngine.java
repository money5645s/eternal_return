package org.eternalreturn.system;

import org.eternalreturn.eranimal.ERAJEntity;
import org.eternalreturn.erentity.EREntity;
import org.eternalreturn.erplayer.ERPlayer;
import org.eternalreturn.util.dpengine.DPEngine;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.eternalreturn.util.dpengine.datastructure.DeadActorException;
import org.eternalreturn.util.dpengine.datastructure.UpdateList;
import org.eternalreturn.util.dpengine.datastructure.UpdateView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Bukkit 객체들과 유연하게 상호작용하기위한 엔진
 * */
public class EREngine extends DPEngine {
    
    /**
     * EREntity들을 쿼리하기 위한 해시맵
     * */
    private HashMap<Entity, EREntity> erEntityMap = new HashMap<>();
    
    /**
     * 플레이어들을 따로 업데이트하기 위한 리스트
     * 뷰로써 동작한다.
     * */
    private UpdateView<ERPlayer> players = new UpdateView<>();

    public EREngine(){
        super();
        this.getMonobehaviourModule().registerUpdateView(players);
    }

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

        getMonobehaviourModule().register(actor);

        try{
            if(entity instanceof Player p && actor instanceof ERPlayer erplayer){
                players.add(erplayer);
            }
            erEntityMap.put(entity,actor);
        }catch (DeadActorException e){
            e.printStackTrace();
        }

    }

    /**
     * 해당 Entity 객체에 맞는 EREntity(extends from MonobehaviourActor)를 반환함.
     * 해당 객체가 더 이상 쓰이지 않는 객체인 경우 (isAlive() == false) 조회할 시점에서 제거함.
     * */
    public EREntity getEREntity(Entity entity) {
        var erEntity = erEntityMap.get(entity);
        if(!erEntity.isAlive()){
            erEntityMap.remove(entity, erEntity);
            erEntity = null; //제거 시에는 null을 반환하도록 설계
        }
        return erEntity;
    }

    public UpdateView<ERPlayer> getPlayers() {
        return players;
    }
}