
data modify storage minecraft:temp temp.animal set from entity @s data.animal
function eternal_return:entity/animal_spawn/animal_spawn/0
data modify entity @s data.animal set from storage minecraft:temp temp.animal
scoreboard players set #ELSE ER.sys 0
scoreboard players set #HasNoID ER.sys 0
scoreboard players set #bool ER.sys 0
data remove storage minecraft:temp temp
