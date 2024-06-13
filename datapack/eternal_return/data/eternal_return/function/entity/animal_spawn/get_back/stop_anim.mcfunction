    
# function eternal_return:entity/animal_spawn/main
# function eternal_return:entity/animal_spawn/stop_anim

data modify entity @s NoAI set value 1b
execute as @e[tag= this, tag= ER.animal.hitbox] run scoreboard players operation @s ER.health = #this.MaxHP ER.health

execute on passengers as @e[tag= ER.animal.model  ,tag= aj.animal_boar.animation.attack] run function animated_java:animal_boar/animations/attack/stop
execute on passengers as @e[tag= ER.animal.model  ,tag= aj.animal_boar.animation.move] run function animated_java:animal_boar/animations/move/stop

execute on passengers as @e[tag= ER.animal.model  ,tag= aj.animal_bear.animation.attack] run function animated_java:animal_bear/animations/attack/stop
execute on passengers as @e[tag= ER.animal.model  ,tag= aj.animal_bear.animation.move] run function animated_java:animal_bear/animations/move/stop

