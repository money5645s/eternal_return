scoreboard players set #bool ER.sys 0
execute if entity @s[nbt={inBlockState:{Name:"minecraft:barrier"}}] run function eternal_return:entity/arrow_pen/arrow_pen/0
execute if score #bool ER.sys matches 0 run function eternal_return:entity/arrow_pen/arrow_pen/1
execute if score #bool ER.sys matches 1 run function eternal_return:entity/arrow_pen/arrow_pen/2
