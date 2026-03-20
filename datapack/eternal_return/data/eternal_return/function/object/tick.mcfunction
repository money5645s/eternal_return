## 오브젝트 함수
# 상위 함수 :function eternal_return:main
# 입출력 없음
# 맵에 배치되는 오브젝트를 관리하는 tick 함수

# 키오스크
    # 인터렉션
        function eternal_return:object/kiosk/tick
# 하이퍼루프
    # 인터렉션
        #execute as @e[type=interaction,tag=click_interaction,tag=hyperloop] run function eternal_return:object/hyperloop/tick

# CCTV
    # 플레이어
        # 플레이어가 클릭했을 때
        execute as @a[tag=player] at @s run function eternal_return:object/cctv/player_tick
    # 인터렉션
        execute as @e[type=item_display,tag=cctv] at @s run function eternal_return:object/cctv/tick
    # 텍스트 디스플레이 [쿨타임]
        execute as @e[type=text_display,tag=cool] at @s run function eternal_return:object/cctv/cooltime/tick

# 점프패드
    execute as @e[tag=jumppad] at @s run function eternal_return:object/jumppad/tick
    execute as @a[tag=player] at @s run function eternal_return:object/jumppad/player_tick

# 소환진
    # 운석
        execute as @e[type=interaction,tag=meteorite,limit=8] at @s run particle reverse_portal ~ ~0.1 ~ 0.5 0 0.5 0.03 5 force
        execute as @e[type=interaction,tag=meteorite] at @s if entity @a[tag=done.loading,distance=..2] run function eternal_return:object/meteor/interaction_tick
        execute as @e[type=marker,tag=meteor,tag=selected,tag=!impacted] at @s run function eternal_return:object/meteor/tick
        execute as @e[type=item_display,tag=meteor_info] at @s run function eternal_return:object/meteor/info/rotate
        execute as @a[tag=player] at @s run function eternal_return:object/meteor/player
    # 생명의 나무
        function eternal_return:object/tree_of_life/tick
        #execute as @a[tag=player] at @s run function eternal_return:object/meteor/player
    # 상자
        function eternal_return:object/box/tick