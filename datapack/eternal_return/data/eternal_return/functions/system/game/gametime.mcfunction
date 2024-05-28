#금지구역 지정
execute as @e[tag=gametime] run scoreboard players add @s gametime 1
execute as @e[tag=gametime,scores={gametime=1}] run function eternal_return:system/game/area
execute as @e[tag=gametime,scores={gametime=1}] run function eternal_return:system/game/start_armor_set
execute as @e[tag=gametime,scores={gametime=1}] run function eternal_return:system/game/stone/stonearea
execute as @e[tag=gametime,scores={gametime=1}] run tag @e[tag=r] add r1
execute as @e[tag=gametime,scores={gametime=1}] run tag @e[tag=st] add st1
execute as @e[tag=gametime,scores={gametime=1}] run tag @a[tag=player] add credit
execute as @e[tag=gametime,scores={gametime=1}] run tag @s add daytimer
execute as @e[tag=gametime,scores={gametime=1}] run scoreboard players set @a[tag=player] second 30
execute as @e[tag=gametime,scores={gametime=1}] run scoreboard players set @a[tag=player] time 0

execute as @e[tag=gametime,scores={gametime=1801}] run tag @e[tag=st,sort=random,limit=2] add st2
execute as @e[tag=gametime,scores={gametime=1801}] run tellraw @a ["",{"text":"곧 생명의 나무가 생성됩니다. - ","color":"green"},{"text":"위치 : 호텔","color":"yellow"},{"text":", ","color":"gray"},{"text":"묘지","color":"yellow"},{"text":", ","color":"gray"},{"text":"절","color":"yellow"}]
execute as @e[tag=gametime,scores={gametime=1801}] run tellraw @a ["",{"text":"곧 운석이 떨어집니다. - ","color":"blue"},{"text":"위치 : ","color":"yellow"},{"selector":"@e[tag=st2]","color":"yellow"}]

execute as @e[tag=gametime,scores={gametime=2401}] run tag @e[tag=r,sort=random,limit=3] add target
execute as @e[tag=gametime,scores={gametime=2401}] run summon minecraft:interaction -518 83 592 {width:1.1f,height:1.1f,Tags:["hoteltree"]}
execute as @e[tag=gametime,scores={gametime=2401}] run setblock -518 83 592 emerald_block
execute as @e[tag=gametime,scores={gametime=2401}] run summon minecraft:interaction -572 85 471 {width:1.1f,height:1.1f,Tags:["cemeterytree"]}
execute as @e[tag=gametime,scores={gametime=2401}] run setblock -572 85 471 emerald_block
execute as @e[tag=gametime,scores={gametime=2401}] run summon minecraft:interaction -781 83 468 {width:1.1f,height:1.1f,Tags:["templetree"]}
execute as @e[tag=gametime,scores={gametime=2401}] run setblock -781 83 468 emerald_block
execute if entity @e[scores={gametime=2400}] run function eternal_return:system/stone/stonearea2
execute as @e[tag=gametime,scores={gametime=2401}] run tag @e[tag=st2] add starea
execute as @e[tag=gametime,scores={gametime=2401}] run tag @e[tag=st2] remove st
execute as @e[tag=gametime,scores={gametime=2401}] run tellraw @a ["",{"text":"생명의 나무가 생성되었습니다. - ","color":"green"},{"text":"위치 : 호텔","color":"yellow"},{"text":", ","color":"gray"},{"text":"묘지","color":"yellow"},{"text":", ","color":"gray"},{"text":"절","color":"yellow"}]
execute as @e[tag=gametime,scores={gametime=2401}] run tellraw @a ["",{"text":"운석이 떨어졌습니다. - ","color":"blue"},{"text":"위치 : ","color":"yellow"},{"selector":"@e[tag=st2]","color":"yellow"}]
execute as @e[tag=gametime,scores={gametime=2401}] run tellraw @a ["",{"text":" "}]
execute as @e[tag=gametime,scores={gametime=2401}] run tag @e[tag=st2] remove st2
execute as @e[tag=gametime,scores={gametime=2401}] run tellraw @a ["",{"text":"다음 금지구역 - ","color":"yellow"},{"selector":"@e[tag=target]","color":"yellow"}]

execute as @e[tag=gametime,scores={gametime=3601}] run tag @e[tag=target] add noarea
execute as @e[tag=gametime,scores={gametime=3601}] run tag @e[tag=target] remove r
execute as @e[tag=gametime,scores={gametime=3601}] run tag @e[tag=target] remove target
execute as @e[tag=gametime,scores={gametime=3601}] run tag @e[tag=r,sort=random,limit=3] add target
execute as @e[tag=gametime,scores={gametime=4201}] run tag @e[tag=st,sort=random,limit=2] add st2
execute as @e[tag=gametime,scores={gametime=4201}] run tellraw @a ["",{"text":"곧 생명의 나무가 생성됩니다. - ","color":"green"},{"text":"위치 : 숲","color":"yellow"},{"text":", ","color":"gray"},{"text":"개울","color":"yellow"}]
execute as @e[tag=gametime,scores={gametime=4201}] run tellraw @a ["",{"text":"곧 운석이 떨어집니다. - ","color":"blue"},{"text":"위치 : ","color":"yellow"},{"selector":"@e[tag=st2]","color":"yellow"}]
execute as @e[tag=gametime,scores={gametime=3601}] run tellraw @a ["",{"text":"현재 금지구역 - ","color":"red"},{"selector":"@e[tag=noarea]","color":"red"}]
execute as @e[tag=gametime,scores={gametime=3601}] run tellraw @a ["",{"text":"다음 금지구역 - ","color":"yellow"},{"selector":"@e[tag=target]","color":"yellow"}]
execute as @e[tag=gametime,scores={gametime=3601}] run tellraw @a ["",{"text":"안전구역 - ","color":"aqua"},{"selector":"@e[tag=r1,tag=!target,tag=!noarea]","color":"aqua"}]

execute as @e[tag=gametime,scores={gametime=4801}] run tag @e[tag=target] add noarea
execute as @e[tag=gametime,scores={gametime=4801}] run tag @e[tag=target] remove r
execute as @e[tag=gametime,scores={gametime=4801}] run tag @e[tag=target] remove target
execute as @e[tag=gametime,scores={gametime=4801}] run tag @e[tag=r,sort=random,limit=3] add target
execute as @e[tag=gametime,scores={gametime=4801}] run summon minecraft:interaction -511 83 504 {width:1.1f,height:1.1f,Tags:["foresttree"]}
execute as @e[tag=gametime,scores={gametime=4801}] run setblock -511 83 504 emerald_block
execute as @e[tag=gametime,scores={gametime=4801}] run summon minecraft:interaction -717 84 428 {width:1.1f,height:1.1f,Tags:["streamtree"]}
execute as @e[tag=gametime,scores={gametime=4801}] run setblock -717 84 428 emerald_block
execute if entity @e[scores={gametime=4800}] run function eternal_return:system/stone/stonearea2
execute as @e[tag=gametime,scores={gametime=4801}] run tag @e[tag=st2] add starea
execute as @e[tag=gametime,scores={gametime=4801}] run tag @e[tag=st2] remove st
execute as @e[tag=gametime,scores={gametime=4801}] run tellraw @a ["",{"text":"생명의 나무가 생성되었습니다. - ","color":"green"},{"text":"위치 : 숲","color":"yellow"},{"text":", ","color":"gray"},{"text":"개울","color":"yellow"}]
execute as @e[tag=gametime,scores={gametime=4801}] run tellraw @a ["",{"text":"운석이 떨어졌습니다. - ","color":"blue"},{"text":"위치 : ","color":"yellow"},{"selector":"@e[tag=st2]","color":"yellow"}]
execute as @e[tag=gametime,scores={gametime=4801}] run tellraw @a ["",{"text":" "}]
execute as @e[tag=gametime,scores={gametime=4801}] run tag @e[tag=st2] remove st2
execute as @e[tag=gametime,scores={gametime=4801}] run tellraw @a ["",{"text":"현재 금지구역 - ","color":"red"},{"selector":"@e[tag=noarea]","color":"red"}]
execute as @e[tag=gametime,scores={gametime=4801}] run tellraw @a ["",{"text":"다음 금지구역 - ","color":"yellow"},{"selector":"@e[tag=target]","color":"yellow"}]
execute as @e[tag=gametime,scores={gametime=4801}] run tellraw @a ["",{"text":"안전구역 - ","color":"aqua"},{"selector":"@e[tag=r1,tag=!target,tag=!noarea]","color":"aqua"}]

execute as @e[tag=gametime,scores={gametime=6001}] run tag @e[tag=target] add noarea
execute as @e[tag=gametime,scores={gametime=6001}] run tag @e[tag=target] remove r
execute as @e[tag=gametime,scores={gametime=6001}] run tag @e[tag=target] remove target
execute as @e[tag=gametime,scores={gametime=6001}] run tag @e[tag=r,sort=random,limit=3] add target
execute as @e[tag=gametime,scores={gametime=6601}] run tag @e[tag=st,sort=random,limit=2] add st2
execute as @e[tag=gametime,scores={gametime=6601}] run tellraw @a ["",{"text":"곧 운석이 떨어집니다. - ","color":"blue"},{"text":"위치 : ","color":"yellow"},{"selector":"@e[tag=st2]","color":"yellow"}]
execute as @e[tag=gametime,scores={gametime=6001}] run tellraw @a ["",{"text":"현재 금지구역 - ","color":"red"},{"selector":"@e[tag=noarea]","color":"red"}]
execute as @e[tag=gametime,scores={gametime=6001}] run tellraw @a ["",{"text":"다음 금지구역 - ","color":"yellow"},{"selector":"@e[tag=target]","color":"yellow"}]
execute as @e[tag=gametime,scores={gametime=6001}] run tellraw @a ["",{"text":"안전구역 - ","color":"aqua"},{"selector":"@e[tag=r1,tag=!target,tag=!noarea]","color":"aqua"}]

execute as @e[tag=gametime,scores={gametime=7201}] run tag @e[tag=target] add noarea
execute as @e[tag=gametime,scores={gametime=7201}] run tag @e[tag=target] remove r
execute as @e[tag=gametime,scores={gametime=7201}] run tag @e[tag=target] remove target
execute as @e[tag=gametime,scores={gametime=7201}] run tag @e[tag=r,sort=random,limit=3] add target
execute as @e[tag=gametime,scores={gametime=7201}] run tag @e[tag=st2] add starea
execute if entity @e[scores={gametime=7200}] run function eternal_return:system/stone/stonearea2
execute as @e[tag=gametime,scores={gametime=7201}] run tag @e[tag=st2] remove st
execute as @e[tag=gametime,scores={gametime=7201}] run tellraw @a ["",{"text":"운석이 떨어졌습니다. - ","color":"blue"},{"text":"위치 : ","color":"yellow"},{"selector":"@e[tag=st2]","color":"yellow"}]
execute as @e[tag=gametime,scores={gametime=7201}] run tellraw @a ["",{"text":" "}]
execute as @e[tag=gametime,scores={gametime=7201}] run tag @e[tag=st2] remove st2
execute as @e[tag=gametime,scores={gametime=7201}] run tellraw @a ["",{"text":"현재 금지구역 - ","color":"red"},{"selector":"@e[tag=noarea]","color":"red"}]
execute as @e[tag=gametime,scores={gametime=7201}] run tellraw @a ["",{"text":"다음 금지구역 - ","color":"yellow"},{"selector":"@e[tag=target]","color":"yellow"}]
execute as @e[tag=gametime,scores={gametime=7201}] run tellraw @a ["",{"text":"안전구역 - ","color":"aqua"},{"selector":"@e[tag=r1,tag=!target,tag=!noarea]","color":"aqua"}]

execute as @e[tag=gametime,scores={gametime=8401}] run tag @e[tag=target] add noarea
execute as @e[tag=gametime,scores={gametime=8401}] run tag @e[tag=target] remove r
execute as @e[tag=gametime,scores={gametime=8401}] run tag @e[tag=target] remove target
execute as @e[tag=gametime,scores={gametime=8401}] run tag @e[tag=r,sort=random,limit=3] add target
execute as @e[tag=gametime,scores={gametime=8401}] run tellraw @a ["",{"text":"현재 금지구역 - ","color":"red"},{"selector":"@e[tag=noarea]","color":"red"}]
execute as @e[tag=gametime,scores={gametime=8401}] run tellraw @a ["",{"text":"다음 금지구역 - ","color":"yellow"},{"selector":"@e[tag=target]","color":"yellow"}]
execute as @e[tag=gametime,scores={gametime=8401}] run tellraw @a ["",{"text":"안전구역 - ","color":"aqua"},{"selector":"@e[tag=r1,tag=!target,tag=!noarea]","color":"aqua"}]

execute as @e[tag=gametime,scores={gametime=9601}] run tag @e[tag=target] add noarea
execute as @e[tag=gametime,scores={gametime=9601}] run tag @e[tag=target] remove r
execute as @e[tag=gametime,scores={gametime=9601}] run tag @e[tag=target] remove target
execute as @e[tag=gametime,scores={gametime=9601}] run tag @e[tag=r,sort=random,limit=3] add target
execute as @e[tag=gametime,scores={gametime=9601}] run tellraw @a ["",{"text":"현재 금지구역 - ","color":"red"},{"selector":"@e[tag=noarea]","color":"red"}]
execute as @e[tag=gametime,scores={gametime=9601}] run tellraw @a ["",{"text":"최종 안전지대 - ","color":"aqua"},{"selector":"@e[tag=target]","color":"aqua"}]

execute as @e[tag=gametime,scores={gametime=10201}] run tag @e[tag=target] add noarea
execute as @e[tag=gametime,scores={gametime=10201}] run tag @e[tag=target] add lastarea
execute as @e[tag=gametime,scores={gametime=10201}] run tag @e[tag=target] remove r
execute as @e[tag=gametime,scores={gametime=10201}] run tag @e[tag=target] remove target
execute as @e[tag=gametime,scores={gametime=10201}] run tellraw @a ["",{"text":"최종 안전지대를 제외한 모든 곳이 금지구역입니다. 전력으로 싸우세요! - ","color":"yellow"}]

execute as @e[tag=gametime,scores={gametime=10801}] run kill @e[tag=lastarea1]
execute as @e[tag=gametime,scores={gametime=10801}] run tellraw @a ["",{"text":"모든 지역이 금지구역입니다. 최후의 1인을 가리세요!","color":"red","bold": true}]

#보스바
execute as @e[tag=gametime] run scoreboard players add @s bosstime 1
execute store result bossbar boss:timer value run scoreboard players get @e[tag=gametime,limit=1] bosstime
execute as @e[tag=gametime,scores={gametime=1..9600,bosstime=1}] run scoreboard players set @e[tag=gametime] dayminute 1
execute as @e[tag=gametime,scores={gametime=9601..,bosstime=1}] run scoreboard players set @e[tag=gametime] daysecond 30

execute as @e[tag=gametime,scores={gametime=1,bosstime=1}] run bossbar add boss:timer ["",{"text":"1일차 낮 ","color":"gold"},"/ ",{"score":{"name":"@e[tag=gametime]","objective":"dayminute"}}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"}}]
execute as @e[tag=gametime,scores={gametime=1..20,bosstime=1..1200}] run bossbar set boss:timer name ["",{"text":"1일차 낮 ","color":"gold"},"/ ",{"text":"01 ","color":"gold"},": ",{"text":"00","color":"gold"}]
execute as @e[tag=gametime,scores={gametime=21..1200,bosstime=1..1200,daysecond=0..9}] run bossbar set boss:timer name ["",{"text":"1일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"text":"0","color":"gold"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=21..1200,bosstime=1..1200,daysecond=10..}] run bossbar set boss:timer name ["",{"text":"1일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=21..1200,bosstime=1..1200,daysecond=0..9}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"1일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"text":"0","color":"gold"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=21..1200,bosstime=1..1200,daysecond=10..}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"1일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=1,bosstime=1}] run bossbar set boss:timer color yellow
execute as @e[tag=gametime,scores={gametime=1}] run time set day
execute as @e[tag=gametime,scores={gametime=1}] run effect give @a[tag=player] glowing 3 3 true
execute as @e[tag=gametime,scores={bosstime=1}] run bossbar set boss:timer players @a
execute as @e[tag=gametime,scores={gametime=1,bosstime=1}] run bossbar set boss:timer max 1200

execute as @e[tag=gametime,scores={gametime=1201..2400,bosstime=1..1200,daysecond=0..9}] run bossbar set boss:timer name ["",{"text":"1일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"text":"0","color":"dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=1201..2400,bosstime=1..1200,daysecond=10..}] run bossbar set boss:timer name ["",{"text":"1일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=1201..2400,bosstime=1..1200,daysecond=0..9}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"1일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"text":"0","color":"dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=1201..2400,bosstime=1..1200,daysecond=10..}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"1일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=1201,bosstime=1}] run bossbar set boss:timer color purple
execute as @e[tag=gametime,scores={gametime=1190}] run time set midnight
execute as @e[tag=gametime,scores={gametime=1201}] run effect give @a[tag=player] blindness 3 3 true

execute as @e[tag=gametime,scores={gametime=2401..3600,bosstime=1..1200,daysecond=0..9}] run bossbar set boss:timer name ["",{"text":"2일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"text":"0","color":"gold"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=2401..3600,bosstime=1..1200,daysecond=10..}] run bossbar set boss:timer name ["",{"text":"2일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=2401..3600,bosstime=1..1200,daysecond=0..9}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"2일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"text":"0","color":"gold"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=2401..3600,bosstime=1..1200,daysecond=10..}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"2일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=2401,bosstime=1}] run bossbar set boss:timer color yellow
execute as @e[tag=gametime,scores={gametime=2390}] run time set day
execute as @e[tag=gametime,scores={gametime=2041}] run effect give @a[tag=player] glowing 3 3 true

execute as @e[tag=gametime,scores={gametime=3601..4800,bosstime=1..1200,daysecond=0..9}] run bossbar set boss:timer name ["",{"text":"2일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"text":"0","color":"dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=3601..4800,bosstime=1..1200,daysecond=10..}] run bossbar set boss:timer name ["",{"text":"2일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=3601..4800,bosstime=1..1200,daysecond=0..9}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"2일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"text":"0","color":"dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=3601..4800,bosstime=1..1200,daysecond=10..}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"2일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=3601,bosstime=1}] run bossbar set boss:timer color purple
execute as @e[tag=gametime,scores={gametime=3590}] run time set midnight
execute as @e[tag=gametime,scores={gametime=3601}] run effect give @a[tag=player] blindness 3 3 true

execute as @e[tag=gametime,scores={gametime=4801..6000,bosstime=1..1200,daysecond=0..9}] run bossbar set boss:timer name ["",{"text":"3일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"text":"0","color":"gold"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=4801..6000,bosstime=1..1200,daysecond=10..}] run bossbar set boss:timer name ["",{"text":"3일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=4801..6000,bosstime=1..1200,daysecond=0..9}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"3일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"text":"0","color":"gold"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=4801..6000,bosstime=1..1200,daysecond=10..}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"3일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=4801,bosstime=1}] run bossbar set boss:timer color yellow
execute as @e[tag=gametime,scores={gametime=4790}] run time set day
execute as @e[tag=gametime,scores={gametime=4801}] run effect give @a[tag=player] glowing 3 3 true

execute as @e[tag=gametime,scores={gametime=6001..7200,bosstime=1..1200,daysecond=0..9}] run bossbar set boss:timer name ["",{"text":"3일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"text":"0","color":"dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=6001..7200,bosstime=1..1200,daysecond=10..}] run bossbar set boss:timer name ["",{"text":"3일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=6001..7200,bosstime=1..1200,daysecond=0..9}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"3일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"text":"0","color":"dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=6001..7200,bosstime=1..1200,daysecond=10..}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"3일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=6001,bosstime=1}] run bossbar set boss:timer color purple
execute as @e[tag=gametime,scores={gametime=5990}] run time set midnight
execute as @e[tag=gametime,scores={gametime=6001}] run effect give @a[tag=player] blindness 3 3 true

execute as @e[tag=gametime,scores={gametime=7201..8400,bosstime=1..1200,daysecond=0..9}] run bossbar set boss:timer name ["",{"text":"4일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"text":"0","color":"gold"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=7201..8400,bosstime=1..1200,daysecond=10..}] run bossbar set boss:timer name ["",{"text":"4일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=7201..8400,bosstime=1..1200,daysecond=0..9}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"4일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"text":"0","color":"gold"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=7201..8400,bosstime=1..1200,daysecond=10..}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"4일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=7201,bosstime=1}] run bossbar set boss:timer color yellow
execute as @e[tag=gametime,scores={gametime=7190}] run time set day
execute as @e[tag=gametime,scores={gametime=7201}] run effect give @a[tag=player] glowing 3 3 true

execute as @e[tag=gametime,scores={gametime=8401..9600,bosstime=1..1200,daysecond=0..9}] run bossbar set boss:timer name ["",{"text":"4일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"text":"0","color":"dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=8401..9600,bosstime=1..1200,daysecond=10..}] run bossbar set boss:timer name ["",{"text":"4일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=8401..9600,bosstime=1..1200,daysecond=0..9}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"4일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"text":"0","color":"dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=8401..9600,bosstime=1..1200,daysecond=10..}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"4일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=8401,bosstime=1}] run bossbar set boss:timer color purple
execute as @e[tag=gametime,scores={gametime=8390}] run time set midnight
execute as @e[tag=gametime,scores={gametime=8401}] run effect give @a[tag=player] blindness 3 3 true

execute as @e[tag=gametime,scores={gametime=9601..,bosstime=1}] run bossbar set boss:timer max 600
execute as @e[tag=gametime,scores={gametime=9601..10200,bosstime=1..1200,daysecond=0..9}] run bossbar set boss:timer name ["",{"text":"5일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"text":"0","color":"gold"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=9601..10200,bosstime=1..1200,daysecond=10..}] run bossbar set boss:timer name ["",{"text":"5일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=9601..10200,bosstime=1..1200,daysecond=0..9}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"5일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"text":"0","color":"gold"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=9601..10200,bosstime=1..1200,daysecond=10..}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"5일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=9601,bosstime=1}] run bossbar set boss:timer color yellow
execute as @e[tag=gametime,scores={gametime=9590}] run time set day
execute as @e[tag=gametime,scores={gametime=9601}] run effect give @a[tag=player] glowing 3 3 true

execute as @e[tag=gametime,scores={gametime=10201..10800,bosstime=1..1200,daysecond=0..9}] run bossbar set boss:timer name ["",{"text":"5일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"text":"0","color":"dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=10201..10800,bosstime=1..1200,daysecond=10..}] run bossbar set boss:timer name ["",{"text":"5일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=10201..10800,bosstime=1..1200,daysecond=0..9}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"5일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"text":"0","color":"dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=10201..10800,bosstime=1..1200,daysecond=10..}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"5일차 밤 ","color":"dark_purple"},"/ ",{"text":"0","color": "dark_purple"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "dark_purple"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "dark_purple"}]
execute as @e[tag=gametime,scores={gametime=10201,bosstime=1}] run bossbar set boss:timer color purple
execute as @e[tag=gametime,scores={gametime=10190}] run time set night
execute as @e[tag=gametime,scores={gametime=10201}] run effect give @a[tag=player] blindness 3 3 true

execute as @e[tag=gametime,scores={gametime=10801..,bosstime=1..1200,daysecond=0..9}] run bossbar set boss:timer name ["",{"text":"6일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"text":"0","color":"gold"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=10801..,bosstime=1..1200,daysecond=10..}] run bossbar set boss:timer name ["",{"text":"6일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=10801..,bosstime=1..1200,daysecond=0..9}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"6일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"text":"0","color":"gold"},{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=10801..,bosstime=1..1200,daysecond=10..}] if score @s dayminute matches 1.. run bossbar set boss:timer name ["",{"text":"6일차 낮 ","color":"gold"},"/ ",{"text":"0","color": "gold"},{"score":{"name":"@e[tag=gametime]","objective":"dayminute"},"color": "gold"}," : ",{"score":{"name":"@e[tag=gametime]","objective":"daysecond"},"color": "gold"}]
execute as @e[tag=gametime,scores={gametime=10801,bosstime=1}] run bossbar set boss:timer color yellow
execute as @e[tag=gametime,scores={gametime=10790}] run time set day
execute as @e[tag=gametime,scores={gametime=10801}] run effect give @a[tag=player] glowing 3 3 true

# 게임 초기화
execute unless entity @e[tag=gametime] run bossbar remove boss:timer
execute unless entity @e[tag=gametime] run kill @e[tag=r1]
execute unless entity @e[tag=gametime] run time set day
execute unless entity @e[tag=gametime] run kill @e[tag=lastarea1]
execute unless entity @e[tag=gametime] run setblock -518 83 592 air
execute unless entity @e[tag=gametime] run setblock -572 85 471 air
execute unless entity @e[tag=gametime] run setblock -781 83 468 air
execute unless entity @e[tag=gametime] run setblock -511 83 504 air
execute unless entity @e[tag=gametime] run setblock -717 84 428 air
execute unless entity @e[tag=gametime] run kill @e[tag=hoteltree]
execute unless entity @e[tag=gametime] run kill @e[tag=cemeterytree]
execute unless entity @e[tag=gametime] run kill @e[tag=templetree]
execute unless entity @e[tag=gametime] run kill @e[tag=foresttree]
execute unless entity @e[tag=gametime] run kill @e[tag=streamtree]
execute unless entity @e[tag=gametime] at @e[tag=st1] if block ~ ~ ~ sculk run setblock ~ ~ ~ air
execute unless entity @e[tag=gametime] run kill @e[tag=st1]
execute unless entity @e[tag=gametime] run kill @e[tag=stone]
execute as @e[tag=gametime,scores={gametime=1..9600,bosstime=1200}] run scoreboard players set @s bosstime 0
execute as @e[tag=gametime,scores={gametime=9601..10800,bosstime=600}] run scoreboard players set @s bosstime 0