# 2일차 소환
execute if score #input.life_of_tree.day NUM matches 2 run summon interaction -518 83 592 {width:1.1f,height:1.1f,Tags:["tree"]}
execute if score #input.life_of_tree.day NUM matches 2 run summon interaction -572 85 471 {width:1.1f,height:1.1f,Tags:["tree"]}
execute if score #input.life_of_tree.day NUM matches 2 run summon interaction -781 83 468 {width:1.1f,height:1.1f,Tags:["tree"]}
# 3일차 소환
execute if score #input.life_of_tree.day NUM matches 3 run summon interaction -511 83 504 {width:1.1f,height:1.1f,Tags:["tree"]}
execute if score #input.life_of_tree.day NUM matches 3 run summon interaction -716 83 429 {width:1.1f,height:1.1f,Tags:["tree"]}



# 에메랄드 블럭 소환
execute as @e[tag=tree] at @s run setblock ~ ~ ~ emerald_block

advancement revoke @a only eternal_return:tree_of_life
advancement grant @a only eternal_return:tree_of_life

# 스코어 초기화
scoreboard players reset #input.life_of_tree.day NUM