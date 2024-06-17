## 생명의 나무
# 각 객체별 함수 실행
execute as @e[tag=tree] at @s run function eternal_return:system/tree_of_life/tree
execute as @a[tag=player] at @s run function eternal_return:system/tree_of_life/player

## 소환 스코어보드
execute if score #tree_of_life.summon CT1 matches 1..1200 run scoreboard players add #tree_of_life.summon CT1 1
#execute if score #tree_of_life.summon CT1 matches 1190 run function eternal_return:system/advencement/empty
execute if score #tree_of_life.summon CT1 matches 1199 run advancement revoke @a only eternal_return:tree_of_life
execute if score #tree_of_life.summon CT1 matches 1199 run advancement grant @a only eternal_return:tree_of_life
execute if score #tree_of_life.summon CT1 matches 1199 run function eternal_return:system/tree_of_life/summon
execute if score #tree_of_life.summon CT1 matches 1200.. run scoreboard players reset #tree_of_life.summon CT1