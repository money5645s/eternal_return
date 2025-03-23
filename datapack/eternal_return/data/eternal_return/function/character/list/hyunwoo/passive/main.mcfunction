execute if score @s[tag=!hyunwoo_passive_skill] dmggive matches 0.. run scoreboard players add @s hyunwoo_passive_skill 1

execute if score @s hyunwoo_passive_skill matches 5.. run tag @s add hyunwoo_passive_skill

execute if entity @s[tag=hyunwoo_passive_skill] at @s run particle happy_villager ~ ~1 ~ 0.3 0.3 0.3 0.05 10 force
execute if entity @s[tag=hyunwoo_passive_skill] run tag @s add hyunwoo_passive_tick
scoreboard players add @s[tag=hyunwoo_passive_tick] hyunwoo_passive_tick 1

execute store result score @s max_health run attribute @s minecraft:max_health get

execute if score @s[tag=hyunwoo_passive_skill,scores={passive_level=1}] dmggive matches 0.. run effect give @s instant_health 1 0 true
execute unless score @s[tag=hyunwoo_passive_tick,scores={passive_level=1,hyunwoo_passive_tick=2}] health = @s max_health run damage @s 3 minecraft:out_of_world
execute if score @s[tag=hyunwoo_passive_skill,scores={passive_level=2}] dmggive matches 0.. run effect give @s instant_health 1 0 true
execute unless score @s[tag=hyunwoo_passive_tick,scores={passive_level=2,hyunwoo_passive_tick=2}] health = @s max_health run damage @s 2 minecraft:out_of_world
execute if score @s[tag=hyunwoo_passive_skill,scores={passive_level=3}] dmggive matches 0.. run effect give @s instant_health 1 0 true
execute unless score @s[tag=hyunwoo_passive_tick,scores={passive_level=3,hyunwoo_passive_tick=2}] health = @s max_health run damage @s 1 minecraft:out_of_world
execute if score @s[tag=hyunwoo_passive_skill,scores={passive_level=4}] dmggive matches 0.. run effect give @s instant_health 1 0 true
execute if score @s[tag=hyunwoo_passive_skill,scores={passive_level=5}] dmggive matches 0.. run effect give @s instant_health 1 1 true
execute unless score @s[tag=hyunwoo_passive_tick,scores={passive_level=5,hyunwoo_passive_tick=2}] health = @s max_health run damage @s 3 minecraft:out_of_world

execute if entity @s[scores={hyunwoo_passive_tick=2..}] run tag @s remove hyunwoo_passive_tick
execute if entity @s[scores={hyunwoo_passive_tick=2..}] run scoreboard players reset @s hyunwoo_passive_tick
execute if score @s[tag=hyunwoo_passive_skill] dmggive matches 0.. run scoreboard players reset @s hyunwoo_passive_skill
execute if score @s[tag=hyunwoo_passive_skill] dmggive matches 0.. run tag @s remove hyunwoo_passive_skill