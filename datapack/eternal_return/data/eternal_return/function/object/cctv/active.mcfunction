## CCTV 활성화
# 상위 함수 : function eternal_return:object/cctv/tick
# 입출력 : 없음

# 쿨타임 텍스트 디스플레이 소환
    execute positioned ~ ~3 ~ run function eternal_return:object/cctv/cooltime/summon {sec:10,tag:"red",size:2.5}
        # 쿨타임 설정
        scoreboard players set @e[type=text_display,tag=cool,tag=red,limit=1,sort=nearest] ct2 10

# 지속시간 텍스트 디스플레이 소환
    # 활성화 되어있지 않았을 때 
        execute unless entity @e[type=text_display,tag=cool,tag=white,distance=..5] positioned ~ ~4 ~ run function eternal_return:object/cctv/cooltime/summon {sec:90,tag:"white",size:1}
        # 쿨타임 설정
        scoreboard players set @n[type=text_display,tag=cool,tag=white,limit=1] ct2 6

# 태그
    # 플레이어 로딩 후 태그 제거
        scoreboard players set @a[tag=activing,distance=..2.5] cctv_tick 120
        tag @a[tag=activing,distance=..2.5] remove done.loading
        tag @a[tag=activing,distance=..2.5] remove activing
    # CCTV
        tag @s add cooltime