function eternal_return:entity/hitbox/b1/b4/b3/b2/b1/code with storage minecraft:temp temp
execute if score #i ER.sys < #HPkey ER.sys run function eternal_return:entity/hitbox/b1/b4/b3/b2/b2/code
scoreboard players add #i ER.sys 1
execute store result storage minecraft:temp temp.i int 1 run scoreboard players get #i ER.sys
execute if score #i ER.sys matches ..99 run function eternal_return:entity/hitbox/b1/b4/b3/b2/code 
