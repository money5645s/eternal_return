scoreboard players add @s aj.animal_wolf.animation.attack.local_anim_time 1
scoreboard players operation @s aj.anim_time = @s aj.animal_wolf.animation.attack.local_anim_time
function animated_java:animal_wolf/zzzzzzzz/animations/attack/apply_frame_as_root
execute if score @s aj.animal_wolf.animation.attack.local_anim_time matches 35.. run function animated_java:animal_wolf/zzzzzzzz/animations/attack/end