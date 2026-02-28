package org.eternalreturn.eranimal;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.eternalreturn.erplayer.ERPlayer;
import org.eternalreturn.system.PluginInstance;
import org.eternalreturn.system.SystemManager;
import org.eternalreturn.util.AJEntity.AJEntity;
import org.eternalreturn.util.AJEntity.AJEntityManager;
import org.eternalreturn.util.Geometry.MathVector.Vec3d;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;

import java.util.ArrayList;
import java.util.List;




public class ERAJEntity extends AJEntity {

    protected boolean isHit;

    protected boolean isShown;

    protected Husk actor;

    private TextDisplay hpbar;

    /**
     * 해당 엔티티가 얼마나 많은 플레이어에게 보여지고 있는지 저장
     * */
    private int refCount;

    public void resetRefCount(){
        this.refCount = 0;
    }

    public void addRefCount() {
        this.refCount++;
    }

    public void decreaseRefCount(){
        this.refCount--;
    }

    public int getRefCount() {
        return this.refCount;
    }

    public void summon() {
        AJEntityManager.summon(this, location, 0.0, 0.0 ,0.0);
        this.isShown = true;
    }

    public void summon(double lx, double ly, double lz) {
        AJEntityManager.summon(this, location, lx, ly, lz);
        this.isShown = true;
    }

    public void setDebugDisplay(String str) {
        this.hpbar.text(Component.text(str));
    }


    public enum AnimalState{
        READY,
        ATTACK,
        MOVE,
        DEAD
    }

    public ERAJEntity(String name, Location location) {
        super(name, location);
        this.refCount = 0;
        this.isShown = false;
        this.isHit = false;
    }

    /**
     *
     * */
    @Override protected void afterSummoning() {
        World world = location.getWorld();
        if(world == null){
            throw new NullPointerException("전달된 매개변수 Location에 World 정보가 없습니다.");
        }

        hpbar = (TextDisplay) world.spawnEntity(location, EntityType.TEXT_DISPLAY);

        //TextComponent textComponent = Component.text("").font("");
        //hpbar.setText(textComponent.content());

        hpbar.setBillboard(Display.Billboard.CENTER);
        hpbar.setBackgroundColor(Color.fromARGB(0,0,0,0));

    }

    /**
     * AJEntity 제거 뿐만 아니라 Actor까지 함께 제거한다.
     */
    @Override
    public void remove() {
        if(rootEntity != null){
            super.remove();
        }
        if(actor != null){
            actor.remove();
        }
        this.isShown = false;
    }

    @Override
    protected void afterSpawnEvent(Entity spawnedRootEntity){
        rootEntity = spawnedRootEntity;
        rootEntity.addPassenger(hpbar);
        System.out.println("rootEntity가 성공적으로 전달되었습니다.");
    }


    //getter
    /**
     * 해당 객체의 ACTOR을 얻어온다. <br>
     * @ACTOR : 해당 객체가 참조하는 AJEntity의 rootEntity를 passenger로 삼는 엔티티를 말한다.
     * */
    public Entity getActor(){
        return this.actor;
    }

    /**
     * 해당 객체의 Actor을 설정한다. (invisible, adult, silent, invulnerable)
     * */
    public void setActor(Husk actor){
        this.actor = actor;
        this.actor.setAdult();
        this.actor.setSilent(true);
        this.actor.setInvisible(true);
        this.actor.setInvulnerable(true);
        this.actor.addPassenger(this.rootEntity);
    }

    /**
     * 해당 ER Animal의 Bar(체력, 이름, 레벨 등을 표시하는 막대)를 가져온다.
     * */
    public TextDisplay getBar(){
        return this.hpbar;
    }

    /**
     * 한번 호출 시 isHit의 반환값은 false가 됨.
     * 여러 번 호출하지 말 것.
     * */
    public boolean isHit(){
        return this.isHit;
    }

    public void setHit() {
        this.isHit = true;
    }

    public boolean isShown(){
        return this.isShown;
    }

}
