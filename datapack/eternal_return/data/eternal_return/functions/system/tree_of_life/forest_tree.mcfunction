execute unless entity @a[tag=foresttree1] as @e[tag=foresttree] on target at @s if block ~ ~-1 ~ minecraft:amethyst_block run tag @s add foresttree1
execute as @a[tag=foresttree1] run scoreboard players add @s tree 1
execute as @a[tag=foresttree1] at @s unless block ~ ~-1 ~ minecraft:amethyst_block run data remove entity @e[tag=foresttree,limit=1] interaction
execute as @a[tag=foresttree1] at @s unless block ~ ~-1 ~ minecraft:amethyst_block run scoreboard players reset @s tree
execute as @a[tag=foresttree1] at @s unless block ~ ~-1 ~ minecraft:amethyst_block run tag @s remove foresttree1
execute if entity @a[tag=foresttree1,scores={tree=160..}] at @e[tag=foresttree] run setblock ~ ~ ~ air
execute as @a[tag=foresttree1,scores={tree=160..}] run kill @e[tag=foresttree]
execute as @a[tag=foresttree1,scores={tree=160..}] run tag @s remove foresttree1

execute as @e[tag=foresttree] at @s run particle minecraft:happy_villager ~ ~ ~ 1 1 1 0.05 5 force