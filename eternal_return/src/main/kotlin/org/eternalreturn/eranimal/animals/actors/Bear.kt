package org.eternalreturn.eranimal.animals.actors

import org.eternalreturn.eranimal.ERAJEntity
import org.eternalreturn.eranimal.DPAnimal
import org.eternalreturn.system.EREngine

class Bear(engine: EREngine, erAjEntity: ERAJEntity) :
    DPAnimal(engine, erAjEntity, 1.5, 1.5, 2.5, 0.0, -1.0, 0.0) {


    init {
        this.aJEntity.registerAnimation("ready",    2.0)
        this.aJEntity.registerAnimation("attack",   3.0)
        this.aJEntity.registerAnimation("move",     3.5)
        this.aJEntity.registerAnimation("skill",    3.25)
        this.aJEntity.registerAnimation("death",    2.0)
    }

}

/**
 * #> 야생동물 체력 및 공격력 설정
 * #멧돼지
 * set #ER.animal.boar.health               ER.sys 37
 * set #ER.animal.boar.attack               ER.sys 5
 * set #ER.animal.boar.cooltime             ER.sys 120
 * set #ER.animal.boar.skill_effect_tick    ER.sys 20
 * set #ER.animal.boar.skill_end_tick       ER.sys 24
 * set #ER.animal.boar.attack_tick_first    ER.sys 7
 * set #ER.animal.boar.attack_tick_second   ER.sys 7
 * #곰
 * set #ER.animal.bear.health               ER.sys 60
 * set #ER.animal.bear.attack               ER.sys 6
 * set #ER.animal.bear.cooltime             ER.sys 120
 * set #ER.animal.bear.skill_effect_tick    ER.sys 14
 * set #ER.animal.bear.skill_end_tick       ER.sys 15
 * set #ER.animal.bear.attack_tick_first    ER.sys 8
 * set #ER.animal.bear.attack_tick_second   ER.sys 8
 * #늑대
 * set #ER.animal.wolf.health               ER.sys 25
 * set #ER.animal.wolf.attack               ER.sys 4
 * set #ER.animal.wolf.cooltime             ER.sys 120
 * set #ER.animal.wolf.skill_effect_tick    ER.sys 20
 * set #ER.animal.wolf.skill_end_tick       ER.sys 30
 * set #ER.animal.wolf.attack_tick_first    ER.sys 8
 * set #ER.animal.wolf.attack_tick_second   ER.sys 8
 * #알파
 * set #ER.animal.alpha.health              ER.sys 150
 * set #ER.animal.alpha.attack              ER.sys 10
 * set #ER.animal.alpha.cooltime            ER.sys 120
 * set #ER.animal.alpha.skill_effect_tick   ER.sys 42
 * set #ER.animal.alpha.skill_end_tick      ER.sys 65
 * set #ER.animal.alpha.attack_tick_first   ER.sys 12
 * set #ER.animal.alpha.attack_tick_second  ER.sys 34
 * */