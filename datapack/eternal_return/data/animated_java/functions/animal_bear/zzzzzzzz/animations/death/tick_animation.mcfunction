scoreboard players add @s aj.animal_bear.animation.death.local_anim_time 1
scoreboard players operation @s aj.anim_time = @s aj.animal_bear.animation.death.local_anim_time
function animated_java:animal_bear/zzzzzzzz/animations/death/apply_frame_as_root
execute if score @s aj.animal_bear.animation.death.local_anim_time matches 32.. run function animated_java:animal_bear/zzzzzzzz/animations/death/end