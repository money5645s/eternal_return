title @a actionbar "debg"
scoreboard players remove #game_tick time 1
execute if score #game_tick time matches ..0 run scoreboard players remove #game_sec time 1
execute if score #game_tick time matches ..0 run scoreboard players set #game_tick time 20

execute if score #game_sec time matches -1 if score #game_min time matches 1.. run scoreboard players remove #game_min time 1
execute if score #game_sec time matches -1 run scoreboard players set #game_sec time 59

execute if score #game_min time matches 0 if score #game_min time matches 0 if score #game_tick time matches 0 run \
    scoreboard players add #game_halfday time 1

execute if score #game_halfday time matches 2.. if score #game_day time matches ..7 run scoreboard players add #game_day time 1
execute if score #game_halfday time matches 2.. run scoreboard players set #game_halfday time 0
