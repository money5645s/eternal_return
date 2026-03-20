
# 리스폰 타이머

execute if score day time matches 2 if score halfday time matches 1 if score tick time matches 5 run scoreboard players set respawn time 1
execute if score day time matches 3 if score halfday time matches 0 if score tick time matches 5 run scoreboard players set respawn time 2
execute if score day time matches 3 if score halfday time matches 1 if score tick time matches 1749 run scoreboard players set msg skript 20
execute if score day time matches 3 if score halfday time matches 1 if score tick time matches 1780 as @a if score @s resurrection matches 1 run scoreboard players add @s credit 100
execute if score day time matches 3 if score halfday time matches 1 if score tick time matches 1780 as @a if score @s resurrection matches 2 run scoreboard players add @s credit 200
execute if score day time matches 3 if score halfday time matches 1 if score tick time matches 1780 run scoreboard players set @a resurrection 0


