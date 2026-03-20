tellraw @a {"text":"새로 고침 완료! 제발 제발 F3 + T !!!!!","color":"green"}
forceload add -561 512 -585 536
forceload add -243 485 -243 479


##################### CONFIG ####################

    # 밸런싱을 위해 만든 load 함수

    function eternal_return:config/game
    function eternal_return:config/map
    function eternal_return:config/character

#################################################



## 게임 설정
    # 맵 기본 설정

    # 게임 시간 초기화
    
## 청크 로딩
    function eternal_return:forceload
    function eternal_return:game/fied_set

worldborder set 10000 1
gamerule pvp false
## 팀 추가
    team add player
    team modify player nametagVisibility never
    team modify player seeFriendlyInvisibles true
    team add bush
    team modify bush nametagVisibility never
    team modify bush seeFriendlyInvisibles true


## 스코어 보드 추가
    # 운석 충돌 장소
        scoreboard objectives add imapct1 dummy
        scoreboard objectives add imapct2 dummy
    # 스크린
        scoreboard objectives add Page.loading dummy
        scoreboard objectives add Page.loading.tick dummy
        scoreboard objectives add Page.tick dummy
        scoreboard objectives add Page.slot dummy
        scoreboard objectives add Page.sec dummy

    ## 플레이어
        # hp, k/d
        scoreboard objectives add hp health {"text":"♥","color":"red"}
            scoreboard objectives setdisplay below_name hp
        scoreboard objectives add kill_player playerKillCount
        scoreboard objectives add kill playerKillCount
        scoreboard objectives add death deathCount
        scoreboard objectives add death_count deathCount
        # 레벨, 스킬포인트
        scoreboard objectives add level dummy
        scoreboard objectives add exp dummy
        scoreboard objectives add skill_point dummy
        # 스킬 쿨타임 이미지
            # 패시브
                scoreboard objectives add active_level dummy
                scoreboard objectives add ACD dummy
            # 액티브
                scoreboard objectives add passive_level dummy
                scoreboard objectives add PCD dummy
    # CCTV 활성화 시간
        scoreboard objectives add cctv_tick dummy
    # 크레딧
        scoreboard objectives add credit dummy
    # 캐릭터
        scoreboard objectives add character dummy

    # 포션 레벨
        scoreboard objectives add resistance dummy

    # 센터 스코어
        scoreboard objectives add centerbar.tick dummy
        scoreboard objectives add centerbar.sec dummy
    # 부활 스코어
        scoreboard objectives add resurrection dummy
        scoreboard objectives add resurretion.tick dummy
        scoreboard objectives add resurretion.sec dummy
    # 임시변수/넘버링변수
        scoreboard objectives add TEMP dummy
        scoreboard objectives add NUM dummy
    # 오브젝트 쿨타임
        scoreboard objectives add ct1 dummy
        scoreboard objectives add ct2 dummy
    # 게임시간
    scoreboard objectives add time dummy
    # 상수
        scoreboard objectives add CONST dummy
        # 상수 리스트
            scoreboard players set #0 CONST 0
            scoreboard players set #1 CONST 1
            scoreboard players set #2 CONST 2
            scoreboard players set #3 CONST 3
            scoreboard players set #4 CONST 4
            scoreboard players set #5 CONST 5
            scoreboard players set #6 CONST 6
            scoreboard players set #7 CONST 7
            scoreboard players set #8 CONST 8
            scoreboard players set #9 CONST 9
            scoreboard players set #10 CONST 10
            scoreboard players set #20 CONST 20
            scoreboard players set #60 CONST 60
            scoreboard players set #90 CONST 90
            scoreboard players set #100 CONST 100
            scoreboard players set #200 CONST 200
            scoreboard players set #360 CONST 360
            scoreboard players set #600 CONST 600
            scoreboard players set #1000 CONST 1000
            scoreboard players set #100000 CONST 100000
        scoreboard objectives add Region dummy
    # 금지구역 남은 시간
        scoreboard objectives add warp_point dummy

    # 금지구역 남은 시간
        scoreboard objectives add bantime dummy
        scoreboard objectives add bantime.tick dummy
    # 승리 쿨타임
        scoreboard objectives add win.tick dummy
    # 패배 쿨타임
        scoreboard objectives add lose.tick dummy
    ## 스크립트 정보교환
        scoreboard objectives add skript dummy
    ## 선택지 트리거 명령어
        scoreboard objectives add select trigger
    ## 튜토리얼
        scoreboard objectives add tutorial dummy
        scoreboard objectives add tutorial.tick dummy
    # 야생동물 관련 스코어보드 생성
        scoreboard objectives add ER.sys dummy
        scoreboard objectives add ER.health dummy
        scoreboard objectives add ER.motionX dummy
        scoreboard objectives add ER.motionY dummy
        scoreboard objectives add ER.motionZ dummy

        scoreboard objectives add ER.cooltime dummy
        scoreboard objectives add df_id dummy

    # 야생동물 체력 및 공격력 및 애니메이션 틱 설정
        # 멧돼지
            scoreboard players set #ER.animal.boar.health ER.sys 37
            scoreboard players set #ER.animal.boar.attack ER.sys 5
            scoreboard players set #ER.animal.boar.cooltime ER.sys 120
            scoreboard players set #ER.animal.boar.skill_effect_tick ER.sys 25
            scoreboard players set #ER.animal.boar.skill_end_tick ER.sys 45
            scoreboard players set #ER.animal.boar.attack_tick_first ER.sys 7
            scoreboard players set #ER.animal.boar.attack_tick_second ER.sys 7
        # 곰
            scoreboard players set #ER.animal.bear.health ER.sys 60
            scoreboard players set #ER.animal.bear.attack ER.sys 6
            scoreboard players set #ER.animal.bear.cooltime ER.sys 120
            scoreboard players set #ER.animal.bear.skill_effect_tick ER.sys 14
            scoreboard players set #ER.animal.bear.skill_end_tick ER.sys 15
            scoreboard players set #ER.animal.bear.attack_tick_first ER.sys 8
            scoreboard players set #ER.animal.bear.attack_tick_second ER.sys 8
        # 늑대
            scoreboard players set #ER.animal.wolf.health ER.sys 25
            scoreboard players set #ER.animal.wolf.attack ER.sys 4
            scoreboard players set #ER.animal.wolf.cooltime ER.sys 120
            scoreboard players set #ER.animal.wolf.skill_effect_tick ER.sys 20
            scoreboard players set #ER.animal.wolf.skill_end_tick ER.sys 30
            scoreboard players set #ER.animal.wolf.attack_tick_first ER.sys 8
            scoreboard players set #ER.animal.wolf.attack_tick_second ER.sys 8
        # 알파
            scoreboard players set #ER.animal.alpha.health ER.sys 150
            scoreboard players set #ER.animal.alpha.attack ER.sys 10
            scoreboard players set #ER.animal.alpha.cooltime ER.sys 120
            scoreboard players set #ER.animal.alpha.skill_effect_tick ER.sys 42
            scoreboard players set #ER.animal.alpha.skill_end_tick ER.sys 60
            scoreboard players set #ER.animal.alpha.attack_tick_first ER.sys 12
            scoreboard players set #ER.animal.alpha.attack_tick_second ER.sys 34

            
       


    # 하이퍼루프 영역 번호 지정
        scoreboard players set #ER.place.null ER.sys 0
        scoreboard players set #ER.place.beach ER.sys 1
        scoreboard players set #ER.place.gas_station ER.sys 2
        scoreboard players set #ER.place.golmok ER.sys 3
        scoreboard players set #ER.place.yanggung ER.sys 4
        scoreboard players set #ER.place.school ER.sys 5
        scoreboard players set #ER.place.hotel ER.sys 6
        scoreboard players set #ER.place.fire ER.sys 7
        scoreboard players set #ER.place.police ER.sys 8
        scoreboard players set #ER.place.forest ER.sys 9
        scoreboard players set #ER.place.pond ER.sys 10
        scoreboard players set #ER.place.stream ER.sys 11
        scoreboard players set #ER.place.graveyard ER.sys 12
        scoreboard players set #ER.place.hospital ER.sys 13
        scoreboard players set #ER.place.village ER.sys 14
        scoreboard players set #ER.place.cathedral ER.sys 15
        scoreboard players set #ER.place.storage ER.sys 16
        scoreboard players set #ER.place.port ER.sys 17
        scoreboard players set #ER.place.factory ER.sys 18
        scoreboard players set #ER.place.jul ER.sys 19
    
    # 브리핑룸 남은 시간/인원 표기 창
        scoreboard players set now.bg time 0
        scoreboard players set left.time.bg.tick time 0
        scoreboard players set left.time.bg.num time 0

    # 게임타이머 초기화
        function eternal_return:game/timer/init
