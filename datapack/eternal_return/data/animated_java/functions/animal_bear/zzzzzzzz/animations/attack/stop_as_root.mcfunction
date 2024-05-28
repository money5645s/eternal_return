scoreboard players set @s aj.animal_bear.animation.attack.local_anim_time 0
tag @s remove aj.animal_bear.animation.attack
execute on passengers run data modify entity @s interpolation_duration set value 0
tag @s add aj.animal_bear.disable_command_keyframes
function animated_java:animal_bear/zzzzzzzz/animations/attack/tree/leaf_0
tag @s remove aj.animal_bear.disable_command_keyframes