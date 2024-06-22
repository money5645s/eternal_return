execute at @s as @e[tag=ER.animal.bear,tag=ER.animal.root,tag=this] if entity @s[distance=20..] run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/animal_spawn/animal_spawn/0
execute if score #ELSE ER.sys matches 1 run return 1
execute at @s as @e[tag=ER.animal.boar,tag=ER.animal.root,tag=this] if entity @s[distance=20..] run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/animal_spawn/animal_spawn/1
execute if score #ELSE ER.sys matches 1 run return 1
execute at @s as @e[tag=ER.animal.wolf,tag=ER.animal.root,tag=this] if entity @s[distance=20..] run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/animal_spawn/animal_spawn/2
execute if score #ELSE ER.sys matches 1 run return 1
execute at @s as @e[tag=ER.animal.alpha,tag=ER.animal.root,tag=this] if entity @s[distance=20..] run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/animal_spawn/animal_spawn/3
execute if score #ELSE ER.sys matches 1 run return 1
