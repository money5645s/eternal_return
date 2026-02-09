package org.EternalReturn.ERCharacter.Character.nathapon;

import org.EternalReturn.ERCharacter.ERCharacter;
import org.EternalReturn.ERPlayer.ERPlayer;

public class Character_Nathapon extends ERCharacter {

    // 플레이어별 공격 횟수를 저장하는 맵

    public Character_Nathapon(ERPlayer player) {
        super(player);
        this.ActiveCooldownSeconds = 8;
        this.PassiveCooldownSeconds = 8;
        registerMonobehaviour(new Passive());
        registerMonobehaviour(new Active());
    }


    @Override
    public String getName() { return "nathapon"; }

}