
## 키오스크 함수
# 상위 함수 :function eternal_return:object/tick
# 입출력 없음

## 클릭
    # 키오스크 클릭
        execute as @a[tag=click_kiosk] at @s run playsound minecraft:er.kiosk.open master @s ~ ~ ~ 5 1
        execute as @a[tag=click_kiosk] at @s run function eternal_return:object/kiosk/open_kiosk
    # 키오스크 화면 클릭
        execute as @a[tag=player] run function eternal_return:object/kiosk/player

        execute as @e[tag=kiosk,type=armor_stand,tag=on] run data modify entity @s equipment.head.components."minecraft:custom_model_data".strings[0] set value "kiosk"
        execute as @e[tag=kiosk,type=armor_stand,tag=!on] run data modify entity @s equipment.head.components."minecraft:custom_model_data".strings[0] set value "kiosk_off"