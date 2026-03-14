## 다이로그 버튼
function pdb:get_me

# 하이퍼루프 1~19
    # 지역 이동

# 키오스크 20~25
    # 아이템 구매

# 부활 26~45
    # 지역 선택

# 퀵액션 46~53
    
    # 스킬 레벨업
        # 액티브 스킬 레벨업 버튼
        execute if score @s select matches 51 run function eternal_return:gui/dialog/skill_levelup/active
        # 패시브 스킬 레벨업 버튼
        execute if score @s select matches 52 run function eternal_return:gui/dialog/skill_levelup/passive
    # 원격 드론 버튼
    execute if score @s select matches 50..53 run scoreboard players set @s select -1
    # 메모리 제거
    data remove storage temp skill