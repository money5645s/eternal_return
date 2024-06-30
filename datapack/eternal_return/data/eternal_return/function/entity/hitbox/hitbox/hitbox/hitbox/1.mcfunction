data modify storage minecraft:temp temp.HPdata append value "-0"
execute if score #i ER.sys matches 100 run return run function eternal_return:entity/hitbox/hitbox/hitbox/hitbox/hitbox/0 
execute if score #i ER.sys >= #HPkey ER.sys run function eternal_return:entity/hitbox/hitbox/hitbox/hitbox/hitbox/1 
execute if score #i ER.sys < #HPkey ER.sys run function eternal_return:entity/hitbox/hitbox/hitbox/hitbox/hitbox/2 
scoreboard players set #temp ER.sys 1
scoreboard players operation #i ER.sys += #temp ER.sys
execute store result storage minecraft:temp temp.i int 1 run scoreboard players get #i ER.sys
execute if score #i ER.sys matches ..100 run return run function eternal_return:entity/hitbox/hitbox/hitbox/hitbox/1 
