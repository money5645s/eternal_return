##> 함수 반복문

## 인게임
function eternal_return:game/tick


## 오브젝트
    function eternal_return:object/tick
## 플레이어
    # 플레이어 설정

    execute as @a run function eternal_return:player/tick
        # [debug] 플레이어가 맞은 경우 
        #execute as @a if data entity @s {HurtTime:6s} run function eternal_return:test
    # GUI
        # 로딩바
        execute as @a if entity @s[tag=player] run function eternal_return:gui/loading/tick
        # 커스텀 스크린
        #execute as @a if entity @s[tag=player] run function eternal_return:gui/screen/tick
    
    # 맵 창 / 키오스크 / 부활 선택창
    #function eternal_return:object/tick















## 캐릭터
    #execute as @a if entity @s[tag=player] run function eternal_return:character/tick
## 게임 
    #function eternal_return:game/tick
## 맵
    # execute as @a if entity @s[tag=player] run function eternal_return:map/tick
## 기물

# 부시
#function eternal_return:sys/player/bush
# 키오스크
#function eternal_return:sys/kiosk/main
# 점프패드
#
# 하이퍼 루프
#execute \
as @a[tag=player] \
at @s run \
function eternal_return:sys/hyperloop/main
# 생명의 나무
#execute \
as @a[tag=player] \
at @s run \
function eternal_return:sys/tree_of_life/main
# 운석
#execute \
as @a[tag=player] \
at @s run \ 
function eternal_return:sys/meteor/main


# 야생동물 업데이트
#execute \
#as @e[type=!player,tag=ER] run \
#function eternal_return:entity/main/main

# 벽뚫화살 업데이트
#execute \
#as @e[type= minecraft:arrow] \
#at @s run \
#function eternal_return:entity/arrow_pen/main


##> 디버그
#function eternal_return:debug

## 이전 버전 데이터팩 함수 루프
#function eternal_return:sys/game/noarea
#function eternal_return:sys/game/safearea
#function eternal_return:sys/game/last_area
#function eternal_return:sys/game/daytimer


## CC기
#function eternal_return:sys/character/stun/main


## 낮밤 변경
#function eternal_return:sys/game/daycycle











# 삼지창을 던질때 설정
#execute as @a[tag=tri] if score @s rc matches 0 run attribute @s minecraft:movement_speed base reset
#execute as @a[tag=tri] if score @s rc matches 1.. run scoreboard players set @s rc 0