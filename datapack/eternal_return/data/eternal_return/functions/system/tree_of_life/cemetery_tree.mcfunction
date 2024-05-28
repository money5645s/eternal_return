execute unless entity @a[tag=cemeterytree1] as @e[tag=cemeterytree] on target at @s if block ~ ~-1 ~ minecraft:amethyst_block run tag @s add cemeterytree1
execute as @a[tag=cemeterytree1] run scoreboard players add @s tree 1
execute as @a[tag=cemeterytree1] at @s unless block ~ ~-1 ~ minecraft:amethyst_block run data remove entity @e[tag=cemeterytree,limit=1] interaction
execute as @a[tag=cemeterytree1] at @s unless block ~ ~-1 ~ minecraft:amethyst_block run scoreboard players reset @s tree
execute as @a[tag=cemeterytree1] at @s unless block ~ ~-1 ~ minecraft:amethyst_block run tag @s remove cemeterytree1
execute if entity @a[tag=cemeterytree1,scores={tree=160..}] at @e[tag=cemeterytree] run setblock ~ ~ ~ air
execute as @a[tag=cemeterytree1,scores={tree=160..}] run kill @e[tag=cemeterytree]
execute as @a[tag=cemeterytree1,scores={tree=160..}] run tag @s remove cemeterytree1

execute as @e[tag=cemeterytree] at @s run particle minecraft:happy_villager ~ ~ ~ 1 1 1 0.05 5 force