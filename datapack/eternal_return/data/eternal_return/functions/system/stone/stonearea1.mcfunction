execute as @e[tag=st2] at @s run particle dust{color:[1.000,0.000,0.000],scale:1} ~ ~ ~ 0.05 10 0.05 0 10 force
execute as @e[tag=st2] at @s run tp @s ~ ~ ~ ~10 0
execute as @e[tag=st2] at @s run particle minecraft:end_rod ^ ^0.1 ^1 0 0 0 0 5 force
execute as @e[tag=starea] at @s run particle minecraft:reverse_portal ~ ~0.1 ~ 0.5 0 0.5 0.03 5 force

execute as @e[tag=starea] at @s unless block ~ ~0 ~ sculk run kill @s