scoreboard players set @s aj.animal_alpha.animation.skill.local_anim_time 0
tag @s remove aj.animal_alpha.animation.skill
execute on passengers run data modify entity @s interpolation_duration set value 0
tag @s add aj.animal_alpha.disable_command_keyframes
function animated_java:animal_alpha/zzzzzzzz/animations/skill/tree/leaf_0
tag @s remove aj.animal_alpha.disable_command_keyframes