scoreboard objectives add aj.i dummy
scoreboard objectives add aj.id dummy
scoreboard objectives add aj.tween_time dummy
scoreboard objectives add aj.anim_time dummy
scoreboard objectives add aj.life_time dummy
scoreboard objectives add aj.animal_wolf.export_version dummy
scoreboard objectives add aj.animal_wolf.rig_loaded dummy
scoreboard objectives add aj.animal_wolf.animation.move.local_anim_time dummy
scoreboard objectives add aj.animal_wolf.animation.ready.local_anim_time dummy
scoreboard objectives add aj.animal_wolf.animation.death.local_anim_time dummy
scoreboard objectives add aj.animal_wolf.animation.attack.local_anim_time dummy
scoreboard objectives add aj.animal_wolf.animation.move.loop_mode dummy
scoreboard objectives add aj.animal_wolf.animation.ready.loop_mode dummy
scoreboard objectives add aj.animal_wolf.animation.death.loop_mode dummy
scoreboard objectives add aj.animal_wolf.animation.attack.loop_mode dummy
scoreboard players set $aj.animal_wolf.animation.move aj.id 0
scoreboard players set $aj.animal_wolf.animation.ready aj.id 1
scoreboard players set $aj.animal_wolf.animation.death aj.id 2
scoreboard players set $aj.animal_wolf.animation.attack aj.id 3
scoreboard players set $aj.animal_wolf.variant.default aj.id 0
scoreboard players add .aj.last_id aj.id 0
scoreboard players set $aj.loop_mode.loop aj.i 0
scoreboard players set $aj.loop_mode.once aj.i 1
scoreboard players set $aj.loop_mode.hold aj.i 2
scoreboard players set aj.animal_wolf.export_version aj.i 79372640
scoreboard players reset * aj.animal_wolf.rig_loaded
execute as @e[type=minecraft:item_display,tag=aj.animal_wolf.root] run function animated_java:animal_wolf/zzzzzzzz/on_load