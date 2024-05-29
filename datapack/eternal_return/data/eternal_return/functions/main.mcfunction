##> 함수 반복문

## 플레이어
function eternal_return:system/player/main
# 채팅 클릭
execute as @a[tag=player_id] at @s run function eternal_return:system/trigger/main

## 기물
# 부시
function eternal_return:system/bush/main
# 키오스크
function eternal_return:system/kiosk/main
# 점프패드
function eternal_return:system/jumppad/main
# 하이퍼 루프
function eternal_return:system/hyperloop/main


## 맵
function eternal_return:system/map/main



# 플레이어 제외 엔티티 업데이트
#execute as @e[type=!player] run function eternal_return:entity/main

#> 디버그
function eternal_return:debug

### 이전 버전 데이터팩 함수 루프
#function eternal_return:system/game/noarea
#function eternal_return:system/game/safearea
#function eternal_return:system/game/gametime
#function eternal_return:system/game/last_area
#function eternal_return:system/game/daytimer
#function eternal_return:system/game/credit
#
## 생명의 나무
#function eternal_return:system/tree_of_life/tree
#function eternal_return:system/tree_of_life/cemetery_tree
#function eternal_return:system/tree_of_life/forest_tree
#function eternal_return:system/tree_of_life/hotel_tree
#function eternal_return:system/tree_of_life/stream_tree
#function eternal_return:system/tree_of_life/temple_tree
#
## 운석
#function eternal_return:system/stone/stonearea
#function eternal_return:system/stone/stonearea1
#function eternal_return:system/stone/stonearea2
#function eternal_return:system/stone/stonearea3
#
#
#
##캐릭터
#function eternal_return:system/character/character
#function eternal_return:system/character/happychaos
#function eternal_return:system/character/jackie
#function eternal_return:system/character/nickys
#function eternal_return:system/character/luke
#function eternal_return:system/character/mai
#function eternal_return:system/character/shou
#function eternal_return:system/character/charlotte
#function eternal_return:system/character/aya
#
#function eternal_return:system/player/rightclick
#function eternal_return:system/player/damagetaken