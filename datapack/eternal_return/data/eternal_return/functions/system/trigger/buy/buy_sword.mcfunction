execute as @s[scores={credit=30..}] run give @s iron_sword 1
execute as @s[scores={credit=30..}] run xp add @s -30 levels
execute as @s[scores={credit=..30}] run tellraw @s ["",{"text":"크레딧이 부족합니다.","color":"red"}]