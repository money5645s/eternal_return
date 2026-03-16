## 생명의 나무
# 각 객체별 함수 실행
execute as @e[tag=tree] at @s run particle minecraft:happy_villager ~ ~0.5 ~ 0.4 0.4 0.4 1 2 force
execute as @e[tag=tree] at @s if entity @a[distance=..3,tag=done.loading] run function eternal_return:object/tree_of_life/tree
execute as @a[tag=player] at @s run function eternal_return:object/tree_of_life/player

## 소환 스코어보드
execute if score #tree_of_life.summon ct1 matches 1..600 run scoreboard players add #tree_of_life.summon ct1 1
execute if score #tree_of_life.summon ct1 matches 599 run function eternal_return:object/tree_of_life/summon
execute if score #tree_of_life.summon ct1 matches 600.. run scoreboard players reset #tree_of_life.summon ct1