scoreboard players add @a[tag=rightclick_cool] rightclick_cool 1
execute as @a[scores={rightclick_cool=5..}] run tag @s remove rightclick_cool
execute as @a[scores={rightclick_cool=5..}] run scoreboard players reset @s rightclick_cool
tag @a[tag=rightclick] remove rightclick