package org.eternalreturn.system;

import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.eternalreturn.ercharacter.event.CharacterKillEvent;
import org.eternalreturn.ercharacter.event.CharacterLeftClickEvent;
import org.eternalreturn.ercharacter.event.CharacterShootProjectileEvent;
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent;
import org.eternalreturn.erentity.DPEntity;
import org.eternalreturn.erplayer.DPlayer;

public class ERListener implements Listener {


    @EventHandler
    public void onPlayerInteraction(PlayerInteractEvent e){
        var engine = PluginInstance.getEREngine();
        DPlayer erPlayer = (DPlayer)engine.getEREntity(e.getPlayer());

        var action = e.getAction();

        if((action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) && erPlayer != null){
            erPlayer.submitEvent(new CharacterLeftClickEvent());
            erPlayer.shootRay();
        }
    }

    @EventHandler
    public void onPlayerAttack(PrePlayerAttackEntityEvent e) {
        var engine = PluginInstance.getEREngine();
        DPlayer erPlayer = (DPlayer)engine.getEREntity(e.getPlayer());
        if(erPlayer != null) {
            erPlayer.shootRay();
        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        var victim = e.getPlayer();
        if(!victim.getScoreboardTags().contains("resurrectable")){
            return;
        }

        var erVictim = PluginInstance.getEREngine().getEREntity(victim);
        if(erVictim instanceof DPlayer p){
            p.remove();
        }
//
//        var death = victim.getScoreboard().getObjective("death");
//        if(death != null){
//            var score = death.getScoreFor(victim);
//            score.setScore(score.getScore() + 1);
//        }

        var deathCount = victim.getScoreboard().getObjective("death_count");
        if(deathCount != null){
            var score = deathCount.getScoreFor(victim);
            score.setScore(score.getScore() + 1);
        }

        var killer = e.getDamageSource().getCausingEntity();
        if(killer instanceof Player pKiller){
            var killerScoreboard = pKiller.getScoreboard().getObjective("kill");
            if(killerScoreboard != null){
                var score = killerScoreboard.getScoreFor(pKiller);
                score.setScore(score.getScore() + 1);
            }
        }

        victim.setGameMode(GameMode.SPECTATOR);
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerSwap(PlayerDropItemEvent e){
        e.setCancelled(true); //사실 어디 위치시켜도 상관 없음.
        var engine = PluginInstance.getEREngine();
        var erPlayer = (DPlayer)engine.getEREntity(e.getPlayer());

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

        DPEntity erVictim = engine.getEREntity(victim);
        DPEntity erKiller = engine.getEREntity(killer);

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

        var engine = PluginInstance.getEREngine();
        var erPlayer = engine.getEREntity(player);

        if(erPlayer != null) {
            erPlayer.submitEvent(new CharacterShootProjectileEvent(force));
        }
    }
}
