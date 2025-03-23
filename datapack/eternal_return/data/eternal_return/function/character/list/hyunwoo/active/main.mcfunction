execute if entity @s[tag=Qskill,tag=!active_cool] at @s run function eternal_return:character/list/hyunwoo/active/active

function eternal_return:character/list/hyunwoo/active/skill


## 쿨타임

# LV 1
execute if entity @s[tag=Qskill,tag=!active_cool,scores={active_level=1}] run scoreboard players set @s active_cool 30

# LV 2
execute if entity @s[tag=Qskill,tag=!active_cool,scores={active_level=2}] run scoreboard players set @s active_cool 26

# LV 3
execute if entity @s[tag=Qskill,tag=!active_cool,scores={active_level=3}] run scoreboard players set @s active_cool 22

# LV 4
execute if entity @s[tag=Qskill,tag=!active_cool,scores={active_level=4}] run scoreboard players set @s active_cool 18

# LV 5
execute if entity @s[tag=Qskill,tag=!active_cool,scores={active_level=5}] run scoreboard players set @s active_cool 14