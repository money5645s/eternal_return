# 생명의 나무 제거
tellraw @s [{"text":"생명의 나무가 성공적으로 제거되었습니다 ","color":"green"}]
execute as @e[tag=tree] at @s run setblock ~ ~ ~ air
kill @e[tag=tree]
scoreboard players reset #tree_of_life.summon ct1
scoreboard players reset #input.life_of_tree.day NUM