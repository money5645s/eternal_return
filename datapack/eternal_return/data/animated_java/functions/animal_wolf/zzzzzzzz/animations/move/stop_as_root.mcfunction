scoreboard players set @s aj.animal_wolf.animation.move.local_anim_time 0
tag @s remove aj.animal_wolf.animation.move
execute on passengers run data modify entity @s interpolation_duration set value 0
tag @s add aj.animal_wolf.disable_command_keyframes
function animated_java:animal_wolf/zzzzzzzz/animations/move/tree/leaf_0
tag @s remove aj.animal_wolf.disable_command_keyframes