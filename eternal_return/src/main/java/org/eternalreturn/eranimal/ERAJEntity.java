package org.eternalreturn.eranimal;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
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
import java.util.Objects;


public class ERAJEntity extends AJEntity {

    protected Husk actor;

    private TextDisplay hpbar;

    public void setDebugDisplay(String str) {
        this.hpbar.text(Component.text(str));
    }

    public void setDebugDisplay(Component cmp) {
        this.hpbar.text(cmp);
    }

    public ERAJEntity(String name, Location location) {
        super(name, location);
    }

    /**
     * 소환 이후의 로직을 설정한다.
     * 소환 직후에는 아무 것도 보이지 않도록 설정하였음
     * <blockquote><pre>
     *     AJEntity.setNotBeShown(); // <- 해당 함수가 호출됨.
     * </pre></blockquote>
     * */
    @Override protected void afterSummoning() {
        World world = location.getWorld();
        if(world == null){
            throw new NullPointerException("전달된 매개변수 Location에 World 정보가 없습니다.");
        }

        hpbar = (TextDisplay) world.spawnEntity(location, EntityType.TEXT_DISPLAY);
        hpbar.setBillboard(Display.Billboard.CENTER);
        hpbar.setBackgroundColor(Color.fromARGB(0,0,0,0));

        setNotBeShown();

    }

    /**
     * AJEntity 제거 뿐만 아니라 Actor까지 함께 제거한다.
     */
    @Override public void remove() {
        if(rootEntity != null){
            super.remove();
            rootEntity.remove();
            rootEntity = null;
        }
        if(actor != null){
            actor.remove();
            actor = null;
        }
        if(hpbar != null){
            hpbar.remove();
            hpbar = null;
        }
        this.isShown = false;
    }

    @Override protected void afterSpawnEvent(ItemDisplay spawnedRootEntity){
        rootEntity = spawnedRootEntity;
        rootEntity.addPassenger(hpbar);
        setNotBeShown();
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
        try{
            Objects.requireNonNull(actor.getAttribute(Attribute.ATTACK_DAMAGE)).setBaseValue(0.0);
            Objects.requireNonNull(actor.getAttribute(Attribute.MAX_HEALTH)).setBaseValue(1024.0);
            Objects.requireNonNull(actor.getAttribute(Attribute.ARMOR)).setBaseValue(1024.0);
            this.actor.setHealth(1024.0);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void setNotBeShown(){
        super.setNotBeShown();
        hpbar.text(Component.text(""));
    }


}
