scoreboard players set @s aj.animal_boar.animation.ready.local_anim_time 0
tag @s remove aj.animal_boar.animation.ready
execute on passengers run data modify entity @s interpolation_duration set value 0
tag @s add aj.animal_boar.disable_command_keyframes
function animated_java:animal_boar/zzzzzzzz/animations/ready/tree/leaf_0
tag @s remove aj.animal_boar.disable_command_keyframes