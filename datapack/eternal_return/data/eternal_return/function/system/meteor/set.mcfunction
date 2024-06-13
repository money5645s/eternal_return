
# 임의의 두 마커에 태그 부여
tag @e[tag=meteor,tag=!selected,tag=marker,sort=random,limit=2] add selected
scoreboard players set @e[tag=meteor,tag=selected,tag=marker] CT1 0
# 에러 메시지
execute unless entity @e[tag=selected,tag=meteor,tag=marker] run tellraw @s [{"text":"떨어질 위치가 존재하지 않습니다","color":"red"}]

# 운석 예고 메시지
execute if entity @e[tag=selected,tag=meteor,tag=marker] run tellraw @a [{"text":"곧 운석이 떨어집니다. - ","color":"blue"},{"text":"위치 : ","color":"yellow"},{"selector":"@e[tag=selected,tag=meteor]","color":"yellow"}]
