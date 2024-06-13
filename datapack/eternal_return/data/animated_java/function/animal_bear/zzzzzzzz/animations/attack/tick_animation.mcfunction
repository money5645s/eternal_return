scoreboard players add @s aj.animal_bear.animation.attack.local_anim_time 1
scoreboard players operation @s aj.anim_time = @s aj.animal_bear.animation.attack.local_anim_time
function animated_java:animal_bear/zzzzzzzz/animations/attack/apply_frame_as_root
execute if score @s aj.animal_bear.animation.attack.local_anim_time matches 25.. run function animated_java:animal_bear/zzzzzzzz/animations/attack/end