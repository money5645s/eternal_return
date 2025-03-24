scoreboard players add @s nathapon_marker 1
execute rotated as @n[tag=nathapon] run tp @s ^ ^ ^1
particle minecraft:trial_spawner_detection_ominous ~ ~ ~ 0.15 0.15 0.15 0 5 force

execute if entity @s[scores={nathapon_marker=6..}] at @s unless entity @e[tag=nathapon_target] run scoreboard players set @n[tag=nathapon,scores={active_level=1}] active_cool 40
execute if entity @s[scores={nathapon_marker=6..}] at @s unless entity @e[tag=nathapon_target] run scoreboard players set @n[tag=nathapon,scores={active_level=2}] active_cool 37
execute if entity @s[scores={nathapon_marker=6..}] at @s unless entity @e[tag=nathapon_target] run scoreboard players set @n[tag=nathapon,scores={active_level=3}] active_cool 34
execute if entity @s[scores={nathapon_marker=6..}] at @s unless entity @e[tag=nathapon_target] run scoreboard players set @n[tag=nathapon,scores={active_level=4}] active_cool 31
execute if entity @s[scores={nathapon_marker=6..}] at @s unless entity @e[tag=nathapon_target] run scoreboard players set @n[tag=nathapon,scores={active_level=5}] active_cool 25
execute at @s as @n[distance=..1.5,tag=!nathapon,type=!marker] run tag @s add nathapon_target
execute if entity @s[scores={nathapon_marker=6..}] run kill @s