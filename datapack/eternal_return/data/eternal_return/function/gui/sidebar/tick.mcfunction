## 스코어보드 사이드바
# 상위 함수 : function eternal_return:player/tick
# 입출력 없음

# 플레이어 스탯 업데이트 함수 호출
function pdb:get_me



# 플레이어 스탯 업데이트
    # 플레이어 스탯 텍스트 숫자 변형
        # 크래딧
        execute if score @s credit matches ..999 run scoreboard players operation #pedzero TEMP = @s credit
        execute if score @s credit matches ..999 run function eternal_return:calc/number_zero_ped3/__0/run
        execute if score @s credit matches ..999 run data modify storage pdb:main in.stats.credit set from storage temp pedzero.result
        execute if score @s credit matches 1000.. run data modify storage pdb:main in.stats.credit set value "MAX"
        # 공격력
        execute store result score #pedzero TEMP run data get storage pdb:main in.stats.atk
        function eternal_return:calc/number_zero_ped2/_0/run
        data modify storage pdb:main in.stats.atk set from storage temp pedzero.result
        # 공격 속도
        execute store result score #pedzero TEMP run data get storage pdb:main in.stats.atk_spd
        function eternal_return:calc/number_zero_ped3/__0/run
        data modify storage pdb:main in.stats.atk_spd set from storage temp pedzero.result
        # 방어력
        execute store result score #pedzero TEMP run data get storage pdb:main in.stats.armor
        function eternal_return:calc/number_zero_ped2/_0/run
        data modify storage pdb:main in.stats.armor set from storage temp pedzero.result
        # 이동 속도
        execute store result score #pedzero TEMP run data get storage pdb:main in.stats.mvs
        function eternal_return:calc/number_zero_ped3/__0/run
        data modify storage pdb:main in.stats.mvs set from storage temp pedzero.result

        # 캐릭터 유니코드 넘버
        execute store result score #pedzero TEMP run data get storage pdb:main in.character_profill
        function eternal_return:calc/unicord_zero_ped4/run
        data modify storage pdb:main in.profile_unicode set from storage temp pedzero.result

        # 쿨타임
            # Active
            execute store result score #pedzero TEMP run data get storage pdb:main in.stats.active.cool
            function eternal_return:calc/number_zero_ped2/_0/run
            execute if score @s active_cool matches 10..99 run data modify storage pdb:main in.stats.active.cool set value {"text": "", "font": "gui/character/skill/text/active_cool"}
            execute if score @s active_cool matches 0..9 run data modify storage pdb:main in.stats.active.cool set value {"text": "", "font": "gui/character/skill/text/active_cool2"}
            execute if score @s active_cool matches 0 run data modify storage pdb:main in.stats.active.cool set value {"text": "  ", "font": "gui/character/skill/text/active_cool"}
            execute if score @s active_cool matches 1..99 run data modify storage pdb:main in.stats.active.cool.text set from storage temp pedzero.result

            # Passive
            execute store result score #pedzero TEMP run data get storage pdb:main in.stats.passive.cool
            function eternal_return:calc/number_zero_ped2/_0/run
            execute if score @s passive_cool matches 10..99 run data modify storage pdb:main in.stats.passive.cool set value {"text": "", "font": "gui/character/skill/text/passive_cool"}
            execute if score @s passive_cool matches 0..9 run data modify storage pdb:main in.stats.passive.cool set value {"text": "", "font": "gui/character/skill/text/passive_cool2"}
            execute if score @s passive_cool matches 0 run data modify storage pdb:main in.stats.passive.cool set value {"text": "  ", "font": "gui/character/skill/text/passive_cool"}
            execute if score @s passive_cool matches 1..99 run data modify storage pdb:main in.stats.passive.cool.text set from storage temp pedzero.result
        
                # 스킬 아이콘 어둡게 처리
                execute if score @s active_cool matches 1..99 run data modify storage pdb:main in.stats.active.cool.image set value "1"
                execute if score @s active_cool matches 0 run data modify storage pdb:main in.stats.active.cool.image set value "0"
                execute if score @s passive_cool matches 1..99 run data modify storage pdb:main in.stats.passive.cool.image set value "1"
                execute if score @s passive_cool matches 0 run data modify storage pdb:main in.stats.passive.cool.image set value "0"
        # 레벨
        execute store result score #pedzero TEMP run data get storage pdb:main in.stats.level
        function eternal_return:calc/number_zero_ped2/_0/run
        execute if score @s level matches 10..99 run data modify storage pdb:main in.stats.level set value {"text": "", "font": "gui/character/skill/text/level"}
        execute if score @s level matches 0..9 run data modify storage pdb:main in.stats.level set value {"text": "", "font": "gui/character/skill/text/level2"}
        data modify storage pdb:main in.stats.level.text set from storage temp pedzero.result
            
        # 스킬포인트 표기
            # active
            execute if score @s skill_point matches 0 run data modify storage pdb:main in.stats.active.point_image set value "2"
            execute if score @s skill_point matches 1.. if score @s active_level matches 5 run data modify storage pdb:main in.stats.active.point_image set value "1"
            execute if score @s skill_point matches 1.. if score @s active_level matches ..4 run data modify storage pdb:main in.stats.active.point_image set value "0"
            # passive
            execute if score @s skill_point matches 0 run data modify storage pdb:main in.stats.passive.point_image set value "2"
            execute if score @s skill_point matches 1.. if score @s passive_level matches 5 run data modify storage pdb:main in.stats.passive.point_image set value "1"
            execute if score @s skill_point matches 1.. if score @s passive_level matches ..4 run data modify storage pdb:main in.stats.passive.point_image set value "0"
            
    # 메모리 정리
    scoreboard players reset #pedzero TEMP

# 사이드바 라인 상태 갱신
function eternal_return:gui/sidebar/line/state with storage pdb:main args
function pdb:save_me