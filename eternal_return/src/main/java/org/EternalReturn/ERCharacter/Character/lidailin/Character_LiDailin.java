package org.EternalReturn.ERCharacter.Character.lidailin;

import org.EternalReturn.ERCharacter.ERCharacter;
import org.EternalReturn.ERPlayer.ERPlayer;

public class Character_LiDailin extends ERCharacter {

    public boolean isDrunk = false;
    public int drinkCount = 0;

    public Character_LiDailin(ERPlayer player) {
        super(player);
        this.cooldownSeconds = 8;
        registerMonobehaviour(new Passive());
        registerMonobehaviour(new Active());
    }


    @Override
    public String getName() { return "lidailin"; }

}