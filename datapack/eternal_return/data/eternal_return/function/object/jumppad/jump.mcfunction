## 점프패드 구역 위치별 모션값 설정
# 상위 함수 :function eternal_return:object/tick
# 출력 : 플레이어의 모션값을 추가해서 날린다

# 소리 출력
    playsound minecraft:er.jumppad.jump master @a[distance=..1,tag=use_jumppad] ~ ~ ~ 1 1
        execute as @a[distance=..1] run attribute @s minecraft:fall_damage_multiplier base set 0
# 모션값 조절
    # 공장
        execute if entity @s[tag=factory] run tag @a[distance=..1] add jump_factory
    # 항구
        execute if entity @s[tag=harbor] run tag @a[distance=..1] add jump_harbor
    # 고급 주택가
        execute if entity @s[tag=residential_area] run tag @a[distance=..1] add jump_residential_area
    # 연못1
        execute if entity @s[tag=pondA_1] run tag @a[distance=..1] add jump_pondA_1
        execute if entity @s[tag=pondA_2] run tag @a[distance=..1] add jump_pondA_2
    # 연못2
        execute if entity @s[tag=pondB_1] run tag @a[distance=..1] add jump_pondB_1
        execute if entity @s[tag=pondB_2] run tag @a[distance=..1] add jump_pondB_2