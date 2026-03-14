## CCTV 
# 상위 함수 : function eternal_return:object/tick
# 입출력 없음

# 세 칸내 플레이어가 우클릭 시
    # 일반 모드 / 활성화 된 상태인 경우
        
        execute if entity @s[tag=!cooltime] if entity @a[tag=done.loading,distance=..2.5] run \
            function eternal_return:object/cctv/active

    # 6일차 낮 이후 인 경우
        #execute if score #timer.day NUM matches 6.. if data entity @s interaction at @s on target run \ 
        #    title @s[distance=..2.5] actionbar [{"text": "최종 안전지대 페이즈 중에는 보안콘솔을 사용할 수 없습니다."}]
        ## 쿨타임 진행 중 6일차가 되었을 때
        #    execute if score #timer.day NUM matches 6.. at @s run kill @e[type=text_display,tag=cool,distance=..5]


    # 태그 제거
        ## 쿨타임 태그
        execute at @s unless entity @n[type=text_display,tag=cool,tag=red,limit=1,distance=..5] run tag @s remove cooltime

# 모드 변경
    # 일반
        execute if entity @s[tag=on] run \
            data modify entity @s item.components."minecraft:custom_model_data".floats[0] set value 0.0f
    # 활성화됨 (쿨타임 중)
        execute if entity @s[tag=on,tag=cooltime] run \
            data modify entity @s item.components."minecraft:custom_model_data".floats[0] set value 2.0f
    # 오프라인(6일차 이후)
    execute if entity @s[tag=!on] run \
        data modify entity @s item.components."minecraft:custom_model_data".floats[0] set value 3.0f