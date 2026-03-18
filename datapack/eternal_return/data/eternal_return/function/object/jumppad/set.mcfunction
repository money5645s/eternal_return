## 하이퍼루프 설치 8개
# 모델링 생성
    kill @e[tag=jumppad]
    # 공장
        execute positioned -532 82 322 run function eternal_return:object/jumppad/summon {rotation:0,place:"factory"}
    # 항구
        execute positioned -429 82 354 run function eternal_return:object/jumppad/summon {rotation:90,place:"harbor"}
    # 고급 주택가
        execute positioned -386 81 478 run function eternal_return:object/jumppad/summon {rotation:130,place:"residential_area"}
    # 연못1
        execute positioned -650 82 475 run function eternal_return:object/jumppad/summon {rotation:60,place:"pondA_1"}
        execute positioned -666 79 479 run function eternal_return:object/jumppad/summon {rotation:-110,place:"pondA_2"}
    # 연못2
        execute positioned -622 82 465 run function eternal_return:object/jumppad/summon {rotation:-55,place:"pondB_1"}
        execute positioned -593 82 485 run function eternal_return:object/jumppad/summon {rotation:125,place:"pondB_2"}

    # 점프패드
        scoreboard players set @e[tag=jumppad] ct1 0
        scoreboard players set @e[tag=jumppad] ct2 0