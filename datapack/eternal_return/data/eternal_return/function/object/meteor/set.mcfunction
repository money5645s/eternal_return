# 1. 대상 존재 여부 확인 및 후보군 선정 (2마리 확보)
tag @e[tag=meteor,tag=!selected,tag=!impacted,type=marker,sort=random,limit=2] add temp_select

# 2. 에러 처리 (선택된 대상이 없으면 종료)
execute unless entity @e[tag=temp_select] run tellraw @s [{"text":"떨어질 위치가 존재하지 않습니다","color":"red"}]
execute unless entity @e[tag=temp_select] run return 0

# 3. 데이터 할당 및 초기 설정 (at @s 추가하여 위치 보정)
execute as @e[tag=temp_select] at @s run function eternal_return:object/meteor/info/summon

# 4. 개별 점수 기록 (순차적 태그 교체)
# [첫 번째 운석 처리] 기록하고 바로 selected로 승격시킨 뒤 temp_select 제거
execute as @e[tag=temp_select,limit=1] run scoreboard players operation impact1 skript = @s NUM
execute as @e[tag=temp_select,limit=1] run tag @s add selected
tag @e[tag=selected,tag=temp_select] remove temp_select

# [두 번째 운석 처리] 남은 temp_select 하나를 마저 기록하고 승격
execute as @e[tag=temp_select,limit=1] run scoreboard players operation impact2 skript = @s NUM
tag @e[tag=temp_select] add selected
tag @e[tag=selected] remove temp_select
scoreboard players set msg skript 3

# 5. 스코어 초기화 (이제 둘 다 selected 태그를 가짐)
scoreboard players set @e[tag=selected,tag=!impacted,type=marker] ct1 0

# 6. 운석 예고 메시지
advancement revoke @a only eternal_return:meteor_30
advancement grant @a only eternal_return:meteor_30

