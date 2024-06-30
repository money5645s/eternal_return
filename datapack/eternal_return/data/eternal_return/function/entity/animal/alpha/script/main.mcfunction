scoreboard players operation #this.ID ER.sys = @s df_id
execute as @e[tag= ER.animal.alpha] if score #this.ID ER.sys = @s df_id run function eternal_return:entity/animal/alpha/script/script/0 
scoreboard players operation #this.HP ER.sys = @e[type=minecraft:ghast       ,tag=this,tag=ER.animal.hitbox] ER.health
scoreboard players operation #this.MaxHP ER.sys = @e[type=minecraft:zombie      ,tag=this,tag=ER.animal.root  ] ER.health
execute if entity @e[type=minecraft:zombie      ,tag=this,tag=ER.animal.root  ,nbt={NoAI:1b}] run function eternal_return:entity/animal/alpha/script/script/1 
execute unless entity @e[type=minecraft:zombie      ,tag=this,tag=ER.animal.root  ,nbt={NoAI:1b}] run function eternal_return:entity/animal/alpha/script/script/2 
data modify storage minecraft:temp temp set value {animal : "alpha", OPTdistance : 20, damage : 5, attackTick : 12, attackDistance : 5}
title @a actionbar [{"score":{"name":"#this.HP","objective":"ER.sys"}},{"text":"  "},{"nbt":"temp","storage":"minecraft:temp"}]
function eternal_return:entity/animal/class/optimize/main with storage minecraft:temp temp
execute if score #this.HP ER.sys matches 1.. if score #this.AI ER.sys matches 0 run function eternal_return:entity/animal/alpha/script/script/3 
execute if score #this.HP ER.sys matches 1.. if score #this.AI ER.sys matches 1 run function eternal_return:entity/animal/alpha/script/script/4 
execute if score #this.HP ER.sys matches ..0 run function eternal_return:entity/animal/alpha/script/script/5 
execute at @s run tp @e[type=minecraft:ghast       ,tag=this,tag=ER.animal.hitbox] ~ ~ ~
data remove storage minecraft:temp temp
scoreboard players set #this.AI ER.sys 0
scoreboard players set #MotionExist ER.sys 0
tag @e[tag=this] remove this
