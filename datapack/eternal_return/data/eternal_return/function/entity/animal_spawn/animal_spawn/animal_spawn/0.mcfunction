data modify storage minecraft:temp temp.animal append from storage minecraft:temp temp.animal[0]
data remove storage minecraft:temp temp.animal[0]
execute store result score #this.id ER.sys run data get storage minecraft:temp temp.animal[0].id
execute as @e[tag=ER.animal.root] if score @s df_id = #this.id ER.sys run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/animal_spawn/0
execute if score #isExist ER.sys matches 1 run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/animal_spawn/1
scoreboard players set #ELSE ER.sys 0
execute if score #isExist ER.sys matches 0 run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/animal_spawn/2
scoreboard players set #isExist ER.sys 0
tag @e[tag=this] remove this
