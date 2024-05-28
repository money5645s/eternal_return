function eternal_return:system/kiosk/main
function eternal_return:system/bush/main
function eternal_return:system/player/main
function eternal_return:system/jumppad/main
function eternal_return:system/hyperloop/main


# 플레이어 제외 엔티티 업데이트
execute as @e[type=!player] run function eternal_return:entity/main

#> 디버그
function eternal_return:debug


## 이전 맵 함수 루프
function eternal_return:system/game/noarea
function eternal_return:system/game/safearea
function eternal_return:system/game/gametime
function eternal_return:system/game/last_area
function eternal_return:system/game/daytimer
function eternal_return:system/game/credit

# 생명의 나무
function eternal_return:system/tree_of_life/tree
function eternal_return:system/tree_of_life/cemetery_tree
function eternal_return:system/tree_of_life/forest_tree
function eternal_return:system/tree_of_life/hotel_tree
function eternal_return:system/tree_of_life/stream_tree
function eternal_return:system/tree_of_life/temple_tree

# 운석
function eternal_return:system/stone/stonearea
function eternal_return:system/stone/stonearea1
function eternal_return:system/stone/stonearea2
function eternal_return:system/stone/stonearea3

# 맵
function eternal_return:system/map/school
function eternal_return:system/map/archery_range
function eternal_return:system/map/gas_station
function eternal_return:system/map/alley
function eternal_return:system/map/police_station
function eternal_return:system/map/fire_station
function eternal_return:system/map/temple
function eternal_return:system/map/stream
function eternal_return:system/map/pond
function eternal_return:system/map/hospital
function eternal_return:system/map/cemetery
function eternal_return:system/map/chapel
function eternal_return:system/map/factory
function eternal_return:system/map/dock
function eternal_return:system/map/warehouse
function eternal_return:system/map/uptown
function eternal_return:system/map/beach
function eternal_return:system/map/forest
function eternal_return:system/map/hotel
function eternal_return:system/map/research_center

#캐릭터
function eternal_return:system/character/character
function eternal_return:system/character/happychaos
function eternal_return:system/character/jackie
function eternal_return:system/character/nickys
function eternal_return:system/character/luke
function eternal_return:system/character/mai
function eternal_return:system/character/shou
function eternal_return:system/character/charlotte
function eternal_return:system/character/aya

function eternal_return:system/player/rightclick
function eternal_return:system/player/damagetaken