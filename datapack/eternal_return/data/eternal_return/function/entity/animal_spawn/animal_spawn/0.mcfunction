execute if score @s ER.sys matches 0 run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/0 
execute if score @s ER.sys matches 1.. run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/1 
execute if score #HasNoID ER.sys matches 1 if entity @p[distance=0..20] at @s[tag=ER.spawn.animal.boar] run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/2 
execute if score #HasNoID ER.sys matches 1 if entity @p[distance=0..20] at @s[tag=ER.spawn.animal.bear] run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/3 
execute if score #HasNoID ER.sys matches 1 if entity @p[distance=0..20] at @s[tag=ER.spawn.animal.wolf] run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/4 
execute if score #HasNoID ER.sys matches 1 if entity @p[distance=0..20] at @s[tag=ER.spawn.animal.alpha] run function eternal_return:entity/animal_spawn/animal_spawn/animal_spawn/5 
