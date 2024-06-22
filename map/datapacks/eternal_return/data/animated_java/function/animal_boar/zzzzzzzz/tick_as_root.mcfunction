execute unless score @s aj.animal_boar.rig_loaded = @s aj.animal_boar.rig_loaded run function animated_java:animal_boar/zzzzzzzz/on_load
scoreboard players add @s aj.life_time 1
execute at @s on passengers run tp @s ~ ~ ~ ~ ~
function animated_java:animal_boar/zzzzzzzz/animations/tick
function #animated_java:animal_boar/on_tick/as_root