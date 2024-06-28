scoreboard objectives add aj.i dummy
scoreboard objectives add aj.id dummy
scoreboard objectives add aj.tween_time dummy
scoreboard objectives add aj.anim_time dummy
scoreboard objectives add aj.life_time dummy
scoreboard objectives add aj.animal_alpha.export_version dummy
scoreboard objectives add aj.animal_alpha.rig_loaded dummy
scoreboard objectives add aj.animal_alpha.animation.skill.local_anim_time dummy
scoreboard objectives add aj.animal_alpha.animation.attack.local_anim_time dummy
scoreboard objectives add aj.animal_alpha.animation.death.local_anim_time dummy
scoreboard objectives add aj.animal_alpha.animation.ready.local_anim_time dummy
scoreboard objectives add aj.animal_alpha.animation.move.local_anim_time dummy
scoreboard objectives add aj.animal_alpha.animation.skill.loop_mode dummy
scoreboard objectives add aj.animal_alpha.animation.attack.loop_mode dummy
scoreboard objectives add aj.animal_alpha.animation.death.loop_mode dummy
scoreboard objectives add aj.animal_alpha.animation.ready.loop_mode dummy
scoreboard objectives add aj.animal_alpha.animation.move.loop_mode dummy
scoreboard players set $aj.animal_alpha.animation.skill aj.id 0
scoreboard players set $aj.animal_alpha.animation.attack aj.id 1
scoreboard players set $aj.animal_alpha.animation.death aj.id 2
scoreboard players set $aj.animal_alpha.animation.ready aj.id 3
scoreboard players set $aj.animal_alpha.animation.move aj.id 4
scoreboard players set $aj.animal_alpha.variant.default aj.id 0
scoreboard players add .aj.last_id aj.id 0
scoreboard players set $aj.loop_mode.loop aj.i 0
scoreboard players set $aj.loop_mode.once aj.i 1
scoreboard players set $aj.loop_mode.hold aj.i 2
scoreboard players set aj.animal_alpha.export_version aj.i -1135165698
scoreboard players reset * aj.animal_alpha.rig_loaded
execute as @e[type=minecraft:item_display,tag=aj.animal_alpha.root] run function animated_java:animal_alpha/zzzzzzzz/on_load