package org.eternalreturn.erplayer;

import org.eternalreturn.ercharacter.ERCharacter;
import org.eternalreturn.ercharacter.event.*;
import org.eternalreturn.erentity.EREntity;
import org.eternalreturn.erentity.events.EREntityDamagedEvent;
import org.eternalreturn.system.EREngine;
import org.eternalreturn.system.PluginInstance;
import org.eternalreturn.system.SystemManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.*;
import org.eternalreturn.util.dpengine.DPEngine;

import java.util.*;

public class ERPlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        //Player p = e.getPlayer();
        //SystemManager.addPlayer(p);
        //System.out.println("플레이어가 업데이트되었습니다. : " + p);
        ////p.sendMessage("당신이 리스트에 추가되었습니다.");
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        //Player p = e.getPlayer();
        //SystemManager.removePlayer(p);
        //System.out.println("플레이어가 업데이트되었습니다. : " + p);
        ////p.sendMessage("플레이어가 게임을 떠났습니다.");
    }

    @EventHandler
    public void onPlayerInteraction(PlayerInteractEvent e){
        Action action = e.getAction();
        var engine = PluginInstance.getEREngine();
        if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)){
            ERPlayer erPlayer = (ERPlayer)engine.getEREntity(e.getPlayer());
            erPlayer.submitEvent(new CharacterLeftClickEvent());
        }
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent e) {

        if (!(e.getDamager() instanceof Player p)) return;

        var engine = PluginInstance.getEREngine();
        ERPlayer erPlayer = (ERPlayer)engine.getEREntity(p);
        EREntity victim = (EREntity)engine.getEREntity(e.getEntity());

        if(victim == null){
            return;
        }

        if(e.getEntity() instanceof Husk){
            erPlayer.submitEvent(new CharacterLeftClickEvent());
        }

        victim.submitEvent(new EREntityDamagedEvent());
        erPlayer.submitEvent(new CharacterLeftClickEvent());
    }

    @EventHandler
    public void onPlayerSwap(PlayerSwapHandItemsEvent e){
        var engine = PluginInstance.getEREngine();
        var erPlayer = (ERPlayer)engine.getEREntity(e.getPlayer());
        erPlayer.submitEvent(new CharacterSwapHandEvent(erPlayer));
        e.setCancelled(true);
    }

    @EventHandler
    public void onKill(EntityDeathEvent e){
        Entity killer = e.getDamageSource().getCausingEntity();
        Entity victim = e.getEntity();

        var engine = PluginInstance.getEREngine();

        if(killer == null){
            return;
        }

        EREntity erVictim = engine.getEREntity(victim);
        EREntity erKiller = engine.getEREntity(killer);

        if(erKiller == null || erVictim == null){
            return;
        }

        erKiller.submitEvent(new CharacterKillEvent());

    }

}
