
execute as @e[tag=meteor,tag=selected] if score @s CT1 matches ..160 run scoreboard players add @s CT1 1
execute as @e[tag=meteor] at @s if score @s[tag=selected] CT1 matches ..159 run function eternal_return:system/meteor/effect/before_impact
execute if score @e[tag=selected,tag=meteor,limit=1] CT1 matches 159 run tellraw @a [{"text":"운석이 떨어졌습니다. - ","color":"blue"},{"text":"위치 : ","color":"yellow"},{"selector":"@e[type=marker,tag=selected]","color":"yellow"}]
execute as @e[tag=meteor] at @s if score @s[tag=selected] CT1 matches 160 run function eternal_return:system/meteor/effect/impact

execute as @e[tag=meteor,tag=interaction] at @s run function eternal_return:system/meteor/meteor
execute as @e[tag=player] at @s run function eternal_return:system/meteor/player