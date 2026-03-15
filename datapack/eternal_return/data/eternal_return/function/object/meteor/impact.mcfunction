## 운석 떨어짐
playsound minecraft:block.fire.ambient master @a[distance=..5] ~ ~ ~ 4 1
particle minecraft:block{block_state:"minecraft:stone"} ~ ~ ~ 1.5 0 1.5 2 50 force
particle minecraft:campfire_cosy_smoke ~ ~0.2 ~ 0.4 0 0.4 0.03 50 force
particle minecraft:lava ~ ~ ~ 0.2 0 0.2 0.2 10 force
particle minecraft:large_smoke ~ ~ ~ 0 0 0 0.2 50 force
particle minecraft:flame ~ ~ ~ 0.2 0 0.2 0.2 100 force
tag @s add impacted
scoreboard players reset @s ct1
kill @e[type=item_display,tag=meteor_info,distance=..5]
setblock ~ ~ ~ minecraft:sculk
summon interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["meteorite"]}
kill @s