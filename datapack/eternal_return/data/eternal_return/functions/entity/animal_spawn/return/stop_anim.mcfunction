    
# function eternal_return:entity/animal_spawn/remove_id
# function eternal_return:entity/animal_spawn/if_exist

data modify entity @s NoAI set value 1b
execute as @e[tag= this, tag= ER.animal.hitbox] run scoreboard players operation @s ER.health = #this.MaxHP ER.health

execute on passengers as @e[tag= ER.animal.model  ,tag= aj.animal_boar.animation.attack] run function animated_java:animal_boar/animations/attack/stop
execute on passengers as @e[tag= ER.animal.model  ,tag= aj.animal_boar.animation.move] run function animated_java:animal_boar/animations/move/stop

execute on passengers as @e[tag= ER.animal.model  ,tag= aj.animal_bear.animation.attack] run function animated_java:animal_bear/animations/attack/stop
execute on passengers as @e[tag= ER.animal.model  ,tag= aj.animal_bear.animation.move] run function animated_java:animal_bear/animations/move/stop

