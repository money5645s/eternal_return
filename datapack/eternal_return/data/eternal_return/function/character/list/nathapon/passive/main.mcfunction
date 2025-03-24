execute if score @s[tag=!passive_cool] dmggive matches 0.. run tag @s add nathapon_passive_skill
execute if entity @s[tag=nathapon_passive_skill] at @s run function eternal_return:character/list/nathapon/passive/skill

execute if score #timer.halfday NUM matches 2 if score #timer.day NUM matches 2.. run effect give @s night_vision infinite 3 true
execute if score #timer.halfday NUM matches 1 if score #timer.day NUM matches 2.. run effect clear @s night_vision
execute if score #timer.halfday NUM matches 3 if score #timer.day NUM matches 2.. run effect clear @s night_vision