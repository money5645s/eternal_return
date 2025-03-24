scoreboard players add @e[tag=nathapon_target] nathapon_target 1

execute as @e[tag=nathapon_target] at @s run particle minecraft:trial_spawner_detection_ominous ~ ~ ~ 0.3 0.3 0.3 0 3 force

execute as @e[scores={nathapon_target=1}] at @s run summon marker ~ ~ ~ {Tags:["nathapon_target_marker"]}
execute as @e[tag=nathapon_target_marker] at @s run tp @s ~ ~ ~ ~10 0
execute at @e[tag=nathapon_target_marker] run particle end_rod ^ ^0.1 ^5 0 0 0 0 3
execute as @e[tag=nathapon_target_marker] if entity @n[scores={nathapon_target=100}] run kill @s

execute as @e[tag=nathapon_target] at @s at @e[tag=nathapon_target_marker,distance=5..] run function eternal_return:character/list/nathapon/active/kill

execute if entity @e[tag=nathapon_target] run tag @s add nathapon_active_hit
execute if entity @e[scores={nathapon_target=100..}] run function eternal_return:character/list/nathapon/active/cooltime

tag @n[scores={nathapon_target=100..}] remove nathapon_target
scoreboard players reset @n[scores={nathapon_target=100..}] nathapon_target

execute if entity @e[scores={nathapon_target=1..}] as @e[scores={nathapon_target=1..}] unless entity @s[tag=nathapon_target] run scoreboard players reset @s nathapon_target
execute as @e[tag=nathapon_target] run say 1