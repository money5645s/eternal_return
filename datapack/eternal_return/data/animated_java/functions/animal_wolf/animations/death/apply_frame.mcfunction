execute if entity @s[tag=aj.animal_wolf.root] run function animated_java:animal_wolf/zzzzzzzz/animations/death/apply_frame_as_root
execute if entity @s[tag=!aj.animal_wolf.root] run tellraw @a ["",{"text":"["},{"text":"Animated Java","color":"aqua"},{"text":"] "},{"text":"ERROR ☠","color":"red"},{"text":" > ","color":"gray"},[{"text":"The function","color":"yellow"},{"text":" animated_java:animal_wolf/animations/death/apply_frame ","color":"blue"},{"text":"must be run"},{"text":" as ","color":"red"},{"text":"the root entity!"}]]