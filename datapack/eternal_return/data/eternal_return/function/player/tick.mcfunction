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




# 승리
    execute if entity @s[tag=player] at @s run function eternal_return:player/win
# 패배
    execute if entity @s[tag=player] at @s run function eternal_return:player/lose