execute as @a[tag=player] at @s run particle minecraft:trial_spawner_detection_ominous ~ ~0.5 ~ 0.5 0.6 0.5 0 40 force
execute as @a[tag=player] at @s run playsound minecraft:er.announce_game_start master @s ~ ~ ~ 0.5
tag @a remove freeze
tag @a remove bos.start
gamemode adventure @a
scoreboard players set @a credit 0
scoreboard players set @a level 1
scoreboard players set @a active_level 0
scoreboard players set @a passive_level 0
scoreboard players set @a exp 0
scoreboard players set @a skill_point 1
scoreboard players set @a bantime 30
scoreboard players set day time 1
scoreboard players set halfday time 0
scoreboard players set tick time 2400
function eternal_return:map/set






# 항공 보급
    function eternal_return:object/box/set_box_marker
# 생명의 나무 제거
    function eternal_return:object/tree_of_life/remove
    scoreboard players reset #tree_of_life.summon ct1
# 운석 제거
    function eternal_return:object/meteor/remove
    function eternal_return:object/meteor/summon


scoreboard players set @e[tag=jumppad] ct1 0
scoreboard players set @e[tag=jumppad] ct2 0


execute as @a[tag=player] run function eternal_return:game/event/give_item
scoreboard players set game.start time 1
time set day
worldborder set 10000 1
recipe give @a *
gamerule pvp true
gamerule fall_damage true