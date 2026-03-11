package org.eternalreturn.util.AJEntity;

import org.bukkit.command.CommandSender;
import org.eternalreturn.util.Free.FreeAble;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * AJEntity를 원활하게 관리하기 위한 싱글톤 클래스<br>
 * */
public class AJEntityManager implements FreeAble, Runnable {

    private static AJEntityManager manager = null;

    //private static CommandSender commandSender = Bukkit.getConsoleSender();
    private static CommandSender commandSender = Bukkit.createCommandSender(builder -> {});

    static HashMap<Entity, AJEntity> ajEntityMap = new HashMap<>(8);

    static List<AJEntity> ajEntities = new ArrayList<>(8);

    private static Plugin plugin = null;

    static List<AJEntity> ajEntitySummonQueue = new LinkedList<>();

    public static void sendCommand(String cmd){
        Bukkit.dispatchCommand(commandSender, cmd);
    }

    @Override
    public void free() {
        flushAllEntities();
        ajEntityMap.clear();
        ajEntities.clear();
        ajEntitySummonQueue.clear();
    }

    private AJEntityManager(){

    }

    public static AJEntityManager registerAJEntityManager(Plugin pluginInstance){
        if(manager == null && plugin == null){
            manager = new AJEntityManager();
            plugin = pluginInstance;
        }
        else{
            Bukkit.getConsoleSender().sendMessage(
                    "\"AJEntityManager.registerAJEntityManager(Plugin plugin)\"는 이미 호출되었습니다. \n객체가 이미 존재합니다.");
        }
        return manager;
    }

    /**
     * 현재 싱글톤 인스턴스가 존재하는지 확인하는 함수. <br>
     * AJEntityManager의 public static 메소드들을 실행시키기 전 해당 싱글톤 인스턴스가 정상적인 상태인지 확인한다.
     * */
    private boolean thisSingletoneIsAlreadAllocated(){
        if(manager == null || plugin == null){
            Bukkit.getConsoleSender().sendMessage("\n"
                    + "\"@Override public void onEnable(){...}\"에서\n"
                    + "\"AJEntityManager.registerAJEntityManager(Plugin plugin)\"를 먼저 호출하십시오.");
            return false;
        }
        return true;
    }

    public static AJEntityManager getInstance(){
        return manager;
    }

    public static AJEntity getAJEntity(Entity rootEntity){
        return ajEntityMap.get(rootEntity);
    }

    /**
     * 해당 Location에 AJEntity 객체의 명세대로 엔티티를 소환한다.
     * */
    private static StringBuilder cmdBuilder = new StringBuilder();
    private static ArrayDeque<String> commandQueue = new ArrayDeque<>(512);
    public static void summon(@NotNull AJEntity ajEntity, @NotNull Location location, double lx, double ly, double lz){
        if(location.getWorld() == null){
            try{
                throw new NullPointerException("해당 함수의 매개변수로 전달된 Location 객체는 반드시 World 객체 정보를 가지고 있어야 합니다.");
            }catch (NullPointerException e){
                e.printStackTrace();
                return;
            }
        }

        if(manager.thisSingletoneIsAlreadAllocated()){
            /// 이거 명령어 순서 바꾸면 좆된다!!!!!!!!!!!!!!!!!!!

            //해당 명세서를 큐에 넣는다. 위의 명령어가 실행이 완료될 때마다, 순차적으로 dequeue(poll)되어
            //ajEntityMap에 등록된다.
            ajEntitySummonQueue.addFirst(ajEntity);
            //해당 AJEntity가 소환된 world 및 좌표 저장
            ajEntity.location = location;
            ajEntity.afterSummoning();

            cmdBuilder.append("execute").append(" positioned ")
                    .append((location.getX() + lx)).append(" ").append((location.getY() + ly)).append(" ").append((location.getZ() + lz))
                    .append(" rotated ").append(location.getYaw()).append(" ").append(location.getPitch()).append(" run function animated_java:")
                    .append(ajEntity.getName()).append("/summon {args:0}");

//            String command =  "execute"
//                    +" positioned " + (location.getX() + lx) + " " + (location.getY() + ly) + " " + (location.getZ() + lz)
//                    +" rotated " + location.getYaw() + " " + location.getPitch()
//                    +" run function animated_java:" + ajEntity.getName() + "/summon {args:0}";

            //var cache = new AJCache(location.getX() + lx, location.getY() + ly, location.getZ() + lz, location.getYaw(), location.getPitch(), ajEntity.getName());
            //AJEntityManager.sendCommand(cmdBuilder.toString());
            commandQueue.addLast(cmdBuilder.toString());
            cmdBuilder.delete(0, cmdBuilder.length());

        }
    }

    public static void remove(AJEntity ajEntity, Entity rootEntity) {
        ajEntityMap.remove(rootEntity, ajEntity);
        ajEntities.remove(ajEntity);
    }

    /**
     * 현재 객체가 관리하고 있는 모든 AJEntity를 삭제하는 메소드.
     * */
    public static void flushAllEntities(){
        for(AJEntity ajEntity : ajEntities){
            ajEntity.remove();
        }
        ajEntityMap.clear();
        ajEntities.clear();
    }

    /**
     * 소환된 엔티티를 대상으로 태그를 선형으로 조사하여<br>
     * "aj."로 시작, ".root"로 끝나는 태그가 있는지 확인하고, 해당 엔티티인 경우 true 반환 그 외에는 false 반환
     * */
    static boolean isAJEntityRoot(@NotNull Entity entity){
        return entity.getType().equals(EntityType.ITEM_DISPLAY) && entity.getScoreboardTags().contains("aj.global.root");
    }

    public static void registerAJEntity(AJEntity ajEntity){
        AJEntityManager.ajEntities.add(ajEntity);
    }

    public static List<AJEntity> getAjEntities(){
        return AJEntityManager.ajEntities;
    }


    private int commandDispatchLimit = 1;
    @Override
    public void run() {

        int i = 0;

        while(!commandQueue.isEmpty() && i < commandDispatchLimit){
            var cmd = commandQueue.removeFirst();
            sendCommand(cmd);
            i++;
        }

    }
}
