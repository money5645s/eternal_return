package org.eternalreturn.system;

import org.eternalreturn.erplayer.DPlayer;
import org.eternalreturn.util.itemUtill.CustomModelDataManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//싱글톤 객체
public class SystemManager {

    private static SystemManager instance;
    private static HashMap<Player, DPlayer> erPlayerHashMap;
    private static List<DPlayer> erPlayerList;
    private static CustomModelDataManager CustomModelDataManager;


    //free (메모리 할당 해제)
    public void free() {
        erPlayerList.clear();
        erPlayerHashMap.clear();
        CustomModelDataManager.free();
    }

    private SystemManager() {
        erPlayerList = new ArrayList<>();
        erPlayerHashMap = new HashMap<>();
        CustomModelDataManager = new CustomModelDataManager();
    }

    //getter
    public static DPlayer getERPlayer(Player player){ //Player 객체를 이용해서 DPlayer 객체 불러오기
        return erPlayerHashMap.get(player);
    }

    public static SystemManager getInstance() {
        if(instance == null){
            instance = new SystemManager();
        }
        return instance;
    }

    public static @NotNull HashMap<Player, DPlayer> getERPlayerHashMap(){
        return erPlayerHashMap;
    }

    public static CustomModelDataManager getCustomModelDataManager(){
        return CustomModelDataManager;
    }

}
