execute if score @s lose.tick matches 1.. run scoreboard players add @s lose.tick 1

execute if score @s lose.tick matches 2 run tag @s remove in_game
execute if score @s lose.tick matches 10 run tag @s remove in_restricted_area
execute if score @s lose.tick matches 1.. run title @s times 0 5000 0
execute if score @s lose.tick matches 5 run title @s title " "
execute if score @s lose.tick matches 5 run title @s subtitle ""
execute if score @s lose.tick matches 5 run title @s title [{text:"b",font:"mono_color"}]
execute if score @s lose.tick matches 43 run title @s subtitle [{text:"E L I M I N A T E D",shadow_color:0,color:"#c43423"}]
execute if score @s lose.tick matches 80 run tag @s add goto_lobby
execute if score @s lose.tick matches 2 run playsound minecraft:er.game.lose master @s ~ ~ ~
