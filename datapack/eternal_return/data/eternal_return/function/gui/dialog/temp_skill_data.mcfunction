data modify storage temp skill set from storage pdb:main in.character
data modify storage temp skill.character_profill set from storage pdb:main in.character_profill
execute store result storage temp skill.active_level_temp int 1 run scoreboard players get @s active_level
execute store result storage temp skill.passive_level_temp int 1 run scoreboard players get @s passive_level