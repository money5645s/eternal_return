
# 보어를 소환할 마커를 제거하는 함수입니다.
#> function eternal_return:entity/animal_spawn/point/boar

execute at @s run kill @e[type=minecraft:marker,tag=ER.spawn.animal,limit=1,sort=nearest]




