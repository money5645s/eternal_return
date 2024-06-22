scoreboard players add @s aj.animal_alpha.animation.skill.local_anim_time 1
scoreboard players operation @s aj.anim_time = @s aj.animal_alpha.animation.skill.local_anim_time
function animated_java:animal_alpha/zzzzzzzz/animations/skill/apply_frame_as_root
execute if score @s aj.animal_alpha.animation.skill.local_anim_time matches 65.. run function animated_java:animal_alpha/zzzzzzzz/animations/skill/end