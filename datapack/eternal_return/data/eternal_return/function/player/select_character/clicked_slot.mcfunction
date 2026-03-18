## 캐릭터 슬롯 클릭
    # 버튼 클릭음
    #playsound minecraft:ui.button.click ui @s[tag=!slot_click] ~ ~ ~ 5 1
    # 랜덤 클릭시(베타테스트 전용)
    execute if score @s select matches 100 store result score #random_sel TEMP run random value 1..8
    execute if score @s select matches 100 if score #random_sel TEMP matches 1 run scoreboard players set @s select 101
    execute if score @s select matches 100 if score #random_sel TEMP matches 2 run scoreboard players set @s select 111
    execute if score @s select matches 100 if score #random_sel TEMP matches 3 run scoreboard players set @s select 112
    execute if score @s select matches 100 if score #random_sel TEMP matches 4 run scoreboard players set @s select 114
    execute if score @s select matches 100 if score #random_sel TEMP matches 5 run scoreboard players set @s select 129
    execute if score @s select matches 100 if score #random_sel TEMP matches 6 run scoreboard players set @s select 131
    execute if score @s select matches 100 if score #random_sel TEMP matches 7 run scoreboard players set @s select 132
    execute if score @s select matches 100 if score #random_sel TEMP matches 8 run scoreboard players set @s select 133
    scoreboard players reset #random_sel TEMP
    # (정식버전)
    #execute if score @s select matches 100 if score #random_sel TEMP matches 7 run random value 101..133
    
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
    scoreboard players operation #debug TEMP = #charater_number TEMP
    # 데이터 변경
    execute store result storage pdb:main in.character_profill int 1 run scoreboard players get #charater_number TEMP
    # 다이얼로그 표기
    function eternal_return:gui/dialog/fianl_page with storage pdb:main in
    #scoreboard players set @s select -1
    # 메모리 비우기
    scoreboard players reset #charater_number TEMP