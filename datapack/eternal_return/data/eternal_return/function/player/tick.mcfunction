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
    execute at @s if entity @s[tag=player] run function eternal_return:player/stats/tick
    execute if entity @s[tag=player] run function eternal_return:gui/sidebar/tick
    execute if score @s[tag=player] select matches 1..99 run function eternal_return:gui/dialog/tick
# 플레이어 테스트 서버

# 플레이어 재접속 시
    #execute if entity @s[tag=player] run function eternal_return:gui/bossbar/leave_game

# 플레이어 고정
    #execute at @s if entity @s[tag=player] run function eternal_return:player/freeze_player



# 부시
    #execute if entity @s[tag=player] at @s run function eternal_return:player/bush/tick