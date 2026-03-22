## 승전보
# 상위 함수 : function eternal_return:player/tick
# 입력 : @s pdb:main

execute if score @s kill_player matches 1 run effect give @s minecraft:instant_health 1 0
execute if score @s kill_player matches 1.. run playsound minecraft:er.player.max_credit master @s ~ ~ ~ 1 1
execute if score @s kill_player matches 1.. run scoreboard players add @s bantime 7
execute if score @s kill_player matches 1.. run scoreboard players add @s credit 100
execute if score @s kill_player matches 1.. run scoreboard players add @s exp 500
execute if score @s kill_player matches 1.. run scoreboard players set @s kill_player 0