## 하이퍼루프
# player_id temp 입력
function eternal_return:temp/input/id


## 페이지 열기
# 개인 인터렉션 소환 및 태그 부여
execute as @e[tag=hyperloop,tag=interaction] on target at @s[tag=!hyper_loop,distance=..2,tag=!loop] run function eternal_return:system/hyperloop/summon/interaction with storage player_data temp[0]
execute as @e[tag=hyperloop,tag=interaction] at @s on target run tag @s[tag=!hyper_loop,distance=..2,tag=!loop] add hyper_loop
# 화면 선택 시 좌클릭
execute if entity @s[tag=hyper_loop,tag=!loop] run function eternal_return:system/hyperloop/choice_interaction with storage player_data temp[0]
# 클릭 NBT 태그 지우기
execute as @e[tag=hyperloop,tag=interaction] at @s run data remove entity @s interaction
execute as @e[tag=choice_] if data entity @s attack run kill @s


## 위치 선정 후
# 스코어보드
scoreboard players add @s[tag=loop,scores={CT1=..120}] CT1 1
scoreboard players set @s[tag=!loop] CT1 0
# 선택한 위치로 이동
execute if score @s CT1 matches 62 run function eternal_return:system/hyperloop/warp with storage player_data temp[0]


## 하이퍼루프에서 멀어질 때
execute if entity @e[tag=hyperloop,tag=interaction,distance=2..10] run function eternal_return:system/hyperloop/remove/tag


# player_id temp 초기화
function eternal_return:temp/free/id