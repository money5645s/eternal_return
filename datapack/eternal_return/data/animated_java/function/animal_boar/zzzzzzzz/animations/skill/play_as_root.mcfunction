scoreboard players set @s aj.anim_time 0
scoreboard players set @s aj.animal_boar.animation.skill.local_anim_time 0
scoreboard players set @s aj.animal_boar.animation.skill.loop_mode 1
execute on passengers run data modify entity @s interpolation_duration set value 0
function animated_java:animal_boar/zzzzzzzz/animations/skill/tree/leaf_0
execute on passengers run data modify entity @s interpolation_duration set value 1
tag @s add aj.animal_boar.animation.skill