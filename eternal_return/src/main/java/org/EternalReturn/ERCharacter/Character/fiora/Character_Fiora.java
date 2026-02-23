package org.EternalReturn.ERCharacter.Character.fiora;


import org.EternalReturn.ERCharacter.ERCharacter;
import org.EternalReturn.ERPlayer.ERPlayer;

public class Character_Fiora extends ERCharacter {

    public boolean isActiveSkill = false;

    public Character_Fiora(ERPlayer player) {
        super(player);
        this.ActiveCooldownSeconds = 3;
        registerMonobehaviour(new Passive());
        registerMonobehaviour(new Active());
    }


    @Override
    public String getName() { return "fiora"; }

}