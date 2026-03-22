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
    execute if entity @s[tag=player] run function eternal_return:player/bush/tick
# 금지구역
    execute if entity @s[tag=player] run function eternal_return:player/restricted_area
# 데스
    execute if entity @s[tag=player] run function eternal_return:player/death
# 부활
    execute if entity @s[tag=player] run function eternal_return:player/resurrection/tick

# 크래딧 제거 한게치
    execute if entity @s[tag=player] if score @s credit matches 1000.. run scoreboard players set @s credit 1000
# 금구 타이머 한계치
    execute if entity @s[tag=player] if score @s bantime matches 40.. run scoreboard players set @s bantime 40
    execute if score day time matches 5 if score halfday time matches 1 if score tick time matches 1 if entity @s[tag=player] run scoreboard players add @s bantime 20

## 오브젝트 클릭
    # 하이퍼 루프
        execute if entity @s[tag=player] run function eternal_return:object/hyperloop/tick
    # CCTV
        execute if entity @s[tag=player] run function eternal_return:object/cctv/player_tick
    # 점프 패드
        execute if entity @s[tag=player] run function eternal_return:object/jumppad/player_tick
    # 키오스크
        execute if entity @s[tag=player,tag=click_kiosk] at @s run playsound minecraft:er.kiosk.open master @s ~ ~ ~ 5 1
        execute if entity @s[tag=player,tag=click_kiosk] at @s run function eternal_return:object/kiosk/open_kiosk
        # 키오스크 화면 클릭
            execute if entity @s[tag=player] run function eternal_return:object/kiosk/player
    # 운석 클릭
        execute if entity @s[tag=player] at @s run function eternal_return:object/meteor/player
    # 생명의 나무 클릭
        execute if entity @s[tag=player] at @s run function eternal_return:object/tree_of_life/player
# 승리
    execute if score game.start time matches 1 if entity @s[tag=player] run function eternal_return:player/win
# 패배
    execute if entity @s[tag=player] run function eternal_return:player/lose