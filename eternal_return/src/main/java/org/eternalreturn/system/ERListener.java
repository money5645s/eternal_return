package org.eternalreturn.system;

import com.destroystokyo.paper.event.player.PlayerAttackEntityCooldownResetEvent;
import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.eternalreturn.ercharacter.event.*;
import org.eternalreturn.erentity.EREntity;
import org.eternalreturn.erplayer.ERPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.*;

public class ERListener implements Listener {

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
        var engine = PluginInstance.getEREngine();
        ERPlayer erPlayer = (ERPlayer)engine.getEREntity(e.getPlayer());

        if(e.getAction().isLeftClick() && erPlayer != null){
            erPlayer.shootRay();
        }
    }

    @EventHandler
    public void onPlayerAttack(PrePlayerAttackEntityEvent e) {
        var engine = PluginInstance.getEREngine();
        ERPlayer erPlayer = (ERPlayer)engine.getEREntity(e.getPlayer());
        if(erPlayer != null) {
            erPlayer.shootRay();
        }
    }

//    @EventHandler
//    public void onPlayerSwap(PlayerSwapHandItemsEvent e){
//        var engine = PluginInstance.getEREngine();
//        var erPlayer = (ERPlayer)engine.getEREntity(e.getPlayer());
//        if(erPlayer != null) {
//            erPlayer.submitEvent(new CharacterSwapHandEvent(erPlayer));
//        }
//        e.setCancelled(true);
//    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        var player = e.getPlayer();
        if(!player.getScoreboardTags().contains("resurrectable")){
            return;
        }
        e.getPlayer().setGameMode(GameMode.SPECTATOR);
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerSwap(PlayerDropItemEvent e){
        e.setCancelled(true); //사실 어디 위치시켜도 상관 없음.
        var engine = PluginInstance.getEREngine();
        var erPlayer = (ERPlayer)engine.getEREntity(e.getPlayer());
        if(erPlayer != null) {
            erPlayer.submitEvent(new CharacterSwapHandEvent(erPlayer));
        }
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

        erKiller.submitEvent(new CharacterKillEvent(erVictim));

    }

    @EventHandler
    public void onShoot(EntityShootBowEvent e){
        e.setCancelled(true);
        var player = (Player)e.getEntity();
        float force = e.getForce();
        player.sendMessage("Force : " + force);

        var engine = PluginInstance.getEREngine();
        var erPlayer = engine.getEREntity(player);

        if(erPlayer != null) {
            erPlayer.submitEvent(new CharacterShootProjectileEvent(force));
        }
    }
}
