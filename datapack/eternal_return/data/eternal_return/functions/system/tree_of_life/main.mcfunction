## 생명의 나무

# 범위 안에서 우클릭 할때
execute as @e[tag=tree,distance=..3] on target run tag @s add get_tree
# 범위를 벗어날 때
execute as @e[tag=tree] at @s if entity @a[distance=3..10] run data remove entity @s interaction
execute as @e[tag=tree] at @s run tag @a[distance=3..10] remove get_tree

# 스코어 추가
scoreboard players add @s[tag=get_tree,scores={CT2=..3}] CT2 1
scoreboard players set @s[tag=!get_tree] CT2 0

# 채집 도중 취소
execute if score @s[tag=get_tree,tag=!charge2] CT2 matches 2 run function eternal_return:system/actionbar/loading/set {sec:70,text:'[{"translate":"space.-105"},{"font":"actionbar/up","text":"생명의 나무 채집 중","color":"white"},{"translate":"space.40"}]'}
execute if score @s[tag=get_tree,tag=charge2] CT2 matches 2 run tag @s remove pause_loading
execute if entity @e[tag=tree,distance=3..10] run tag @s add cancel_loading
execute if entity @e[tag=tree,distance=10..15] run tag @s add clear_loading

# 생명의 나무 채집 완료
give @s[tag=get_tree,tag=charge2,tag=done_loading] oak_sapling[item_name='{"bold":true,"color":"green","text":"생명의 나무"}'] 1
execute if entity @s[tag=get_tree,tag=charge2,tag=done_loading] as @e[tag=tree,distance=..3] at @s run setblock ~ ~ ~ air
execute if entity @s[tag=get_tree,tag=charge2,tag=done_loading] run kill @e[tag=tree,distance=..3]
tag @s[tag=get_tree,tag=charge2,tag=done_loading] remove done_loading
tag @s[tag=charge2,tag=done_loading] remove done_loading

# 생명의 나무 파티클
execute as @e[tag=tree] at @s run particle minecraft:happy_villager ~ ~ ~ 0.4 0.4 0.4 1 5