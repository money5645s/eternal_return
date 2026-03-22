execute if score @s[tag=in_restricted_area] bantime matches 1.. if score @s bantime.tick matches 1 run title @s times 1 1 3
execute if score @s[tag=in_restricted_area] bantime matches 1.. if score @s bantime.tick matches ..19 run scoreboard players add @s bantime.tick 1
execute if score @s[tag=in_restricted_area] bantime matches 1.. if score @s bantime.tick matches 2 run title @s title {"text":"r","font":"screen_effect","shadow_color":0}
execute if score @s[tag=in_restricted_area] bantime matches 2.. if score @s bantime.tick matches 2 run playsound minecraft:er.restricted_area.alarm_tick master @s ~ ~ ~
execute if score @s[tag=in_restricted_area] bantime matches 1.. if score @s bantime.tick matches 20 run scoreboard players remove @s bantime 1
execute if score @s[tag=in_restricted_area] bantime matches 1 if score @s bantime.tick matches 19 run playsound minecraft:er.restricted_area.dead master @s ~ ~ ~
execute if score @s[tag=in_restricted_area] bantime matches 1 if score @s bantime.tick matches 19 run tag @s add death.
execute if score @s[tag=in_restricted_area] bantime matches 1.. if score @s bantime.tick matches 20 run scoreboard players set @s bantime.tick 0

scoreboard players set @s[tag=!in_restricted_area] bantime.tick 0
