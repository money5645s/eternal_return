#execute if score @s win.tick matches 1.. run scoreboard players add @s win.tick 1

execute if score @s win.tick matches 2 run tag @s remove in_game
execute if score @s win.tick matches 5 run playsound minecraft:er.game.win master @s ~ ~ ~
execute if score @s win.tick matches 10 run tag @s remove in_restricted_area
execute if score @s win.tick matches 1.. run title @s times 0 5000 0
execute if score @s win.tick matches 5 run title @s title " "
execute if score @s win.tick matches 5 run title @s subtitle ""
execute if score @s win.tick matches 5 run title @s title [{text:"b",font:"mono_color"}]
execute if score @s win.tick matches 43 run title @s subtitle [{text:"V I C T O R Y",shadow_color:0,color:"aqua"}]
execute if score @s win.tick matches 70 run scoreboard players set msg skript 102
execute if score @s win.tick matches 80 run tag @s add goto_lobby
