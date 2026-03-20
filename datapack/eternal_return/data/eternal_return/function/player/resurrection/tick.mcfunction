execute if score @s resurretion.tick matches 1.. run scoreboard players remove @s resurretion.tick 1
execute if score @s resurretion.tick matches 0 run dialog show @s eternal_return:resurrection
execute if score @s resurretion.tick matches 1 run scoreboard players set @s resurretion.sec 300

execute if score @s resurretion.sec matches 1.. run scoreboard players remove @s resurretion.sec 1
execute if score @s resurretion.sec matches 0 store result score @s select run random value 400..419

execute if score @s select matches 400..419 if score @s resurretion.tick matches 0 run scoreboard players reset @s resurretion.tick
execute if score @s select matches 400..419 run function eternal_return:player/resurrection/warp


