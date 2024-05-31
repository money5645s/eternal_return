## 하이퍼루프
execute as @e[tag=hyperloop,tag=interaction,tag=!loop] at @s on target run tag @a[tag=!open_hyperloop,distance=..2] add open_hyperloop
# 인터렉션 재클릭
execute as @e[tag=hyperloop,tag=interaction] at @s run data remove entity @s interaction

# 하이퍼루프 페이지 열기
execute if entity @s[tag=open_hyperloop,tag=!loop] run function eternal_return:system/hyperloop/display
# 채팅창 클릭 허용
scoreboard players enable @a[tag=open_hyperloop,tag=!loop] ChatClick


# 하이퍼루프에서 멀어질 때
execute if entity @s[tag=open_hyperloop,tag=!loop] if entity @e[tag=hyperloop,tag=interaction,distance=2..10] run function df_library:window/resetchat
execute if entity @s[tag=open_hyperloop] if entity @e[tag=hyperloop,tag=interaction,distance=2..10] run tag @s remove open_hyperloop
execute if entity @e[tag=hyperloop,tag=interaction,distance=2..10] run tag @s remove loop
execute if entity @e[tag=hyperloop,tag=interaction,distance=2..10] run tag @s add cancel_loading
execute if entity @e[tag=hyperloop,tag=interaction,distance=2..10] run scoreboard players set @s ChatClick 0


## 위치 선정 후
# 스코어보드
scoreboard players add @a[tag=open_hyperloop,tag=loop,scores={CT1=..120}] CT1 1
scoreboard players set @a[tag=!loop] CT1 0


# 워프 취소 시
#execute as @a[scores={loop=1..59}] at @s unless block ~ ~-1 ~ minecraft:gilded_blackstone run tellraw @s {"text":"하이퍼루프 이용을 취소되었습니다.","color": "red"}
#execute as @a at @s unless block ~ ~-1 ~ minecraft:gilded_blackstone run scoreboard players reset @s loop
#execute as @a at @s unless block ~ ~-1 ~ minecraft:gilded_blackstone run tag @s remove loop1e