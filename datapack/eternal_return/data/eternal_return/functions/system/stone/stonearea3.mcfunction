## 골목길
execute unless entity @a[tag=alleystone1] as @e[tag=alleystone] on target at @s if entity @e[tag=starea,name="골목길",distance=..3] run tag @s add alleystone1
execute as @a[tag=alleystone1] run scoreboard players add @s stone 1
execute as @a[tag=alleystone1] at @s unless entity @e[tag=starea,name="골목길",distance=..3] run data remove entity @e[tag=alleystone,limit=1] interaction
execute as @a[tag=alleystone1] at @s unless entity @e[tag=starea,name="골목길",distance=..3] run scoreboard players reset @s stone
execute as @a[tag=alleystone1] at @s unless entity @e[tag=starea,name="골목길",distance=..3] run tag @s remove alleystone1
execute if entity @a[tag=alleystone1,scores={stone=160..}] as @e[tag=starea,name="골목길"] at @s run setblock ~ ~ ~ air
execute if entity @a[tag=alleystone1,scores={stone=160..}] as @e[tag=starea,name="골목길"] at @s run kill @s
execute as @a[tag=alleystone1,scores={stone=160..}] run kill @e[tag=alleystone]
execute as @a[tag=alleystone1,scores={stone=160..}] run tag @s remove alleystone1

## 양궁장
execute unless entity @a[tag=archerystone1] as @e[tag=archerystone] on target at @s if entity @e[tag=starea,name="양궁장",distance=..3] run tag @s add archerystone1
execute as @a[tag=archerystone1] run scoreboard players add @s stone 1
execute as @a[tag=archerystone1] at @s unless entity @e[tag=starea,name="양궁장",distance=..3] run data remove entity @e[tag=archerystone,limit=1] interaction
execute as @a[tag=archerystone1] at @s unless entity @e[tag=starea,name="양궁장",distance=..3] run scoreboard players reset @s stone
execute as @a[tag=archerystone1] at @s unless entity @e[tag=starea,name="양궁장",distance=..3] run tag @s remove archerystone1
execute if entity @a[tag=archerystone1,scores={stone=160..}] as @e[tag=starea,name="양궁장"] at @s run setblock ~ ~ ~ air
execute if entity @a[tag=archerystone1,scores={stone=160..}] as @e[tag=starea,name="양궁장"] at @s run kill @s
execute as @a[tag=archerystone1,scores={stone=160..}] run kill @e[tag=archerystone]
execute as @a[tag=archerystone1,scores={stone=160..}] run tag @s remove archerystone1

## 모래사장
execute unless entity @a[tag=beachstone1] as @e[tag=beachstone] on target at @s if entity @e[tag=starea,name="모래사장",distance=..3] run tag @s add beachstone1
execute as @a[tag=beachstone1] run scoreboard players add @s stone 1
execute as @a[tag=beachstone1] at @s unless entity @e[tag=starea,name="모래사장",distance=..3] run data remove entity @e[tag=beachstone,limit=1] interaction
execute as @a[tag=beachstone1] at @s unless entity @e[tag=starea,name="모래사장",distance=..3] run scoreboard players reset @s stone
execute as @a[tag=beachstone1] at @s unless entity @e[tag=starea,name="모래사장",distance=..3] run tag @s remove beachstone1
execute if entity @a[tag=beachstone1,scores={stone=160..}] as @e[tag=starea,name="모래사장"] at @s run setblock ~ ~ ~ air
execute if entity @a[tag=beachstone1,scores={stone=160..}] as @e[tag=starea,name="모래사장"] at @s run kill @s
execute as @a[tag=beachstone1,scores={stone=160..}] run kill @e[tag=beachstone]
execute as @a[tag=beachstone1,scores={stone=160..}] run tag @s remove beachstone1

## 성당
execute unless entity @a[tag=chapelstone1] as @e[tag=chapelstone] on target at @s if entity @e[tag=starea,name="성당",distance=..3] run tag @s add chapelstone1
execute as @a[tag=chapelstone1] run scoreboard players add @s stone 1
execute as @a[tag=chapelstone1] at @s unless entity @e[tag=starea,name="성당",distance=..3] run data remove entity @e[tag=chapelstone,limit=1] interaction
execute as @a[tag=chapelstone1] at @s unless entity @e[tag=starea,name="성당",distance=..3] run scoreboard players reset @s stone
execute as @a[tag=chapelstone1] at @s unless entity @e[tag=starea,name="성당",distance=..3] run tag @s remove chapelstone1
execute if entity @a[tag=chapelstone1,scores={stone=160..}] as @e[tag=starea,name="성당"] at @s run setblock ~ ~ ~ air
execute if entity @a[tag=chapelstone1,scores={stone=160..}] as @e[tag=starea,name="성당"] at @s run kill @s
execute as @a[tag=chapelstone1,scores={stone=160..}] run kill @e[tag=chapelstone]
execute as @a[tag=chapelstone1,scores={stone=160..}] run tag @s remove chapelstone1

## 항구
execute unless entity @a[tag=dockstone1] as @e[tag=dockstone] on target at @s if entity @e[tag=starea,name="항구",distance=..3] run tag @s add dockstone1
execute as @a[tag=dockstone1] run scoreboard players add @s stone 1
execute as @a[tag=dockstone1] at @s unless entity @e[tag=starea,name="항구",distance=..3] run data remove entity @e[tag=dockstone,limit=1] interaction
execute as @a[tag=dockstone1] at @s unless entity @e[tag=starea,name="항구",distance=..3] run scoreboard players reset @s stone
execute as @a[tag=dockstone1] at @s unless entity @e[tag=starea,name="항구",distance=..3] run tag @s remove dockstone1
execute if entity @a[tag=dockstone1,scores={stone=160..}] as @e[tag=starea,name="항구"] at @s run setblock ~ ~ ~ air
execute if entity @a[tag=dockstone1,scores={stone=160..}] as @e[tag=starea,name="항구"] at @s run kill @s
execute as @a[tag=dockstone1,scores={stone=160..}] run kill @e[tag=dockstone]
execute as @a[tag=dockstone1,scores={stone=160..}] run tag @s remove dockstone1

## 공장
execute unless entity @a[tag=factorystone1] as @e[tag=factorystone] on target at @s if entity @e[tag=starea,name="공장",distance=..3] run tag @s add factorystone1
execute as @a[tag=factorystone1] run scoreboard players add @s stone 1
execute as @a[tag=factorystone1] at @s unless entity @e[tag=starea,name="공장",distance=..3] run data remove entity @e[tag=factorystone,limit=1] interaction
execute as @a[tag=factorystone1] at @s unless entity @e[tag=starea,name="공장",distance=..3] run scoreboard players reset @s stone
execute as @a[tag=factorystone1] at @s unless entity @e[tag=starea,name="공장",distance=..3] run tag @s remove factorystone1
execute if entity @a[tag=factorystone1,scores={stone=160..}] as @e[tag=starea,name="공장"] at @s run setblock ~ ~ ~ air
execute if entity @a[tag=factorystone1,scores={stone=160..}] as @e[tag=starea,name="공장"] at @s run kill @s
execute as @a[tag=factorystone1,scores={stone=160..}] run kill @e[tag=factorystone]
execute as @a[tag=factorystone1,scores={stone=160..}] run tag @s remove factorystone1

## 소방서
execute unless entity @a[tag=firestone1] as @e[tag=firestone] on target at @s if entity @e[tag=starea,name="소방서",distance=..3] run tag @s add firestone1
execute as @a[tag=firestone1] run scoreboard players add @s stone 1
execute as @a[tag=firestone1] at @s unless entity @e[tag=starea,name="소방서",distance=..3] run data remove entity @e[tag=firestone,limit=1] interaction
execute as @a[tag=firestone1] at @s unless entity @e[tag=starea,name="소방서",distance=..3] run scoreboard players reset @s stone
execute as @a[tag=firestone1] at @s unless entity @e[tag=starea,name="소방서",distance=..3] run tag @s remove firestone1
execute if entity @a[tag=firestone1,scores={stone=160..}] as @e[tag=starea,name="소방서"] at @s run setblock ~ ~ ~ air
execute if entity @a[tag=firestone1,scores={stone=160..}] as @e[tag=starea,name="소방서"] at @s run kill @s
execute as @a[tag=firestone1,scores={stone=160..}] run kill @e[tag=firestone]
execute as @a[tag=firestone1,scores={stone=160..}] run tag @s remove firestone1

## 주유소
execute unless entity @a[tag=gasstone1] as @e[tag=gasstone] on target at @s if entity @e[tag=starea,name="주유소",distance=..3] run tag @s add gasstone1
execute as @a[tag=gasstone1] run scoreboard players add @s stone 1
execute as @a[tag=gasstone1] at @s unless entity @e[tag=starea,name="주유소",distance=..3] run data remove entity @e[tag=gasstone,limit=1] interaction
execute as @a[tag=gasstone1] at @s unless entity @e[tag=starea,name="주유소",distance=..3] run scoreboard players reset @s stone
execute as @a[tag=gasstone1] at @s unless entity @e[tag=starea,name="주유소",distance=..3] run tag @s remove gasstone1
execute if entity @a[tag=gasstone1,scores={stone=160..}] as @e[tag=starea,name="주유소"] at @s run setblock ~ ~ ~ air
execute if entity @a[tag=gasstone1,scores={stone=160..}] as @e[tag=starea,name="주유소"] at @s run kill @s
execute as @a[tag=gasstone1,scores={stone=160..}] run kill @e[tag=gasstone]
execute as @a[tag=gasstone1,scores={stone=160..}] run tag @s remove gasstone1

## 병원
execute unless entity @a[tag=hospitalstone1] as @e[tag=hospitalstone] on target at @s if entity @e[tag=starea,name="병원",distance=..3] run tag @s add hospitalstone1
execute as @a[tag=hospitalstone1] run scoreboard players add @s stone 1
execute as @a[tag=hospitalstone1] at @s unless entity @e[tag=starea,name="병원",distance=..3] run data remove entity @e[tag=hospitalstone,limit=1] interaction
execute as @a[tag=hospitalstone1] at @s unless entity @e[tag=starea,name="병원",distance=..3] run scoreboard players reset @s stone
execute as @a[tag=hospitalstone1] at @s unless entity @e[tag=starea,name="병원",distance=..3] run tag @s remove hospitalstone1
execute if entity @a[tag=hospitalstone1,scores={stone=160..}] as @e[tag=starea,name="병원"] at @s run setblock ~ ~ ~ air
execute if entity @a[tag=hospitalstone1,scores={stone=160..}] as @e[tag=starea,name="병원"] at @s run kill @s
execute as @a[tag=hospitalstone1,scores={stone=160..}] run kill @e[tag=hospitalstone]
execute as @a[tag=hospitalstone1,scores={stone=160..}] run tag @s remove hospitalstone1

## 경찰서
execute unless entity @a[tag=policestone1] as @e[tag=policestone] on target at @s if entity @e[tag=starea,name="경찰서",distance=..3] run tag @s add policestone1
execute as @a[tag=policestone1] run scoreboard players add @s stone 1
execute as @a[tag=policestone1] at @s unless entity @e[tag=starea,name="경찰서",distance=..3] run data remove entity @e[tag=policestone,limit=1] interaction
execute as @a[tag=policestone1] at @s unless entity @e[tag=starea,name="경찰서",distance=..3] run scoreboard players reset @s stone
execute as @a[tag=policestone1] at @s unless entity @e[tag=starea,name="경찰서",distance=..3] run tag @s remove policestone1
execute if entity @a[tag=policestone1,scores={stone=160..}] as @e[tag=starea,name="경찰서"] at @s run setblock ~ ~ ~ air
execute if entity @a[tag=policestone1,scores={stone=160..}] as @e[tag=starea,name="경찰서"] at @s run kill @s
execute as @a[tag=policestone1,scores={stone=160..}] run kill @e[tag=policestone]
execute as @a[tag=policestone1,scores={stone=160..}] run tag @s remove policestone1

## 연못
execute unless entity @a[tag=pondstone1] as @e[tag=pondstone] on target at @s if entity @e[tag=starea,name="연못",distance=..3] run tag @s add pondstone1
execute as @a[tag=pondstone1] run scoreboard players add @s stone 1
execute as @a[tag=pondstone1] at @s unless entity @e[tag=starea,name="연못",distance=..3] run data remove entity @e[tag=pondstone,limit=1] interaction
execute as @a[tag=pondstone1] at @s unless entity @e[tag=starea,name="연못",distance=..3] run scoreboard players reset @s stone
execute as @a[tag=pondstone1] at @s unless entity @e[tag=starea,name="연못",distance=..3] run tag @s remove pondstone1
execute if entity @a[tag=pondstone1,scores={stone=160..}] as @e[tag=starea,name="연못"] at @s run setblock ~ ~ ~ air
execute if entity @a[tag=pondstone1,scores={stone=160..}] as @e[tag=starea,name="연못"] at @s run kill @s
execute as @a[tag=pondstone1,scores={stone=160..}] run kill @e[tag=pondstone]
execute as @a[tag=pondstone1,scores={stone=160..}] run tag @s remove pondstone1

## 학교
execute unless entity @a[tag=schoolstone1] as @e[tag=schoolstone] on target at @s if entity @e[tag=starea,name="학교",distance=..3] run tag @s add schoolstone1
execute as @a[tag=schoolstone1] run scoreboard players add @s stone 1
execute as @a[tag=schoolstone1] at @s unless entity @e[tag=starea,name="학교",distance=..3] run data remove entity @e[tag=schoolstone,limit=1] interaction
execute as @a[tag=schoolstone1] at @s unless entity @e[tag=starea,name="학교",distance=..3] run scoreboard players reset @s stone
execute as @a[tag=schoolstone1] at @s unless entity @e[tag=starea,name="학교",distance=..3] run tag @s remove schoolstone1
execute if entity @a[tag=schoolstone1,scores={stone=160..}] as @e[tag=starea,name="학교"] at @s run setblock ~ ~ ~ air
execute if entity @a[tag=schoolstone1,scores={stone=160..}] as @e[tag=starea,name="학교"] at @s run kill @s
execute as @a[tag=schoolstone1,scores={stone=160..}] run kill @e[tag=schoolstone]
execute as @a[tag=schoolstone1,scores={stone=160..}] run tag @s remove schoolstone1

## 고급 주택가
execute unless entity @a[tag=uptownstone1] as @e[tag=uptownstone] on target at @s if entity @e[tag=starea,name="고급 주택가",distance=..3] run tag @s add uptownstone1
execute as @a[tag=uptownstone1] run scoreboard players add @s stone 1
execute as @a[tag=uptownstone1] at @s unless entity @e[tag=starea,name="고급 주택가",distance=..3] run data remove entity @e[tag=uptownstone,limit=1] interaction
execute as @a[tag=uptownstone1] at @s unless entity @e[tag=starea,name="고급 주택가",distance=..3] run scoreboard players reset @s stone
execute as @a[tag=uptownstone1] at @s unless entity @e[tag=starea,name="고급 주택가",distance=..3] run tag @s remove uptownstone1
execute if entity @a[tag=uptownstone1,scores={stone=160..}] as @e[tag=starea,name="고급 주택가"] at @s run setblock ~ ~ ~ air
execute if entity @a[tag=uptownstone1,scores={stone=160..}] as @e[tag=starea,name="고급 주택가"] at @s run kill @s
execute as @a[tag=uptownstone1,scores={stone=160..}] run kill @e[tag=uptownstone]
execute as @a[tag=uptownstone1,scores={stone=160..}] run tag @s remove uptownstone1

## 창고
execute unless entity @a[tag=warehousestone1] as @e[tag=warehousestone] on target at @s if entity @e[tag=starea,name="창고",distance=..3] run tag @s add warehousestone1
execute as @a[tag=warehousestone1] run scoreboard players add @s stone 1
execute as @a[tag=warehousestone1] at @s unless entity @e[tag=starea,name="창고",distance=..3] run data remove entity @e[tag=warehousestone,limit=1] interaction
execute as @a[tag=warehousestone1] at @s unless entity @e[tag=starea,name="창고",distance=..3] run scoreboard players reset @s stone
execute as @a[tag=warehousestone1] at @s unless entity @e[tag=starea,name="창고",distance=..3] run tag @s remove warehousestone1
execute if entity @a[tag=warehousestone1,scores={stone=160..}] as @e[tag=starea,name="창고"] at @s run setblock ~ ~ ~ air
execute if entity @a[tag=warehousestone1,scores={stone=160..}] as @e[tag=starea,name="창고"] at @s run kill @s
execute as @a[tag=warehousestone1,scores={stone=160..}] run kill @e[tag=warehousestone]
execute as @a[tag=warehousestone1,scores={stone=160..}] run tag @s remove warehousestone1



## 채집
execute as @a[scores={stone=1}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 1% ]","color":"yellow"}]
execute as @a[scores={stone=16}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 10% ]","color":"yellow"}]
execute as @a[scores={stone=32}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 20% ]","color":"yellow"}]
execute as @a[scores={stone=48}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 30% ]","color":"yellow"}]
execute as @a[scores={stone=64}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 40% ]","color":"yellow"}]
execute as @a[scores={stone=80}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 50% ]","color":"yellow"}]
execute as @a[scores={stone=96}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 60% ]","color":"yellow"}]
execute as @a[scores={stone=112}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 70% ]","color":"yellow"}]
execute as @a[scores={stone=128}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 80% ]","color":"yellow"}]
execute as @a[scores={stone=144}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 90% ]","color":"yellow"}]
execute as @a[scores={stone=160}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 100% ]","color":"yellow"}]
execute as @a[scores={stone=160..}] run tellraw @s ["",{"text":"채집이 완료되었습니다.","color":"yellow"}]
execute as @a[scores={stone=160..}] run give @s minecraft:echo_shard[item_name='{"bold":true,"color":"blue","text":"운석"}'] 1
execute as @a[scores={stone=160..}] run scoreboard players reset @s stone