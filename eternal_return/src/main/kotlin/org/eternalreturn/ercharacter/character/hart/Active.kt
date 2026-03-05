package org.eternalreturn.ercharacter.character.hart

import org.eternalreturn.ercharacter.ERCharacterMonobehaviour
import org.eternalreturn.ercharacter.event.CharacterSwapHandEvent
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.bukkit.util.Vector
import org.eternalreturn.ercharacter.character.hart.event.HartActiveEvent
import org.eternalreturn.ercharacter.event.CooldownEvent

class Active : ERCharacterMonobehaviour<CharacterSwapHandEvent>() {
    var direction: Vector? = null
    private var isWallSlam = false
    private var tick = 0

    override fun start(event: CharacterSwapHandEvent) {
        val player = getPlayer()

        val hart = actor as Character_Hart
        val cd = hart.cooldown

        if (cd.isWaiting("Active")) {
            val remain = String.format("%.1f", cd.getLeft("Active"))
            getPlayer().sendMessage("§c[!] §7쿨타임 중입니다. (${remain}초)")
            return
        }
        player.sendMessage("F 디버깅")
        // 시선과 무관하게 수평 방향 벡터로 고정 (y=0)
        this.direction = player.getLocation().getDirection().setY(0).normalize().multiply(1)
        tick = 0

        if (hart.stack == 0) {
            player.sendMessage("스킬")
            this.getEREntity().submitEvent(HartActiveEvent())
            hart.stack++
        } else if (hart.stack == 1) {
            player.sendMessage("재사용")
            hart.stack = 0
        }

    }

    override fun update(eventList: MutableCollection<MonobehaviourEvent>) {
        val hart = actor as Character_Hart
        val cd = hart.cooldown
        val player = getPlayer()

        if (cd.isWaiting("Active")) {
            stopMonobehav()
            return
        }

        tick ++

        if (!isWallSlam && tick < 4) {
            player.sendMessage("§c[디버깅] §f${tick}")

            // 1. 돌진 물리 적용
            val curVelocity = player.getVelocity()
            direction!!.setY(curVelocity.getY())
            player.setVelocity(direction!!)
        }

        if (tick > 3){
            player.sendMessage("§c[디버깅] §f돌진 종료")
            if(hart.stack == 0) {
                // 쿨타임 등록
                hart.cooldown.set("Active", hart.ActiveCooldownSeconds)
                this.getEREntity().submitEvent(CooldownEvent("Active", hart.ActiveCooldownSeconds))
            }
            stopMonobehav()
        }

        if (isWallSlam){
            player.sendMessage("§c[디버깅] §f돌진 종료")
            if(hart.stack == 0) {
                // 쿨타임 등록
                hart.cooldown.set("Active", hart.ActiveCooldownSeconds)
                this.getEREntity().submitEvent(CooldownEvent("Active", hart.ActiveCooldownSeconds))
            }
            stopMonobehav()
        }
    }
}