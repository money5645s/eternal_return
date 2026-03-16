# 파티클
particle minecraft:happy_villager ~ ~0.5 ~ 0.4 0.4 0.4 1 2

## 주변에 로딩이 끝난 플레이어가 있을 때
setblock ~ ~ ~ air
kill @s
give @a[distance=..11,tag=done.loading] prismarine_shard[lore=[{"color":"#993bda","italic":false,"text":"영웅"},{"color":"gray","italic":false,"text":"재료"}],max_stack_size=3,tooltip_style="tree_of_life"] 1
tag @a[distance=..11,tag=done.loading] remove done.loading
tag @a[distance=..11,tag=!done.loading,tag=colleting_tree] add stopload
tag @a[distance=..11,tag=colleting_tree] remove colleting_tree
