# 날짜 입력 받기
$scoreboard players set #input.life_of_tree.day NUM $(day)

# 일차 별 소환

# 2일차 소환
execute if score #input.life_of_tree.day NUM matches 2 run summon interaction -518 83 592 {width:1.1f,height:1.1f,Tags:["tree"]}
execute if score #input.life_of_tree.day NUM matches 2 run summon interaction -572 85 471 {width:1.1f,height:1.1f,Tags:["tree"]}
execute if score #input.life_of_tree.day NUM matches 2 run summon interaction -781 83 468 {width:1.1f,height:1.1f,Tags:["tree"]}
execute if score #input.life_of_tree.day NUM matches 2 run tellraw @a [{"text":"생명의 나무가 생성되었습니다. - ","color":"green"},{"text":"위치 : 호텔","color":"yellow"},{"text":", ","color":"gray"},{"text":"묘지","color":"yellow"},{"text":", ","color":"gray"},{"text":"절","color":"yellow"}]
# 3일차 소환
execute if score #input.life_of_tree.day NUM matches 3 run summon interaction -511 83 504 {width:1.1f,height:1.1f,Tags:["tree"]}
execute if score #input.life_of_tree.day NUM matches 3 run summon interaction -717 84 428 {width:1.1f,height:1.1f,Tags:["tree"]}
execute if score #input.life_of_tree.day NUM matches 3 run tellraw @a [{"text":"생명의 나무가 생성되었습니다. - ","color":"green"},{"text":"위치 : 숲","color":"yellow"},{"text":", ","color":"gray"},{"text":"개울","color":"yellow"}]

# 에메랄드 블럭 소환
execute as @e[tag=tree] at @s run setblock ~ ~ ~ emerald_block

# 에러 메시지
$execute unless score #input.life_of_tree.day NUM matches 2..3 run return run tellraw @s [{"text":"$(day)일차에는 생명의 나무를 소환할 수 없습니다","color":"red"}]

# 스코어 초기화
scoreboard players reset #input.life_of_tree.day NUM