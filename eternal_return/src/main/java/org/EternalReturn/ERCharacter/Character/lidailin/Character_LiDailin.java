package org.EternalReturn.ERCharacter.Character.lidailin;

import org.EternalReturn.ERCharacter.Character.hart.Active;
import org.EternalReturn.ERCharacter.Character.hart.Passive;
import org.EternalReturn.ERCharacter.ERCharacter;
import org.EternalReturn.ERPlayer.ERPlayer;

public class Character_LiDailin extends ERCharacter {

    // 플레이어별 공격 횟수를 저장하는 맵

    public Character_LiDailin(ERPlayer player) {
        super(player);
        this.cooldownSeconds = 8;
        registerMonobehaviour(new Passive());
        registerMonobehaviour(new Active());
    }


    @Override
    public String getName() { return "lidailin"; }

}