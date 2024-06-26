## 카밀로가 때렸을 때
execute as @a[tag=camilo,scores={dmggive=0..},tag=!camilocool] run tag @s add camiloskill
execute as @a[tag=camilo,scores={dmggive=0..},tag=!camilocool] at @s run playsound minecraft:entity.wither.death player @a ~ ~ ~ 1 2
execute as @a[tag=camilo,scores={dmggive=0..}] run tag @s add camilocool

## 스킬 사용 이펙트
execute as @a[tag=camilo,scores={dmggive=0..},tag=!camilocool] at @s run function eternal_return:system/character/particle/main

## 카밀로 스킬 효과
execute as @a[tag=camiloskill] run scoreboard players add @s camiloskill 1
execute as @a[scores={camiloskill=1}] run effect give @s absorption infinite 0 true
execute as @a[scores={camiloskill=1}] run damage @s 2 out_of_world
execute as @a[tag=camiloskill] at @s run particle dust{color:[1.000,0.910,0.098],scale:1.5} ~ ~1 ~ 0.5 0.5 0.5 0.03 3 force
execute as @a[tag=camilo,scores={dmggive=0..},tag=camilocool] run scoreboard players add @s SC 10

## 카밀로 스킬 효과 제거
execute as @a[scores={camiloskill=40..}] run effect clear @s absorption
execute as @a[scores={camiloskill=40..}] run tag @s remove camiloskill
execute as @a[scores={camiloskill=40..}] run scoreboard players reset @s camiloskill

## 카밀로 스킬 쿨타임
execute as @a[tag=camilocool] run scoreboard players add @s SC 1
execute as @a[tag=camilo,scores={SC=140}] run tellraw @s ["",{"text":"3초 후 패시브 스킬이 활성화 됩니다.","color":"yellow"}]
execute as @a[tag=camilo,scores={SC=160}] run tellraw @s ["",{"text":"2초 후 패시브 스킬이 활성화 됩니다.","color":"yellow"}]
execute as @a[tag=camilo,scores={SC=180}] run tellraw @s ["",{"text":"1초 후 패시브 스킬이 활성화 됩니다.","color":"yellow"}]
execute as @a[tag=camilo,scores={SC=200..}] run tellraw @s ["",{"text":"패시브 활성화!","color":"aqua"}]
execute as @a[tag=camilo,scores={SC=200..}] run tag @s remove camilocool
execute as @a[tag=camilo,scores={SC=200..}] run scoreboard players reset @s SC

## 카밀로 태그 제거 했을 때 관련 태그 제거
execute as @a[tag=camiloskill] unless entity @s[tag=camilo] run tag @s remove camiloskill
execute as @a[tag=camilocool] unless entity @s[tag=camilo] run tag @s remove camilocool
execute as @a[scores={camiloskill=1..}] unless entity @s[tag=camilo] run scoreboard players reset @s camiloskill

## 카밀로 태그 보유 시 팀 가입
execute as @a[tag=camilo,tag=team1] run team join camilo @s
execute as @a[tag=camilo,tag=team2] run team join camilo @s
execute as @a[tag=camilo,tag=team3] run team join camilo @s
execute as @a[tag=camilo,tag=team4] run team join camilo @s
execute as @a[tag=camilo,tag=team5] run team join camilo @s
execute as @a[tag=camilo,tag=team6] run team join camilo @s
execute as @a[tag=camilo,tag=team7] run team join camilo @s
execute as @a[tag=camilo,tag=team8] run team join camilo @s
execute as @a[tag=camilo,tag=team9] run team join camilo @s
execute as @a[tag=camilo,tag=team10] run team join camilo @s
execute as @a[tag=camilo,tag=team11] run team join camilo @s
execute as @a[tag=camilo,tag=team12] run team join camilo @s
execute as @a[tag=camilo,tag=team13] run team join camilo @s
execute as @a[tag=camilo,tag=team14] run team join camilo @s
execute as @a[tag=camilo,tag=team15] run team join camilo @s
execute as @a[tag=camilo,tag=team16] run team join camilo @s
execute as @a[tag=camilo,tag=team17] run team join camilo @s
execute as @a[tag=camilo,tag=team18] run team join camilo @s