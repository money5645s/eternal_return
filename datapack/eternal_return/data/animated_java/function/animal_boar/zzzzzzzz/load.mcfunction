scoreboard objectives add aj.i dummy
scoreboard objectives add aj.id dummy
scoreboard objectives add aj.tween_time dummy
scoreboard objectives add aj.anim_time dummy
scoreboard objectives add aj.life_time dummy
scoreboard objectives add aj.animal_boar.export_version dummy
scoreboard objectives add aj.animal_boar.rig_loaded dummy
scoreboard objectives add aj.animal_boar.animation.move.local_anim_time dummy
scoreboard objectives add aj.animal_boar.animation.ready.local_anim_time dummy
scoreboard objectives add aj.animal_boar.animation.death.local_anim_time dummy
scoreboard objectives add aj.animal_boar.animation.attack.local_anim_time dummy
scoreboard objectives add aj.animal_boar.animation.skill.local_anim_time dummy
scoreboard objectives add aj.animal_boar.animation.move.loop_mode dummy
scoreboard objectives add aj.animal_boar.animation.ready.loop_mode dummy
scoreboard objectives add aj.animal_boar.animation.death.loop_mode dummy
scoreboard objectives add aj.animal_boar.animation.attack.loop_mode dummy
scoreboard objectives add aj.animal_boar.animation.skill.loop_mode dummy
scoreboard players set $aj.animal_boar.animation.move aj.id 0
scoreboard players set $aj.animal_boar.animation.ready aj.id 1
scoreboard players set $aj.animal_boar.animation.death aj.id 2
scoreboard players set $aj.animal_boar.animation.attack aj.id 3
scoreboard players set $aj.animal_boar.animation.skill aj.id 4
scoreboard players set $aj.animal_boar.variant.default aj.id 0
scoreboard players add .aj.last_id aj.id 0
scoreboard players set $aj.loop_mode.loop aj.i 0
scoreboard players set $aj.loop_mode.once aj.i 1
scoreboard players set $aj.loop_mode.hold aj.i 2
scoreboard players set aj.animal_boar.export_version aj.i 307573008
scoreboard players reset * aj.animal_boar.rig_loaded
execute as @e[type=minecraft:item_display,tag=aj.animal_boar.root] run function animated_java:animal_boar/zzzzzzzz/on_load