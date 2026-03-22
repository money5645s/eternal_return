## 오브젝트 함수
# 상위 함수 :function eternal_return:main
# 입출력 없음
# 맵에 배치되는 오브젝트를 관리하는 tick 함수

# 키오스크
    # 인터렉션
        execute as @e[tag=kiosk,type=armor_stand,tag=on] run data modify entity @s equipment.head.components."minecraft:custom_model_data".strings[0] set value "kiosk"
        execute as @e[tag=kiosk,type=armor_stand,tag=!on] run data modify entity @s equipment.head.components."minecraft:custom_model_data".strings[0] set value "kiosk_off"
# 하이퍼루프
    # 인터렉션
        execute as @e[type=interaction,tag=click_interaction,tag=hyperloop] run function eternal_return:object/hyperloop/tick

# CCTV
    # 인터렉션
        execute as @e[type=item_display,tag=cctv] at @s run function eternal_return:object/cctv/tick
    # 텍스트 디스플레이 [쿨타임]
        execute as @e[type=text_display,tag=cool] at @s run function eternal_return:object/cctv/cooltime/tick
# 점프패드
    execute as @e[tag=jumppad] at @s run function eternal_return:object/jumppad/tick


# 소환진
    # 운석
        execute as @e[type=interaction,tag=meteorite,limit=8] at @s run particle reverse_portal ~ ~0.1 ~ 0.5 0 0.5 0.03 5 force
        execute as @e[type=interaction,tag=meteorite] at @s if entity @a[tag=done.loading,distance=..2] run function eternal_return:object/meteor/interaction_tick
        execute as @e[type=marker,tag=meteor,tag=selected,tag=!impacted] at @s run function eternal_return:object/meteor/tick
        execute as @e[type=item_display,tag=meteor_info] at @s run function eternal_return:object/meteor/info/rotate
        
    # 생명의 나무
        function eternal_return:object/tree_of_life/tick
    # 상자
        function eternal_return:object/box/tick