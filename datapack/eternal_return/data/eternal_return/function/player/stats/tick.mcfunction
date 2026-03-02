## 플레이어의 스탯
# 상위 함수 : function eternal_return:player/tick
# 입력 : @s pdb:main
# 플레이어의 스탯을 저장하는 함수


# 플레이어 정보 불러오기
function pdb:get_me

# 능력치 가져오기
    # 공격력
    execute store result storage pdb:main in.stats.atk int 1 run attribute @s attack_damage get 1
    # 공격속도
        execute store result score #atk_spd TEMP run attribute @s attack_speed get 100
        execute if score #atk_spd TEMP matches ..399 run scoreboard players add #atk_spd TEMP 1
        execute store result storage pdb:main in.stats.atk_spd int 1 run scoreboard players get #atk_spd TEMP
        #메모리 정리
        scoreboard players reset #atk_spd TEMP
    # 방어력
        execute store result storage pdb:main in.stats.armor int 1 run attribute @s armor get 1
    # 이동속도
        execute store result score #mvs TEMP run attribute @s movement_speed get 1000
        execute store result storage pdb:main in.stats.mvs int 1 run scoreboard players get #mvs TEMP
        #메모리 정리
        scoreboard players reset #mvs TEMP
    # 레벨
        execute store result storage pdb:main in.stats.level int 1 run scoreboard players get @s level
    # 경험치
        execute store result storage pdb:main in.stats.exp int 1 run scoreboard players get @s skill_exp
    # 크래딧
        execute store result storage pdb:main in.stats.credit int 1 run scoreboard players get @s credit
    # 액티브 스킬
        execute store result storage pdb:main in.stats.active.cool int 1 run scoreboard players get @s active_cool
        execute store result storage pdb:main in.stats.active.level int 1 run scoreboard players get @s active_level
    # 패시브 스킬
        execute store result storage pdb:main in.stats.passive.cool int 1 run scoreboard players get @s passive_cool
        execute store result storage pdb:main in.stats.passive.level int 1 run scoreboard players get @s passive_level

# 쿨타임 연산
    function eternal_return:player/stats/cooltime

# 플레이어 데이터 저장
function pdb:save_me