execute if score @s aj.animal_bear.animation.move.loop_mode = $aj.loop_mode.loop aj.i run function animated_java:animal_bear/zzzzzzzz/animations/move/end_loop
execute if score @s aj.animal_bear.animation.move.loop_mode = $aj.loop_mode.once aj.i run function animated_java:animal_bear/animations/move/stop
execute if score @s aj.animal_bear.animation.move.loop_mode = $aj.loop_mode.hold aj.i run function animated_java:animal_bear/animations/move/pause