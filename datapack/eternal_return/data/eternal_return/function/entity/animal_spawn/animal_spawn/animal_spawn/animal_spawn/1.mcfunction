scoreboard players set #isExist ER.sys 1
execute as @e[type=minecraft:ghast,tag=ER.animal.hitbox] if score @s df_id = #this.id ER.sys run tag @s add this
scoreboard players operation #this.MaxHP ER.health = @s ER.health
execute as @s[tag=ER.animal.bear,distance=20..] run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/animal_spawn/animal_spawn/0 
execute if score #ELSE ER.sys matches 1 run return 1
execute as @s[tag=ER.animal.boar,distance=20..] run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/animal_spawn/animal_spawn/1 
execute if score #ELSE ER.sys matches 1 run return 1
execute as @s[tag=ER.animal.wolf,distance=20..] run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/animal_spawn/animal_spawn/2 
execute if score #ELSE ER.sys matches 1 run return 1
execute as @s[tag=ER.animal.alpha,distance=20..] run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/animal_spawn/animal_spawn/3 
execute if score #ELSE ER.sys matches 1 run return 1
