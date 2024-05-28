execute unless entity @a[tag=templetree1] as @e[tag=templetree] on target at @s if block ~ ~-1 ~ minecraft:amethyst_block run tag @s add templetree1
execute as @a[tag=templetree1] run scoreboard players add @s tree 1
execute as @a[tag=templetree1] at @s unless block ~ ~-1 ~ minecraft:amethyst_block run data remove entity @e[tag=templetree,limit=1] interaction
execute as @a[tag=templetree1] at @s unless block ~ ~-1 ~ minecraft:amethyst_block run scoreboard players reset @s tree
execute as @a[tag=templetree1] at @s unless block ~ ~-1 ~ minecraft:amethyst_block run tag @s remove templetree1
execute if entity @a[tag=templetree1,scores={tree=160..}] at @e[tag=templetree] run setblock ~ ~ ~ air
execute as @a[tag=templetree1,scores={tree=160..}] run kill @e[tag=templetree]
execute as @a[tag=templetree1,scores={tree=160..}] run tag @s remove templetree1

execute as @e[tag=templetree] at @s run particle minecraft:happy_villager ~ ~ ~ 1 1 1 0.05 5 force