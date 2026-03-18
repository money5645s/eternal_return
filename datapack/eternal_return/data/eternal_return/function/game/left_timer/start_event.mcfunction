
execute as @a[tag=player] at @s run playsound minecraft:er.announce_game_start master @s ~ ~ ~ 0.5
tag @a remove freeze
tag @a remove bos.start
gamemode adventure @a
scoreboard players set day time 1
scoreboard players set halfday time 0
scoreboard players set tick time 1800
function eternal_return:map/set
execute as @a[tag=player] run function eternal_return:game/event/give_item
scoreboard players set game.start time 1
time set day
worldborder set 10000 1