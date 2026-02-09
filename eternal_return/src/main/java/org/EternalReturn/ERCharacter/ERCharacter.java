package org.EternalReturn.ERCharacter;

import org.EternalReturn.ERCharacter.GlobalMonobehav.PlayerRayCastingByLeftClicking;
import org.EternalReturn.EREntity.EREntity;
import org.EternalReturn.ERPlayer.ERPlayer;
import org.EternalReturn.System.PluginInstance;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public abstract class ERCharacter extends EREntity {

    protected ERPlayer erPlayer;

    public long ActiveCooldownSeconds;
    public long PassiveCooldownSeconds;
    public SkillCooldown cooldown = new SkillCooldown();

    public ERCharacter(ERPlayer erPlayer){
        super(PluginInstance.getEREngine().createOrientedBox(erPlayer.getPlayer().getLocation(),1.0/2,3.0/2,1.0/2));
        this.setEntity(erPlayer.getPlayer());
        this.erPlayer = erPlayer;
        registerMonobehaviour(new PlayerRayCastingByLeftClicking());
    }

    public abstract String getName();

    public ERPlayer getERPlayer(){
        return this.erPlayer;
    }

    public Player getPlayer(){
        return (Player)this.getEntity();
    }

    public SkillCooldown getCooldown() {
        return this.cooldown;
    }
}
