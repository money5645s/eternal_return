package org.EternalReturn.ERCharacter.Character.isaac;

import org.EternalReturn.ERCharacter.Character.isaac.Active;
import org.EternalReturn.ERCharacter.Character.isaac.Passive;
import org.EternalReturn.ERCharacter.ERCharacter;
import org.EternalReturn.ERPlayer.ERPlayer;

public class Character_Isaac extends ERCharacter {

    public boolean isActiveSkill = false;

    public Character_Isaac(ERPlayer player) {
        super(player);
        this.cooldownSeconds = 8;
        registerMonobehaviour(new Passive());
        registerMonobehaviour(new Active());
    }


    @Override
    public String getName() { return "isaac"; }

}