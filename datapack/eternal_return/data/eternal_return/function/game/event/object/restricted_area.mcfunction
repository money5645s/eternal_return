
# 금구 알림
execute if score day time matches 2 if score halfday time matches 1 if score tick time matches 170 as @a[tag=player,tag=in_scheduled_restricted_area] at @s run playsound minecraft:er.restricted_area.left_few_sec_close master @s ~ ~ ~
execute if score day time matches 3..5 if score halfday time matches 0..1 if score tick time matches 170 as @a[tag=player,tag=in_scheduled_restricted_area] at @s run playsound minecraft:er.restricted_area.left_few_sec_close master @s ~ ~ ~
execute if score day time matches 6 if score halfday time matches 0 if score tick time matches 170 as @a[tag=player,tag=in_scheduled_restricted_area] at @s run playsound minecraft:er.restricted_area.left_few_sec_close master @s ~ ~ ~



execute if score day time matches 2 if score halfday time matches 0 if score tick time matches 1 run scoreboard players set msg skript 10
execute if score day time matches 2 if score halfday time matches 1 if score tick time matches 1 run scoreboard players set msg skript 11
execute if score day time matches 3 if score halfday time matches 0 if score tick time matches 1 run scoreboard players set msg skript 12
execute if score day time matches 3 if score halfday time matches 1 if score tick time matches 1 run scoreboard players set msg skript 13
execute if score day time matches 4 if score halfday time matches 0 if score tick time matches 1 run scoreboard players set msg skript 14
execute if score day time matches 4 if score halfday time matches 1 if score tick time matches 1 run scoreboard players set msg skript 15
execute if score day time matches 5 if score halfday time matches 0 if score tick time matches 1 run scoreboard players set msg skript 16
execute if score day time matches 5 if score halfday time matches 1 if score tick time matches 1 run scoreboard players set msg skript 17
execute if score day time matches 6 if score halfday time matches 0 if score tick time matches 1 run scoreboard players set msg skript 18
