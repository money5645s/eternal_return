## 스코어보드
execute as @e[tag=meteor,tag=selected] if score @s CT1 matches ..1200 run scoreboard players add @s CT1 1
# 떨어지기 전
execute as @e[tag=meteor] at @s if score @s[tag=selected] CT1 matches ..1199 run function eternal_return:system/meteor/effect/before_impact
# 떨어진 후
execute if score @e[tag=selected,tag=meteor,limit=1] CT1 matches 1199 run advancement revoke @a only eternal_return:meteor
execute if score @e[tag=selected,tag=meteor,limit=1] CT1 matches 1199 run advancement grant @a only eternal_return:meteor
execute if score @e[tag=selected,tag=meteor,limit=1] CT1 matches 1199 run tellraw @a [{"text":"[0","color":"gold"},{"score":{"name":"#timer.min","objective":"CT1"}},":",{"score":{"name":"#timer.sec","objective":"CT1"}},"] ",{"selector":"@e[type=marker,tag=selected]"},{"text":"에 운석이 떨어졌습니다."}]
execute as @e[tag=meteor] at @s if score @s[tag=selected] CT1 matches 1200 run function eternal_return:system/meteor/effect/impact

# 객체별 실행 함수
execute as @e[tag=meteor,tag=interaction] at @s run function eternal_return:system/meteor/meteor
execute as @e[tag=player] at @s run function eternal_return:system/meteor/player