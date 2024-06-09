execute unless score @s aj.animal_wolf.rig_loaded = @s aj.animal_wolf.rig_loaded run function animated_java:animal_wolf/zzzzzzzz/on_load
scoreboard players add @s aj.life_time 1
execute at @s on passengers run tp @s ~ ~ ~ ~ ~
function animated_java:animal_wolf/zzzzzzzz/animations/tick
function #animated_java:animal_wolf/on_tick/as_root
