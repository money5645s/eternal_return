package org.EternalReturn.ERCharacter.Character.hart;

import org.EternalReturn.ERCharacter.ERCharacter;
import org.EternalReturn.ERPlayer.ERPlayer;

public class Character_Hart extends ERCharacter {

    // 플레이어별 공격 횟수를 저장하는 맵

    public Character_Hart(ERPlayer player) {
        super(player);
        this.cooldownSeconds = 8;
        registerMonobehaviour(new Passive());
        registerMonobehaviour(new Active());
    }


    @Override
    public String getName() { return "hart"; }

}