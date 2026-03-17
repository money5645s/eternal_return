package org.eternalreturn.system;


import java.io.UnsupportedEncodingException;

import org.eternalreturn.eranimal.manager.ERAnimalManager;
import org.eternalreturn.util.AJEntity.AJEntityListener;
import org.eternalreturn.util.AJEntity.AJEntityManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginInstance extends JavaPlugin{

    private static PluginInstance serverInstance;
    private static AJEntityManager ajEntityManager;
    private static SystemManager systemManager;

    /**
     * 나중에 병렬처리를 위해 List<>로 관리할 수도 있음.
     * */
    private static EREngine erEngine;


    //UTF-8로 인코딩 후 로거에게 전달하는 함수.
    public static void dfLogUTF8(String str){
        try {
            serverInstance.getLogger().info(new String(str.getBytes(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onEnable() {
        serverInstance = this;
        erEngine = new EREngine(this, 512);
        //로드 시작 시 로그
        dfLogUTF8("이터널 리턴 플러그인 구동 준비...");

        //Animated JAVA Entity initialization
        ajEntityManager = AJEntityManager.registerAJEntityManager(this);
        
        //ERAnimal들을 dpengine구현체에 register하는 구문
        ERAnimalManager.initERAnimalManagers(ajEntityManager, erEngine);

        //시스템매니저 객체 생성
        systemManager = SystemManager.getInstance();


        //GuiOpen 리스너 등록. 이런 식으로 해야 함...
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ERListener(), this);
        //pm.registerEvents(new BSwingListener(), this);
        pm.registerEvents(new AJEntityListener(), this);
        loadCommands();

        Bukkit.getScheduler().runTaskTimer(this, ajEntityManager,0, 1);

        //로드 종료 시 로그
        dfLogUTF8("이터널 리턴 플러그인 구동 준비 완료!");

    }

    //커맨드 로드용. 너무 길어질 것 같아서 미리 뺐음
    private void loadCommands() {
        getCommand("er").setExecutor(new ERCommand());
    }

    @Override
    public void onDisable() {
        erEngine.free();
        systemManager.free();
        ajEntityManager.free();
    }

    public static PluginInstance getServerInstance(){
        return serverInstance;
    }

    public static SystemManager getSystemManager(){
        return systemManager;
    }

    public static EREngine getEREngine() {
        return erEngine;
    }

}
