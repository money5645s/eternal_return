scoreboard players set @s aj.anim_time 0
scoreboard players set @s aj.animal_wolf.animation.death.local_anim_time 0
scoreboard players set @s aj.animal_wolf.animation.death.loop_mode 1
execute on passengers run data modify entity @s interpolation_duration set value 0
function animated_java:animal_wolf/zzzzzzzz/animations/death/tree/leaf_0
execute on passengers run data modify entity @s interpolation_duration set value 1
tag @s add aj.animal_wolf.animation.death