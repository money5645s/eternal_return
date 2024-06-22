## 스코어 자동 동기화

## 플레이어 수 & 팀 수
# 게임 시작 전
execute if entity @a[tag=player,tag=wait_bar] run scoreboard players set #game.player CT1 18
execute if entity @a[tag=player,tag=wait_bar] run scoreboard players set #game.team CT1 18
execute if entity @a[tag=player,tag=wait_bar] store result score #wait.player CT1 if entity @a[tag=player]
# 게임 시작 후
execute unless entity @a[tag=player,tag=wait_bar] store result score #game.player CT1 if entity @a[tag=player]
execute unless entity @a[tag=player,tag=wait_bar] store result score #game.team CT1 if entity @a[tag=player]

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