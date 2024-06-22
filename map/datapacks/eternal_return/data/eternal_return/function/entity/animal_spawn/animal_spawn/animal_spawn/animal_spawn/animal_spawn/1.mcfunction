data modify entity @s NoAI set value 1b
scoreboard players operation @e[tag=this,tag=ER.animal.hitbox] ER.health = #this.MaxHP ER.health
execute on passengers as @e[tag=ER.animal.model,tag=aj.animal_boar.animation.attack] run function animated_java:animal_boar/animations/attack/stop
execute on passengers as @e[tag=ER.animal.model,tag=aj.animal_boar.animation.move] run function animated_java:animal_boar/animations/move/stop
execute on passengers as @e[tag=ER.animal.model,tag=aj.animal_bear.animation.attack] run function animated_java:animal_boar/animations/attack/stop
execute on passengers as @e[tag=ER.animal.model,tag=aj.animal_bear.animation.move] run function animated_java:animal_boar/animations/move/stop
data modify entity @s Pos set from storage minecraft:temp temp.animal[0].Pos
scoreboard players set #ELSE ER.sys 1
