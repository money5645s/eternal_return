## 플레이어
# 입출력 없음
# 상위 함수 : function eternal_return:main
# 플레이어와 관련된 기능을 다루는 함수

# 허기수치 max
    effect give @s saturation infinite 100 true
# 플레이어 첫입장 시
    execute if entity @s[tag=!player] run function eternal_return:player/frist_join
# tirgger 스코어보드 활성화
    scoreboard players enable @s select
# 캐릭터 선택
    execute if entity @s[tag=selection_character] run function eternal_return:player/select_character/tick
# 플레이어 스탯
    execute if score @s[tag=player] select matches 1..99 run function eternal_return:gui/dialog/tick
# 플레이어 
    execute if entity @s run function eternal_return:player/kill_mob
# 레벨 경험치
    execute if entity @s[tag=player] run function eternal_return:craft/tick
# 승전보
    execute if entity @s[tag=player] run function eternal_return:player/kill_player

# 부시
    execute if entity @s[tag=player] at @s run function eternal_return:player/bush/tick
# 금지구역
    execute if entity @s[tag=player] at @s run function eternal_return:player/restricted_area
# 데스
    execute if entity @s[tag=player] at @s run function eternal_return:player/death
# 부활
    execute if entity @s[tag=player] at @s run function eternal_return:player/resurrection/tick

# 크래딧 제거 한게치
    execute if entity @s[tag=player] at @s if score @s credit matches 1000.. run scoreboard players set @s credit 1000
# 금구 타이머 한계치
    execute if score day time matches ..3 if entity @s[tag=player] if score @s bantime matches 20.. run scoreboard players set @s bantime 20
    execute if score day time matches 4.. if entity @s[tag=player] if score @s bantime matches 30.. run scoreboard players set @s bantime 30
    execute if score day time matches 5 if score halfday time matches 1 if score tick time matches 1 if entity @s[tag=player] run scoreboard players add @s bantime 20

    # 플레이어
        execute if entity @s[tag=player] at @s run function eternal_return:object/hyperloop/tick


# 승리
    execute if score game.start time matches 1 if entity @s[tag=player] at @s run function eternal_return:player/win
# 패배
    execute if entity @s[tag=player] at @s run function eternal_return:player/lose