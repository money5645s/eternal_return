package org.eternalreturn.ercharacter.character.adriana.monobehavs

import org.eternalreturn.ercharacter.character.adriana.Character_Adriana
import org.eternalreturn.ercharacter.character.adriana.entities.BurningGroundVirtualEntity
import org.eternalreturn.ercharacter.character.adriana.events.LetsBurn
import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.Location

class BurningGround : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {

    var skillActiveMillis: Long = 0


    public override fun start(event: CharacterSwapHandEvent) {
        // 1. 플레이어가 바라보는 방향 가져오기

        val adriana = actor as Character_Adriana;

        var dir = adriana.getDirection();
        var world = adriana.player.world;

        dir = vec3(x(dir), 0.0, z(dir));
        dir = dir * (1/magnitude(dir))

        // 4. 속도 곱하기 (1.5 정도가 약 5칸 돌진에 적당함)
        adriana.setVelocity(dir * 1.5)

        skillActiveMillis = System.currentTimeMillis();
        adriana.player.sendMessage("§8[Adriana] §f급속 돌진!")
    }


    public override fun update(event: MutableCollection<MonobehaviourEvent>) {

        val adriana = actor as Character_Adriana;
        var pos = adriana.getPosition();
        if(System.currentTimeMillis() - skillActiveMillis >= 50 * 5){
            stopMonobehav();
            return;
        }

        adriana.player.sendMessage("Summon BurningGroundVirtualEntity")

        val burningGroundEntity = BurningGroundVirtualEntity(
            dpEngine,
            adriana.player,
            Location(adriana.player.world, x(pos), y(pos), z(pos)),
            System.currentTimeMillis())

        burningGroundEntity.submitEvent(LetsBurn()); //태우기 실행

    }
}

