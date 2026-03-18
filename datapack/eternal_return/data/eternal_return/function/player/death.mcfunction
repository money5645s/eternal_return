






execute if score @s death_count matches 1.. run tag @s add death.
execute if score @s death_count matches 1.. run scoreboard players set @s death_count 0


effect give @s[tag=death.,tag=resurrectable] minecraft:blindness 10000 1 true






execute if score @s[tag=death.,tag=resurrectable] resurrection matches 0 run tag @s remove resurrectable
execute if score @s[tag=death.] resurrection matches 0 run tag @s add bos.reset
execute if score @s[tag=death.] resurrection matches 0 run gamemode spectator @s
execute if score @s[tag=death.] resurrection matches 0 run tp @s -254 75 487
execute if score @s[tag=death.] resurrection matches 0 run scoreboard players set @s lose.tick 1
execute if score @s[tag=death.,tag=resurrectable] resurrection matches 1.. run scoreboard players set @s resurretion.tick 300
execute if score @s[tag=death.,tag=resurrectable] resurrection matches 1.. run scoreboard players remove @s resurrection 1




tag @s[tag=death.] add freeze
kill @s[tag=death.,tag=resurrectable]
tag @s[tag=death.] remove death.
































#execute if score @s[tag=death.,tag=!resurrectable] resurrection matches 0 run tp @s -254 75 487

#execute if score @s[tag=death.,tag=!resurrectable] resurrection matches 0 run tag @s add goto_lobby
#execute if score @s[tag=death.,tag=resurrectable] resurrection matches 0 run tag @s remove resurrectable
#kill @s[tag=death.]
