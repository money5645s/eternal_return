execute if entity @s[tag=aj.animal_boar.root] run function animated_java:animal_boar/zzzzzzzz/animations/death/tween_resume_as_root
execute if entity @s[tag=!aj.animal_boar.root] run tellraw @a ["",{"text":"["},{"text":"Animated Java","color":"aqua"},{"text":"] "},{"text":"ERROR ☠","color":"red"},{"text":" > ","color":"gray"},[{"text":"The function","color":"yellow"},{"text":" animated_java:animal_boar/animations/death/tween_resume ","color":"blue"},{"text":"must be run"},{"text":" as ","color":"red"},{"text":"the root entity!"}]]