

#> function eternal_return:entity/animal/calss/optimize/main

$execute if entity @s[tag= !ER.optimized] at @s unless entity @p[distance=..$(OPTdistance)] as @e[tag= this, tag= ER.animal.model] run function eternal_return:entity/animal/class/optimize/disable_model {animal : "$(animal)"}

$execute if entity @s[tag= ER.optimized] at @s if entity @p[distance=..$(OPTdistance)] run function eternal_return:entity/animal/class/optimize/enable_model {animal : "$(animal)"}