package org.EternalReturn.ERCharacter.Character.hart;

import org.EternalReturn.ERCharacter.ERCharacter;
import org.EternalReturn.ERPlayer.ERPlayer;

public class Character_Hart extends ERCharacter {

    public Character_Hart(ERPlayer player) {
        super(player);
        this.ActiveCooldownSeconds = 8;
        this.PassiveCooldownSeconds = 8;
        registerMonobehaviour(new Passive());
        registerMonobehaviour(new Active());
    }


    @Override
    public String getName() { return "hart"; }

}