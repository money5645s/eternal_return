## 점프 패드 플레이어 감지
execute as @e[tag=jumppad] at @s if entity @a[distance=..1.2] run tag @s add on_pad

# Counter 값을 custommodeldata에 대입
execute as @e[tag=jumppad,scores={Counter=1..9}] store result entity @s ArmorItems[3].components.minecraft:custom_model_data int 1 run scoreboard players get @s Counter

##> 스코어보드 처리

## 게이지가 완충 되었을 때
# 태그 부여
execute as @e[tag=jumppad,scores={LoopCool=..5,Counter=8}] at @s run function eternal_return:system/jumppad/get_tag with entity @s ArmorItems[3].components.minecraft:custom_data

# 공장
execute as @e[tag=jumppad,tag=zfactory,scores={LoopCool=..5,Counter=8}] at @s run function eternal_return:system/jumppad/jumping {vector:[0.0,0.8,4.9]}
# 항구
execute as @e[tag=jumppad,tag=zharbor,scores={LoopCool=..5,Counter=8}] at @s run function eternal_return:system/jumppad/jumping {vector:[-2.65,0.8,0.0]}
# 고급 주택가
execute as @e[tag=jumppad,tag=zresidential_area,scores={LoopCool=..5,Counter=8}] at @s run function eternal_return:system/jumppad/jumping {vector:[-3.3,1.5,-3.0]}
# 연못1
execute as @e[tag=jumppad,tag=zpondA_1,scores={LoopCool=..5,Counter=8}] at @s run function eternal_return:system/jumppad/jumping {vector:[-1.3,0.6,0.75]}
execute as @e[tag=jumppad,tag=zpondA_2,scores={LoopCool=..5,Counter=8}] at @s run function eternal_return:system/jumppad/jumping {vector:[1.4,1.0,-0.4]}
# 연못2
execute as @e[tag=jumppad,tag=zpondB_1,scores={LoopCool=..5,Counter=8}] at @s run function eternal_return:system/jumppad/jumping {vector:[3.4,0.8,1.8]}
execute as @e[tag=jumppad,tag=zpondB_2,scores={LoopCool=..5,Counter=8}] at @s run function eternal_return:system/jumppad/jumping {vector:[-3.5,0.8,-2.1]}

# 플레이어와 돼지에게 같은 태그 부여

#> 밟고 있을 때
# 타이머, 게이지 증가
scoreboard players add @e[tag=on_pad] LoopCool 1
scoreboard players add @e[tag=on_pad,scores={LoopCool=6..,Counter=..7}] Counter 1
scoreboard players set @e[tag=on_pad,scores={LoopCool=6..}] LoopCool 0
# 게이지 0 상태에서 처음 밟았을 때
scoreboard players set @e[tag=on_pad,scores={LoopCool=0..,Counter=9}] Counter 1

#> 밟지 않을 때
# 타이머 = 0초 & 게이지 = 0
scoreboard players set @e[tag=jumppad,tag=!on_pad,scores={LoopCool=0..5,Counter=0}] Counter 9
# 타이머, 게이지 감소
scoreboard players remove @e[tag=jumppad,tag=!on_pad,scores={LoopCool=1..,Counter=1..}] LoopCool 1
scoreboard players remove @e[tag=jumppad,tag=!on_pad,scores={LoopCool=..0,Counter=1..8}] Counter 1
scoreboard players set @e[tag=jumppad,tag=!on_pad,scores={LoopCool=..0,Counter=1..8}] LoopCool 5


# 돼지가 땅에 닿을때
scoreboard players add @e[tag=jumping,nbt={OnGround:1b}] LoopCool 1
tp @e[tag=jumping,nbt={OnGround:1b},scores={LoopCool=3..}] ~ -100 ~

# 플레이어 태우고 내리기
execute as @e[tag=jumppad] at @s run function eternal_return:system/jumppad/ride with entity @s ArmorItems[3].components.minecraft:custom_data
execute as @a at @s run function eternal_return:system/jumppad/remove_tag with entity @e[tag=jumping,limit=1,distance=..3,sort=nearest,scores={LoopCool=1..}] ArmorItems[3].components.minecraft:custom_data
execute as @a at @s if entity @e[tag=jumping,scores={LoopCool=1..}] run ride @s dismount



# 안 밟을 때 태그 제거
execute as @e[tag=jumppad] at @s if entity @a[distance=1.2..] run tag @s remove on_pad