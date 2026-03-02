## 캐릭터 슬롯 클릭
    # 랜덤 클릭시
    execute if score @s select matches 100 store result score @s select run random roll 101..131

    # 스코어보드 임시 저장
    scoreboard players operation @s character = @s select
    scoreboard players operation @s character -= #100 CONST

    # 슬롯 변경
    execute store result storage temp data.num int 1 run scoreboard players get @s select
    function eternal_return:player/select_character/change_slot_image with storage temp data

    # 캐릭터 번호 변환/임시저장
    scoreboard players operation #charater_number TEMP = @s select
    scoreboard players operation #charater_number TEMP -= #100 CONST
    scoreboard players operation #charater_number TEMP += #1000 CONST
    # 데이터 변경
    execute store result storage pdb:main in.character_profill int 1 run scoreboard players get #charater_number TEMP
    # 다이얼로그 표기
    function eternal_return:gui/dialog/fianl_page with storage pdb:main in
    scoreboard players set @s select -1
    # 메모리 비우기
    scoreboard players reset #charater_number TEMP