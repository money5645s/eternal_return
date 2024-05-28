execute as @a[tag=credit] run scoreboard players add @s credittime 1
execute as @a[tag=credit,scores={credittime=20..}] run xp add @s 1 levels
execute as @a[tag=credit] store result score @s credit run xp query @s levels

execute as @a[tag=credit,scores={credittime=1..}] run stopsound @s * minecraft:entity.player.levelup
scoreboard players set @a[tag=credit,scores={credittime=20..}] credittime 0