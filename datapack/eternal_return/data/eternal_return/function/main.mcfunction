##> 함수 반복문

## 인게임
function eternal_return:game/tick

## 오브젝트
    function eternal_return:object/tick
## 플레이어
    # 플레이어 설정

    execute as @a run function eternal_return:player/tick

# 삼지창을 던질때 설정
#execute as @a[tag=tri] if score @s rc matches 0 run attribute @s minecraft:movement_speed base reset
#execute as @a[tag=tri] if score @s rc matches 1.. run scoreboard players set @s rc 0