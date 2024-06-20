scoreboard players set @s aj.animal_alpha.animation.attack.local_anim_time 0
tag @s remove aj.animal_alpha.animation.attack
execute on passengers run data modify entity @s interpolation_duration set value 0
tag @s add aj.animal_alpha.disable_command_keyframes
function animated_java:animal_alpha/zzzzzzzz/animations/attack/tree/leaf_0
tag @s remove aj.animal_alpha.disable_command_keyframes