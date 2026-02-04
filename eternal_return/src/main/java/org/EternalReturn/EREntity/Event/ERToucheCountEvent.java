package org.EternalReturn.EREntity.Event;

import org.bukkit.entity.Player;

public class ERToucheCountEvent implements EREntityEvent{

    public Player player;

    public ERToucheCountEvent(Player player){
        this.player = player;
    }

}
