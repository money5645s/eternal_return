## 승전보
# 상위 함수 : function eternal_return:player/tick
# 입력 : @s pdb:main

execute if score @s kill_player matches 1 run effect give @s minecraft:instant_health 1 0
execute if score @s kill_player matches 1.. run scoreboard players set @s kill_player 0