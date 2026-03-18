## 캐릭터 선택 완료

    # 다이얼로그 화면 끄기
        dialog clear @s

    # 배경 제거
        clear @s paper
    # 대기실로 이동
        tp @s -277 63 448 90 0
    # 게임모드 변경
        gamemode adventure @s
    # 타이틀 시간 변경
        title @s times 0 20 10
        # 검은화면 표기
        title @s title {text:"b",font:"mono_color"}
    # UI 숨기기
        function eternal_return:gui/bossbar/clear with storage pdb:main args
        function eternal_return:gui/bossbar/hide with storage pdb:main args
    # 넘버 가져오기
        function eternal_return:player/select_character/get_number
    # 스코어보드 초기화
        scoreboard players set @s select -1
    # 캐릭터 배열 정보 삭제
        data remove storage pdb:main in.character_text_array
    # 캐릭터 번호 저장
        execute store result storage pdb:main in.character.no int 1 run scoreboard players get @s character
        # 캐릭터 번호 변환
        scoreboard players operation #charater_number TEMP = @s character
        scoreboard players operation #charater_number TEMP += #1000 CONST
        # 데이터 저장
        execute store result storage pdb:main in.character_profill int 1 run scoreboard players get #charater_number TEMP
        # 메모리 비우기
        scoreboard players reset #charater_number TEMP
    # 캐릭터 정보 불러오기
        function eternal_return:player/select_character/get_character with storage pdb:main in.character
    # 플레이어 데이터 저장
        function pdb:save_me
    # 버튼소리
        playsound minecraft:er.gamestart_button_click ui @s ~ ~ ~ 5 1
    # 화면 표기
        tag @s add bos.null2
        tag @s add in_game
        tag @s add bos.waitbar
        tag @s add selected_character
    # 태그 설정
        tag @s remove selection_character