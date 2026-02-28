package org.EternalReturn.ERCharacter.Character.hyunwoo;

import org.EternalReturn.ERCharacter.ERCharacter;
import org.EternalReturn.ERPlayer.ERPlayer;

public class Character_Hyunwoo extends ERCharacter {

    // 플레이어별 공격 횟수를 저장하는 맵

    public Character_Hyunwoo(ERPlayer player) {
        super(player);
        this.ActiveCooldownSeconds = 8;
        this.PassiveCooldownSeconds = 8;
        registerMonobehaviour(new Active());
        registerMonobehaviour(new Passive());
    }


    @Override
    public String getName() { return "hyunwoo"; }

}