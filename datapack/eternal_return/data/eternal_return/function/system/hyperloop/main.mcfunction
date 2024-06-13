## 하이퍼루프

# 페이지 열기
execute as @e[tag=hyperloop,tag=interaction] at @s on target run tag @s[tag=!hyper_loop,distance=..2,tag=!loop] add hyper_loop
# 화면 선택 시 좌클릭
execute as @e[tag=hyperloop,tag=interaction] at @s on attacker if entity @s[tag=hyper_loop,distance=..2,tag=!loop] run function eternal_return:system/hyperloop/display
# 클릭 NBT 태그 지우기
execute as @e[tag=hyperloop,tag=interaction] at @s run data remove entity @s interaction
execute as @e[tag=hyperloop,tag=interaction] at @s run data remove entity @s attack

## 위치 선정 후
# 스코어보드
scoreboard players add @s[tag=loop,scores={CT1=..120}] CT1 1
scoreboard players set @s[tag=!loop] CT1 0
# 선택한 위치로 이동
execute if score @s CT1 matches 62 run function eternal_return:system/hyperloop/warp

## 하이퍼루프에서 멀어질 때
execute if entity @e[tag=hyperloop,tag=interaction,distance=2..10] run tag @s remove hyper_loop
execute if entity @e[tag=hyperloop,tag=interaction,distance=2..10] run tag @s remove loop
execute if entity @e[tag=hyperloop,tag=interaction,distance=2..10] run tag @s add cancel_loading