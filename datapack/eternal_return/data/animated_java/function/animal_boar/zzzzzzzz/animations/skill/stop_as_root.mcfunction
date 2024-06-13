scoreboard players set @s aj.animal_boar.animation.skill.local_anim_time 0
tag @s remove aj.animal_boar.animation.skill
execute on passengers run data modify entity @s interpolation_duration set value 0
tag @s add aj.animal_boar.disable_command_keyframes
function animated_java:animal_boar/zzzzzzzz/animations/skill/tree/leaf_0
tag @s remove aj.animal_boar.disable_command_keyframes