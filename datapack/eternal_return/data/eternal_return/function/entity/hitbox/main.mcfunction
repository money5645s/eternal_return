execute store result score #HPTMP ER.sys run data get entity @s Health
execute unless score #HPTMP ER.sys matches 1000 run function eternal_return:entity/hitbox/hitbox/0 
execute if score @s ER.health matches ..0 on passengers run function eternal_return:entity/hitbox/hitbox/1 
execute if score @s ER.health matches 1.. run function eternal_return:entity/hitbox/hitbox/2 
execute unless score #HPTMP ER.sys matches 1000 run function eternal_return:entity/hitbox/hitbox/3 
data remove storage minecraft:temp temp
