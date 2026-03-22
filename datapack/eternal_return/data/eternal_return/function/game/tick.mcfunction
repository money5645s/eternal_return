

# 게임 시작전 타이머
execute if score game.start time matches 0 run function eternal_return:game/briefing_room
# 상단 타이머
execute if score day time matches ..6 if score game.start time matches 1 run function eternal_return:game/gamestart_tick
# 이벤트 타임라인

execute if score game.start time matches 1 run function eternal_return:game/event/object
execute if score game.start time matches 1 if score #exp.tick time matches ..30 run scoreboard players add #exp.tick time 1
execute if score game.start time matches 1 if score #credit.tick time matches ..30 run scoreboard players add #credit.tick time 1
# 크래딧 및 경험치 지급
execute if score game.start time matches 1 if score #exp.tick time matches 31 run scoreboard players add @a[tag=player] exp 12
execute if score game.start time matches 1 if score #credit.tick time matches 31 run scoreboard players add @a[tag=player] credit 1
# 쿨타임 초기화
execute if score game.start time matches 1 if score #exp.tick time matches 31 run scoreboard players set #exp.tick time 0
execute if score game.start time matches 1 if score #credit.tick time matches 31 run scoreboard players set #credit.tick time 0



execute store result score #player_count NUM if entity @a[tag=player]
execute if score game.start time matches 1 if score #player_count NUM matches 1 run scoreboard players add @a win.tick 1