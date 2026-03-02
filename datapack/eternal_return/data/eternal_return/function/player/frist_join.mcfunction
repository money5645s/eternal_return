## 플레이어 첫 입장

    # pdb 설정
        function pdb:new_player
        function pdb:get_me
        # 데이터 받아오기
            # 캐릭터 선택창 데이터
                data modify storage pdb:main in.character_text_array set from storage config character_array
            # 팀 부여
                scoreboard players remove #team_count NUM 1
                data modify storage pdb:main in.team set from storage game team_color[0]
                data remove storage game team_color[0]

        # 추가내용 저장
        function pdb:save_me

    # 태그 부여
        tag @s add selection_character
        tag @s add player

    # 태그 삭제
#        tag @s remove on_camera_overlay
#        tag @s remove waiting
#        tag @s remove in_game
#        function eternal_return:character/remove_tag with storage pdb:main in.character
#
#    # 사이드바 제거
#        function eternal_return:gui/sidebar/remove with storage pdb:main args

    # 스코어보드 설정
        # 캐릭터 선택창 초기화
            scoreboard players set @s Page.tick 600
        # trigger 스코어 초기화
            scoreboard players set @s select 0
        # 크래딧 설정
            scoreboard players set @s credit 0
        # 스킬
            # 스킬 레벨
                scoreboard players set @s level 10
                scoreboard players set @s skill_point 0
                scoreboard players set @s skill_exp 0
                scoreboard players set @s skill_exp_image 5
            # 패시브
                scoreboard players set @s active_level 0
                scoreboard players set @s active_cool 0
            # 액티브
                scoreboard players set @s passive_level 0
                scoreboard players set @s passive_cool 0
        # kill
            scoreboard players set @s kill 0
        # death
            scoreboard players set @s death 0


    # 보스바 생성 및 표기
        function eternal_return:gui/bossbar/new with storage pdb:main args
        function eternal_return:gui/bossbar/show with storage pdb:main args

