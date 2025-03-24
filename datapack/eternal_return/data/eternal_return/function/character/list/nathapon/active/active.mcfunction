scoreboard players set @s active_cool 1
execute if entity @s[tag=!nathapon_active_hit] at @s positioned ~ ~1.25 ~ rotated as @s run summon marker ^ ^ ^ {Tags:["nathapon_marker"]}

execute if entity @s[tag=nathapon_active_hit] run tp @n[tag=nathapon_target] @n[tag=nathapon_target_marker]
execute if entity @s[tag=nathapon_active_hit,scores={active_level=1}] run damage @n[tag=nathapon_target] 4 out_of_world by @n[tag=nathapon]
execute if entity @s[tag=nathapon_active_hit,scores={active_level=2}] run damage @n[tag=nathapon_target] 5 out_of_world by @n[tag=nathapon]
execute if entity @s[tag=nathapon_active_hit,scores={active_level=3}] run damage @n[tag=nathapon_target] 6 out_of_world by @n[tag=nathapon]
execute if entity @s[tag=nathapon_active_hit,scores={active_level=4}] run damage @n[tag=nathapon_target] 7 out_of_world by @n[tag=nathapon]
execute if entity @s[tag=nathapon_active_hit,scores={active_level=5}] run damage @n[tag=nathapon_target] 8 out_of_world by @n[tag=nathapon]
execute if entity @s[tag=nathapon_active_hit] run tag @n[tag=nathapon_target] remove nathapon_target
execute if entity @s[tag=nathapon_active_hit] run kill @n[tag=nathapon_target_marker]

function eternal_return:character/list/nathapon/active/cooltime