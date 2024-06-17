execute unless score @e[tag=daytimer,limit=1] dayminute matches 1.. as @e[tag=daytimer,scores={daysecond=1..}] run scoreboard players add @s daytime 1
execute as @e[tag=daytimer,scores={dayminute=1..}] run scoreboard players add @s daytime 1
execute as @e[tag=daytimer,scores={daytime=20..}] run scoreboard players remove @s daysecond 1
execute as @e[tag=daytimer,scores={daysecond=..-1}] run scoreboard players remove @s dayminute 1
execute as @e[tag=daytimer,scores={daysecond=..-1}] run scoreboard players set @s daysecond 59
scoreboard players set @e[tag=daytimer,scores={daytime=20..}] daytime 0