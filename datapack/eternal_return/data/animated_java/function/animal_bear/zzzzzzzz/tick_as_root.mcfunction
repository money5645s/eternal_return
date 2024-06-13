execute unless score @s aj.animal_bear.rig_loaded = @s aj.animal_bear.rig_loaded run function animated_java:animal_bear/zzzzzzzz/on_load
scoreboard players add @s aj.life_time 1
execute at @s on passengers run tp @s ~ ~ ~ ~ ~
function animated_java:animal_bear/zzzzzzzz/animations/tick
function #animated_java:animal_bear/on_tick/as_root