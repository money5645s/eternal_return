execute store result storage player_data temp[5].credit int 1 run scoreboard players get @s credit
function eternal_return:system/player/credit/paste_to_level with storage minecraft:player_data temp[5]
data modify storage player_data temp[5].credit set value 0