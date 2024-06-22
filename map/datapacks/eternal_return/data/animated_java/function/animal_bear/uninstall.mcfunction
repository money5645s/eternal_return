scoreboard objectives remove aj.i
scoreboard objectives remove aj.id
scoreboard objectives remove aj.tween_time
scoreboard objectives remove aj.anim_time
scoreboard objectives remove aj.life_time
scoreboard objectives remove aj.animal_bear.export_version
scoreboard objectives remove aj.animal_bear.rig_loaded
scoreboard objectives remove aj.animal_bear.animation.move.local_anim_time
scoreboard objectives remove aj.animal_bear.animation.attack.local_anim_time
scoreboard objectives remove aj.animal_bear.animation.death.local_anim_time
scoreboard objectives remove aj.animal_bear.animation.ready.local_anim_time
scoreboard objectives remove aj.animal_bear.animation.move.loop_mode
scoreboard objectives remove aj.animal_bear.animation.attack.loop_mode
scoreboard objectives remove aj.animal_bear.animation.death.loop_mode
scoreboard objectives remove aj.animal_bear.animation.ready.loop_mode
tellraw @a ["",{"text":"["},{"text":"Animated Java","color":"aqua"},{"text":"] "},[{"text":"INFO ℹ","color":"green"},{"text":" > ","color":"gray"},{"text":"The animal_bear Rig has been uninstalled successfully.\n"},{"text":"Please remove the Rig's functions from the datapack before reloading.","color":"gray"}]]