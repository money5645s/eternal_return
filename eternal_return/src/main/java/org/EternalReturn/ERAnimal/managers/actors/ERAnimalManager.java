package org.EternalReturn.ERAnimal.managers.actors;

import org.EternalReturn.ERAnimal.*;
import org.EternalReturn.ERAnimal.animals.events.ERAnimalReadyEvent;
import org.EternalReturn.ERAnimal.managers.behavs.ManageAnimals;
import org.EternalReturn.ERAnimal.managers.events.AnimalManageEvent;
import org.EternalReturn.ERPlayer.ERPlayer;
import org.EternalReturn.util.dpengine.DPEngine;
import org.EternalReturn.System.PluginInstance;
import org.EternalReturn.System.SystemManager;
import org.EternalReturn.util.AJEntity.AJEntityManager;
import org.EternalReturn.util.dpengine.behaviour.MonobehaviourActor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;







/**
 * animal.json 정보를 토대로 ERAnimal을 관리하는 클래스.
 * MonobehaviourActor이다.
 * */
public class ERAnimalManager extends MonobehaviourActor {

    /**
     * Initialized from ERAJEntityLoader.load()
     * */
    private static List<AreaERAnimalInfo> areaInfoList;

    private static ERAJEntityLoader erAnimalLoader = new ERAJEntityLoader("plugins/animal.json");;

    /**
     * 얘네들 기준으로 쿼리하기 위해 따로 모아둔 리스트
     * */
    private static List<ERAnimalManager> animalManagers = new ArrayList<>();

    /**
     * ERAnimalManager들은 MonobehaviourActor이므로, dpEngine의 MonobehaviourModule에 register해야 함.
     * */
    public static void initERAnimalManagers(AJEntityManager ajEntityManager, DPEngine engine){
        areaInfoList = erAnimalLoader.load();
        var monobehaviourModule = engine.getMonobehaviourModule();

        var world = PluginInstance.getServerInstance().getServer().getWorlds().getFirst();

        /// areaInfoList를 순회하며 해당하는 ERAnimalManager들을 생성, 등록
        for(AreaERAnimalInfo info : areaInfoList){
            var animalManager = new ERAnimalManager(ajEntityManager, engine, world, info);
            animalManagers.add(animalManager);
            monobehaviourModule.register(animalManager);
        }
    }


    /**
     * 실제 ERAnimalManager가 갖는 필드들
     * */
    private World world;
    private String areaName;
    private DPEngine engine;
    private List<ERAJEntity> entities;

    public ERAnimalManager(AJEntityManager ajEntityManager, DPEngine engine, World world, AreaERAnimalInfo info){
        if(ajEntityManager == null){
            throw new RuntimeException("Cannot instantiate(register) ERAnimalManager, ajEntityManager is null.");
        }


        this.world = world;
        this.areaName = info.name();
        this.engine = engine;
        for(ERAnimalInfo animalInfo : info.animals()){
            var animal = new ERAJEntity(animalInfo.name, new Location(world, animalInfo.x, animalInfo.y, animalInfo.z, animalInfo.yaw, animalInfo.pitch));
            AJEntityManager.registerAJEntity(animal); //register하지 않으면 돌아가지 않아요...
            entities.add(animal);
        }

        registerMonobehaviour(new ManageAnimals());

        //한번만 제출하면 됨. 매 틱마다 동작할 것.
        this.submitEvent(new AnimalManageEvent());
    }

    public List<ERAJEntity> getEntities(){
        return this.entities;
    }


}
