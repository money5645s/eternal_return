## 플레이어 첫 입장

    # pdb 설정
        function pdb:new_player
        function pdb:get_me
        # 데이터 받아오기
            # 캐릭터 선택창 데이터
                data modify storage pdb:main in.character_text_array set from storage config character_array
        # 추가내용 저장
        function pdb:save_me
    
    # 능력치 조정
        attribute @s minecraft:waypoint_transmit_range base set 40
        attribute @s minecraft:waypoint_receive_range base set 0
        attribute @s fall_damage_multiplier base set 0
    # tp
        tp @s -254 75 487
    # 게임모드
        gamemode spectator @s
    # 태그 부여
        tag @s add resurrectable
        tag @s add selection_character
        tag @s add player
    # 타이틀 시간설정
        title @s times 0 3s 10t
    # 스코어보드 설정
        # 캐릭터 선택창 초기화
            scoreboard players set @s Page.tick 0
            scoreboard players set @s Page.sec 30
        # trigger 스코어 초기화
            scoreboard players set @s select 0
        # 크래딧 설정
            scoreboard players set @s credit 0
        # 스킬
            # 스킬 레벨
                scoreboard players set @s level 1
                scoreboard players set @s skill_point 0
                scoreboard players set @s exp 0
            # 패시브
                scoreboard players set @s active_level 0
                scoreboard players set @s ACD 0
            # 액티브
                scoreboard players set @s passive_level 0
                scoreboard players set @s PCD 0
        # 금구 남은시간
            scoreboard players set @s bantime 15
            scoreboard players set @s bantime.tick 0
        # CCTV 시간
            scoreboard players set @s cctv_tick 0
        # 승리 틱
            scoreboard players set @s win.tick 0
        # 패배 틱
            scoreboard players set @s lose.tick 0
        # 부활 카운트
            scoreboard players set @s resurrection 2
        # K/D
            scoreboard players set @s kill 0
            scoreboard players set @s kill_player 0
            scoreboard players set @s death 0
            scoreboard players set @s death_count 0