scoreboard players set @s aj.animal_alpha.animation.death.local_anim_time 0
tag @s remove aj.animal_alpha.animation.death
execute on passengers run data modify entity @s interpolation_duration set value 0
tag @s add aj.animal_alpha.disable_command_keyframes
function animated_java:animal_alpha/zzzzzzzz/animations/death/tree/leaf_0
tag @s remove aj.animal_alpha.disable_command_keyframes