scoreboard objectives add aj.i dummy
scoreboard objectives add aj.id dummy
scoreboard objectives add aj.tween_time dummy
scoreboard objectives add aj.anim_time dummy
scoreboard objectives add aj.life_time dummy
scoreboard objectives add aj.animal_bear.export_version dummy
scoreboard objectives add aj.animal_bear.rig_loaded dummy
scoreboard objectives add aj.animal_bear.animation.move.local_anim_time dummy
scoreboard objectives add aj.animal_bear.animation.attack.local_anim_time dummy
scoreboard objectives add aj.animal_bear.animation.death.local_anim_time dummy
scoreboard objectives add aj.animal_bear.animation.ready.local_anim_time dummy
scoreboard objectives add aj.animal_bear.animation.move.loop_mode dummy
scoreboard objectives add aj.animal_bear.animation.attack.loop_mode dummy
scoreboard objectives add aj.animal_bear.animation.death.loop_mode dummy
scoreboard objectives add aj.animal_bear.animation.ready.loop_mode dummy
scoreboard players set $aj.animal_bear.animation.move aj.id 0
scoreboard players set $aj.animal_bear.animation.attack aj.id 1
scoreboard players set $aj.animal_bear.animation.death aj.id 2
scoreboard players set $aj.animal_bear.animation.ready aj.id 3
scoreboard players set $aj.animal_bear.variant.default aj.id 0
scoreboard players add .aj.last_id aj.id 0
scoreboard players set $aj.loop_mode.loop aj.i 0
scoreboard players set $aj.loop_mode.once aj.i 1
scoreboard players set $aj.loop_mode.hold aj.i 2
scoreboard players set aj.animal_bear.export_version aj.i -130433838
scoreboard players reset * aj.animal_bear.rig_loaded
execute as @e[type=minecraft:item_display,tag=aj.animal_bear.root] run function animated_java:animal_bear/zzzzzzzz/on_load