execute if score @s aj.animal_wolf.animation.ready.loop_mode = $aj.loop_mode.loop aj.i run function animated_java:animal_wolf/zzzzzzzz/animations/ready/end_loop
execute if score @s aj.animal_wolf.animation.ready.loop_mode = $aj.loop_mode.once aj.i run function animated_java:animal_wolf/animations/ready/stop
execute if score @s aj.animal_wolf.animation.ready.loop_mode = $aj.loop_mode.hold aj.i run function animated_java:animal_wolf/animations/ready/pause