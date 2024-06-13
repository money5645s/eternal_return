scoreboard players set @s aj.anim_time 0
scoreboard players set @s aj.animal_boar.animation.ready.local_anim_time 0
scoreboard players set @s aj.animal_boar.animation.ready.loop_mode 0
execute on passengers run data modify entity @s interpolation_duration set value 0
function animated_java:animal_boar/zzzzzzzz/animations/ready/tree/leaf_0
execute on passengers run data modify entity @s interpolation_duration set value 1
tag @s add aj.animal_boar.animation.ready