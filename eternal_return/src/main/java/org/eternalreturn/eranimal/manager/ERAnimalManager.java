package org.eternalreturn.eranimal.manager;

import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TextDisplay;
import org.eternalreturn.area.AreaNode;
import org.eternalreturn.eranimal.*;
import org.eternalreturn.eranimal.manager.behavs.*;
import org.eternalreturn.eranimal.manager.events.DetectingPlayerEvent;
import org.eternalreturn.system.EREngine;
import org.eternalreturn.util.dpengine.DPEngine;
import org.eternalreturn.system.PluginInstance;
import org.eternalreturn.util.AJEntity.AJEntityManager;
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
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

    private boolean allowedToSummonAlpha = false;
    public boolean isAllowedToSummonAlpha(){
        return allowedToSummonAlpha;
    }
    public void allowToSummonAlpha(boolean b){
        allowedToSummonAlpha = b;
    }

    private boolean allowedToSummonOmega = false;
    public boolean isAllowedToSummonOmega(){
        return allowedToSummonOmega;
    }
    public void allowToSummonOmega(boolean b){
        allowedToSummonOmega = b;
    }
    public static List<ERAnimalManager> getAnimalManagers(){
        return animalManagers;
    }


    /**
     * ERAnimalManager들은 MonobehaviourActor이므로, dpEngine의 MonobehaviourModule에 register해야 함.
     * */
    public static void initERAnimalManagers(AJEntityManager ajEntityManager, EREngine engine){
        areaInfoList = erAnimalLoader.load();
        var world = PluginInstance.getServerInstance().getServer().getWorlds().getFirst();

        var areaSystem = engine.getAreaSystem();

        /// areaInfoList를 순회하며 해당하는 ERAnimalManager들을 생성, 등록
        for(AreaERAnimalInfo info : areaInfoList){

            var vertex = areaSystem.getVertex(new AreaNode(info.name())).getData();

            System.out.println(vertex.getName() + " <-> " + info.name());

            var animalManager = new ERAnimalManager(ajEntityManager, engine, world, info);
            vertex.setManager(animalManager);
            animalManagers.add(animalManager);
        }
    }



    /**
     * 실제 ERAnimalManager가 갖는 필드들
     * */
    private @NotNull World world;
    public @NotNull World getWorld(){return world;}

    private @NotNull String areaName;
    public @NotNull String getAreaName() {
        return areaName;
    }

    private @NotNull DPEngine engine;
    private @NotNull List<ERAJEntity> entities;

    private @NotNull ArrayList<TextDisplayer> textDisplayList = new ArrayList<>();
    public ArrayList<TextDisplayer> getTextDisplayList() { return textDisplayList; }

    private @NotNull HashMap<ERAJEntity, ERAnimal> erAnimalMap = new HashMap<>();
    public HashMap<ERAJEntity, ERAnimal> getERAnimalMap() { return erAnimalMap; }

    public double px; public double py; public double pz;

    @Override
    public void remove() {
        super.remove();
        for(var textDisplayer : textDisplayList){
            textDisplayer.remove();
        }
    }

    public ERAnimalManager(@NotNull AJEntityManager ajEntityManager, @NotNull DPEngine engine, @NotNull World world, @NotNull AreaERAnimalInfo info){
        super(engine);
        this.world = world;
        this.areaName = info.name();
        this.engine = engine;
        this.entities = new ArrayList<>();
        for(ERAnimalInfo animalInfo : info.animals()){
            var animal = new ERAJEntity(animalInfo.name, new Location(world, animalInfo.x, animalInfo.y, animalInfo.z, animalInfo.yaw, animalInfo.pitch));
            AJEntityManager.registerAJEntity(animal); //register하지 않으면 돌아가지 않아요...
            entities.add(animal);
        }

        for(var erAJAnimal : entities){
            var loc = new Location(
                    world,
                    erAJAnimal.getSpawnLocation().x(),
                    erAJAnimal.getSpawnLocation().y() + 3.0,
                    erAJAnimal.getSpawnLocation().z()
            );

            var textDisplay = (TextDisplay)this.world.spawnEntity(loc, EntityType.TEXT_DISPLAY);
            textDisplay.setBillboard(Display.Billboard.CENTER); //어느 방향에서 봐도 똑같이 보인다.

            var textDisplayEREntity = new TextDisplayer(textDisplay, (EREngine)getDpEngine()); //어차피 생성되면서 MonobehaviourModule내에 들어가게 됨.
            this.textDisplayList.add(textDisplayEREntity);
        }

        registerMonobehaviour(new InitializeManager(128.0));
        registerMonobehaviour(new WaitForSummoning());
        registerMonobehaviour(new ManageERAnimals(this.entities.size()));
        registerMonobehaviour(new DetectingPlayersInRange());


        this.submitEvent(new InitializeEvent());
    }

    public List<@NotNull ERAJEntity> getEntities(){
        return this.entities;
    }

    @Override
    public void lateinit() {

    }
}
