execute if score @s cw_hp_current > @s hp_dmg run scoreboard players operation @s cw_hp_current -= @s hp_dmg
execute if score @s cw_hp_current > @s hp_dmg store result entity @s Health float 1 run scoreboard players get @s cw_hp_current
execute if score @s cw_hp_current <= @s hp_dmg run kill @s
scoreboard players set @s hp_dmg 0
