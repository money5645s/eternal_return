kill @n[tag=nathapon_target_marker]
tag @s remove nathapon_target

tag @n[tag=nathapon] remove nathapon_active_hit
scoreboard players set @n[tag=nathapon,scores={active_level=1}] active_cool 40
scoreboard players set @n[tag=nathapon,scores={active_level=2}] active_cool 37
scoreboard players set @n[tag=nathapon,scores={active_level=3}] active_cool 34
scoreboard players set @n[tag=nathapon,scores={active_level=4}] active_cool 31
scoreboard players set @n[tag=nathapon,scores={active_level=5}] active_cool 25