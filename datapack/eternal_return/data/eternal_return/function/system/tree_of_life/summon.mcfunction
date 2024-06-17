# 2일차 소환
execute if score #input.life_of_tree.day NUM matches 2 run summon interaction -518 83 592 {width:1.1f,height:1.1f,Tags:["tree"]}
execute if score #input.life_of_tree.day NUM matches 2 run summon interaction -572 85 471 {width:1.1f,height:1.1f,Tags:["tree"]}
execute if score #input.life_of_tree.day NUM matches 2 run summon interaction -781 83 468 {width:1.1f,height:1.1f,Tags:["tree"]}
execute if score #input.life_of_tree.day NUM matches 2 run tellraw @a [{"text":"[0","color":"gold"},{"score":{"name":"#timer.min","objective":"CT1"}},":",{"score":{"name":"#timer.sec","objective":"CT1"}},"] ",{"text":"호텔, 묘지, 절에서 생명의 나무가 나타났습니다."}]
# 3일차 소환
execute if score #input.life_of_tree.day NUM matches 3 run summon interaction -511 83 504 {width:1.1f,height:1.1f,Tags:["tree"]}
execute if score #input.life_of_tree.day NUM matches 3 run summon interaction -717 84 428 {width:1.1f,height:1.1f,Tags:["tree"]}
execute if score #input.life_of_tree.day NUM matches 3 run tellraw @a [{"text":"[0","color":"gold"},{"score":{"name":"#timer.min","objective":"CT1"}},":",{"score":{"name":"#timer.sec","objective":"CT1"}},"] ",{"text":"숲, 개울에서 생명의 나무가 나타났습니다."}]

# 에메랄드 블럭 소환
execute as @e[tag=tree] at @s run setblock ~ ~ ~ emerald_block

# 스코어 초기화
scoreboard players reset #input.life_of_tree.day NUM