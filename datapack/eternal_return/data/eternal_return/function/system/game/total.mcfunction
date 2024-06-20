## 스코어 자동 동기화


# 솔로 : 팀 수 = 플레이어 수 동기화
scoreboard players operation #game.team CT1 = #game.player CT1
# 플레이어 수
execute store result score #game.player CT1 if entity @e[tag=player]

# 일차별 부활 표시
execute \
    if score #timer.day CT1 matches 1 \
        run scoreboard players set #game.respawn CT1 0
execute \
    if score #timer.day CT1 matches 2 \
    if score #timer.halfday CT1 matches 1 \
        run scoreboard players set #game.respawn CT1 1
execute \
    if score #timer.day CT1 matches 2 \
    if score #timer.halfday CT1 matches 2 \
       run scoreboard players set #game.respawn CT1 2
execute \
    if score #timer.day CT1 matches 3.. run scoreboard players set #game.respawn CT1 2