execute unless score @s aj.animal_alpha.rig_loaded = @s aj.animal_alpha.rig_loaded run function animated_java:animal_alpha/zzzzzzzz/on_load
scoreboard players add @s aj.life_time 1
execute at @s on passengers run tp @s ~ ~ ~ ~ ~
function animated_java:animal_alpha/zzzzzzzz/animations/tick
function #animated_java:animal_alpha/on_tick/as_root