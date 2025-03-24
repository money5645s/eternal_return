execute if entity @s[tag=nathapon_active_hit,scores={active_level=1}] run scoreboard players set @s active_cool 40
execute if entity @s[tag=nathapon_active_hit,scores={active_level=2}] run scoreboard players set @s active_cool 37
execute if entity @s[tag=nathapon_active_hit,scores={active_level=3}] run scoreboard players set @s active_cool 34
execute if entity @s[tag=nathapon_active_hit,scores={active_level=4}] run scoreboard players set @s active_cool 31
execute if entity @s[tag=nathapon_active_hit,scores={active_level=5}] run scoreboard players set @s active_cool 25
execute if entity @s[tag=nathapon_active_hit] run tag @s remove nathapon_active_hit