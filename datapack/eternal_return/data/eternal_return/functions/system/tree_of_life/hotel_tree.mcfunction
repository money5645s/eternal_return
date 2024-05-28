execute unless entity @a[tag=hoteltree1] as @e[tag=hoteltree] on target at @s if block ~ ~-1 ~ minecraft:amethyst_block run tag @s add hoteltree1
execute as @a[tag=hoteltree1] run scoreboard players add @s tree 1
execute as @a[tag=hoteltree1] at @s unless block ~ ~-1 ~ minecraft:amethyst_block run data remove entity @e[tag=hoteltree,limit=1] interaction
execute as @a[tag=hoteltree1] at @s unless block ~ ~-1 ~ minecraft:amethyst_block run scoreboard players reset @s tree
execute as @a[tag=hoteltree1] at @s unless block ~ ~-1 ~ minecraft:amethyst_block run tag @s remove hoteltree1
execute if entity @a[tag=hoteltree1,scores={tree=160..}] at @e[tag=hoteltree] run setblock ~ ~ ~ air
execute as @a[tag=hoteltree1,scores={tree=160..}] run kill @e[tag=hoteltree]
execute as @a[tag=hoteltree1,scores={tree=160..}] run tag @s remove hoteltree1

execute as @e[tag=hoteltree] at @s run particle minecraft:happy_villager ~ ~ ~ 1 1 1 0.05 5 force