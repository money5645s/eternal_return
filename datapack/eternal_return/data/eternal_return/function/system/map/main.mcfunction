##> 맵

## 플레이어 
execute as @a[tag=player] at @s run function eternal_return:system/map/player


## 구역 설정

function eternal_return:system/map/area/tick

# 구역별 맵 플레이어 수 저장
execute as @e[tag=zplace] run function eternal_return:system/map/count_player
