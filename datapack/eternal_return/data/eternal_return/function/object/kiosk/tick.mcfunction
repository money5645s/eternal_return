
## 키오스크 함수
# 상위 함수 :function eternal_return:object/tick
# 입출력 없음

## 클릭
    # 키오스크 클릭


        execute as @e[tag=kiosk,type=armor_stand,tag=on] run data modify entity @s equipment.head.components."minecraft:custom_model_data".strings[0] set value "kiosk"
        execute as @e[tag=kiosk,type=armor_stand,tag=!on] run data modify entity @s equipment.head.components."minecraft:custom_model_data".strings[0] set value "kiosk_off"