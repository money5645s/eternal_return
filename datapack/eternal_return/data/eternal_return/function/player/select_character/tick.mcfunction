## 캐릭터 선택하기
    # UUID 호출
    function pdb:get_me

    execute if score @s[tag=!selected_character] Page.tick matches 20 if score @s Page.sec matches 0 run scoreboard players set @s select 100
    execute if score @s[tag=!selected_character] Page.tick matches 20 if score @s Page.sec matches 0 run scoreboard players set @s select 200
    # 다이얼로그 표기
    execute if score @s select matches 0 run item replace entity @s armor.feet with paper[equippable={slot:"feet",equip_sound:"ui.toast.in",camera_overlay:"font/character/bg/ui"}] 1
    execute if score @s select matches 0 run function eternal_return:gui/dialog/normal_page with storage pdb:main in
    execute if score @s select matches 0 run scoreboard players set @s select -1

    # 타이머 연산
    execute if score @s[tag=!selected_character] Page.tick matches ..19 if score @s Page.sec matches 1.. run scoreboard players add @s Page.tick 1
    execute if score @s[tag=!selected_character] Page.tick matches 20 if score @s Page.sec matches 1.. run scoreboard players remove @s Page.sec 1
    execute if score @s[tag=!selected_character] Page.tick matches 20 if score @s Page.sec matches 1.. run scoreboard players set @s Page.tick 0


    ## 캐릭터 슬롯 클릭
    execute if score @s select matches 100..199 run function eternal_return:player/select_character/clicked_slot

    ## 선택 완료
        execute if score @s select matches 200 run function eternal_return:player/select_character/done_select

# 메모리 비우기
    scoreboard players reset #pixel.loading TEMP
    scoreboard players reset #charater_number TEMP
    #data remove storage temp data
    data remove storage temp pedzero
