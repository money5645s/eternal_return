execute store result score #posX ER.sys run data get entity @s Pos[0] 100
execute store result score #posY ER.sys run data get entity @s Pos[1] 100
execute store result score #posZ ER.sys run data get entity @s Pos[2] 100
summon minecraft:arrow ~ ~ ~ {Tags:["this"]}
scoreboard players operation #vecX ER.sys = #posX ER.sys
scoreboard players operation #vecX ER.sys += @s ER.motionX
scoreboard players operation #vecY ER.sys = #posY ER.sys
scoreboard players operation #vecY ER.sys += @s ER.motionY
scoreboard players operation #vecZ ER.sys = #posZ ER.sys
scoreboard players operation #vecZ ER.sys += @s ER.motionZ
scoreboard players operation #motionX ER.sys = @s ER.motionX
scoreboard players operation #motionY ER.sys = @s ER.motionY
scoreboard players operation #motionZ ER.sys = @s ER.motionZ
data modify entity @e[tag= this, limit= 1] Owner set from entity @s Owner
execute as @e[tag= this, limit= 1] run function eternal_return:entity/arrow_pen/arrow_pen/arrow_pen/0
kill @s
