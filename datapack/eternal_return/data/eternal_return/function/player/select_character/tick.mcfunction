## 캐릭터 선택하기
    # UUID 호출
    function pdb:get_me

    # 다이얼로그 표기
    execute if score @s select matches 0 run item replace entity @s armor.feet with paper[equippable={slot:"feet",equip_sound:"ui.toast.in",camera_overlay:"font/character/bg/ui"}] 1
    execute if score @s select matches 0 run function eternal_return:gui/dialog/normal_page with storage pdb:main in
    execute if score @s select matches 0 run scoreboard players set @s select -1

    # 타이머 연산
    execute if score @s Page.tick matches 1.. run scoreboard players remove @s Page.tick 1
    execute if score @s Page.tick matches 1.. run scoreboard players operation #sec TEMP = @s Page.tick
    execute if score @s Page.tick matches 1.. run scoreboard players operation #sec TEMP /= #20 CONST

    # 로딩바 픽셀 연산
    scoreboard players operation #pixel.loading TEMP = #600 CONST
    scoreboard players operation #pixel.loading TEMP -= @s Page.tick
    scoreboard players operation #pixel.loading TEMP /= #3 CONST

    # 표기용 데이터 문자열 변환/임시저장
    scoreboard players operation #pedzero TEMP = #pixel.loading TEMP
    function eternal_return:calc/unicord_zero_ped3/run
    data modify storage temp data.text set from storage temp pedzero.result
    data modify storage temp data.UUID0 set from storage pdb:main in.UUID[0]
    # 타이머/로딩바 표기
    function eternal_return:gui/bossbar/list/character_select_loading with storage temp data

    ## 캐릭터 슬롯 클릭
    execute if score @s select matches 100..199 run function eternal_return:player/select_character/clicked_slot

    ## 선택 완료
        execute if score @s select matches 200 run function eternal_return:player/select_character/done_select

# 메모리 비우기
    scoreboard players reset #pixel.loading TEMP
    scoreboard players reset #charater_number TEMP
    #data remove storage temp data
    data remove storage temp pedzero
