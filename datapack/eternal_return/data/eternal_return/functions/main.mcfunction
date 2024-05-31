##> 함수 반복문
tag @a[tag=!player] add player
## 플레이어
execute as @a[tag=player] at @s run function eternal_return:system/player/main
## 채팅 클릭
execute as @a[tag=player_id] at @s run function eternal_return:system/trigger/main

## 기물
# 부시
function eternal_return:system/bush/main
# 키오스크
function eternal_return:system/kiosk/main
# 점프패드
function eternal_return:system/jumppad/main
# 하이퍼 루프
execute as @a[tag=player_id] at @s run function eternal_return:system/hyperloop/main
# 생명의 나무
execute as @a[tag=player] at @s run function eternal_return:system/tree_of_life/main
# 맵
function eternal_return:system/map/main
# 액션바
execute as @a[tag=player] run function eternal_return:system/actionbar/main



## 맵
function eternal_return:system/map/main

#> 하이퍼루프 함수 (eternal_return:hyper_loop)
#execute if entity @a[tag=hyper_loop] as @a[tag=hyper_loop] at @s run function eternal_return:hyper_loop/main
#execute as @a[tag=!hyper_loop, tag=choosing_hyper_loop] run bossbar set minecraft:hud_5 name [{"text":""}]
#execute as @a[tag=!hyper_loop, tag=choosing_hyper_loop] run tag @s remove choosing_hyper_loop

# 플레이어 제외 엔티티 업데이트
#execute as @e[type=!player] run function eternal_return:entity/main

##> 디버그
function eternal_return:debug

## 이전 버전 데이터팩 함수 루프
function eternal_return:system/game/noarea
function eternal_return:system/game/safearea
execute as @e[tag=gametime] run function eternal_return:system/game/gametime
function eternal_return:system/game/last_area
function eternal_return:system/game/daytimer
function eternal_return:system/game/credit



# 운석
function eternal_return:system/stone/stonearea1
function eternal_return:system/stone/stonearea3



##캐릭터
function eternal_return:system/character/character
function eternal_return:system/character/happychaos
function eternal_return:system/character/jackie
function eternal_return:system/character/nickys
function eternal_return:system/character/luke
function eternal_return:system/character/mai
function eternal_return:system/character/shou
function eternal_return:system/character/charlotte
function eternal_return:system/character/aya
function eternal_return:system/character/yohan
function eternal_return:system/character/heart
function eternal_return:system/character/yuki/main

function eternal_return:system/player/rightclick
function eternal_return:system/player/damagetaken