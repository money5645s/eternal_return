#기본 hs 발사
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim,tag=!happyfocus] unless score @s shift matches 1.. if score @s happyfocus matches 1..12 at @s run function eternal_return:system/character/happychaos/hs_shot

#기본 hs 딜, 파티클
execute as @e[tag=happynormalshot] at @s run tag @e[tag=!happy,tag=!happynormalshot,distance=..1.5,limit=1,sort=nearest] add happynormalhit
damage @e[tag=happynormalhit,tag=!cus,limit=1,sort=nearest] 3 thrown by @a[tag=happy,limit=1,sort=nearest]
damage @e[tag=happynormalhit,tag=cus,limit=1,sort=nearest] 5 thrown by @a[tag=happy,limit=1,sort=nearest]
tag @e[tag=happynormalhit] remove happynormalhit
execute as @e[tag=happynormalshot] at @s run particle dust{color:[0.251,0.800,1.000],scale:1} ~ ~ ~ 0 0 0 0.03 15 normal

#총알 갯수 표기
execute as @a[tag=happy,scores={happybullet=6}] run item replace entity @s container.6 with stone_button[item_name='{"color":"gray","text":"총알"}'] 6
execute as @a[tag=happy,scores={happybullet=5}] run item replace entity @s container.6 with stone_button[item_name='{"color":"gray","text":"총알"}'] 5
execute as @a[tag=happy,scores={happybullet=4}] run item replace entity @s container.6 with stone_button[item_name='{"color":"gray","text":"총알"}'] 4
execute as @a[tag=happy,scores={happybullet=3}] run item replace entity @s container.6 with stone_button[item_name='{"color":"gray","text":"총알"}'] 3
execute as @a[tag=happy,scores={happybullet=2}] run item replace entity @s container.6 with stone_button[item_name='{"color":"gray","text":"총알"}'] 2
execute as @a[tag=happy,scores={happybullet=1}] run item replace entity @s container.6 with stone_button[item_name='{"color":"gray","text":"총알"}'] 1
execute as @a[tag=happy,scores={happybullet=0}] run item replace entity @s container.6 with air
execute as @a[tag=happy,scores={happybullet=6..}] run scoreboard players set @s happybullet 6
execute as @a[tag=happy,scores={happybullet=1..}] run tag @s add happybullet
execute as @a[tag=happy,scores={happybullet=0}] run tag @s remove happybullet

#총알 장전
execute as @a[tag=happy,scores={happyright=1..,shift=1..}] unless score @s happybullet matches 6.. run tag @s add happyreload
execute as @a[tag=happy,tag=happyreload] run scoreboard players add @s happyreload 1
execute as @a[tag=happy,tag=happyreload] run effect clear @s slowness
execute as @a[tag=happy,tag=happyreload] run tag @s remove happyaim
execute as @a[tag=happy,tag=happyreload,scores={happyreload=4}] run scoreboard players add @s happybullet 1
execute as @a[tag=happy,tag=happyreload,scores={happyreload=4}] at @s run playsound minecraft:block.vault.reject_rewarded_player master @a ~ ~ ~ 1 1 0
execute as @a[tag=happy,tag=happyreload,scores={happyreload=4}] run scoreboard players set @s happyreload 0
execute as @a[tag=happy,tag=happyreload,scores={happybullet=6..}] run tag @s remove happyreload
execute as @a[tag=happy,tag=happyreload,scores={shift=0}] run tag @s remove happyreload
execute as @a[tag=happy,tag=happyreload] at @s run particle smoke ~ ~0.75 ~ 0.1 0.1 0.1 0.03 15 normal
execute as @a[tag=happy] unless entity @s[tag=happyreload] run scoreboard players reset @s happyreload
execute as @a[scores={shift=1..}] run scoreboard players set @s shift 0

#정조준 hs 발사
execute as @a[tag=happy,tag=!happyaim,nbt={Inventory:[{id:"minecraft:carrot_on_a_stick",Slot:-106b}]}] run tag @s add happyaim1
execute as @a[tag=happy,tag=!happyaim,nbt={Inventory:[{id:"minecraft:carrot_on_a_stick",Slot:-106b}]}] run item replace entity @s weapon.mainhand from entity @s weapon.offhand
execute as @a[tag=happy,tag=!happyaim,nbt={Inventory:[{id:"minecraft:carrot_on_a_stick",Slot:-106b}]}] run item replace entity @s weapon.offhand with air
execute as @a[tag=happy,tag=happyaim] run item replace entity @s container.5 with lantern[item_name='{"color":"gold","text":"[ 정조준 ]"}'] 1
execute as @a[tag=happy,tag=!happyaim] run item replace entity @s container.5 with soul_lantern[item_name='{"color":"aqua","text":"[ 일반 사격 ]"}'] 1
execute as @a[tag=happy,tag=happyaim] run effect give @s slowness 1 100 true
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim,tag=!happyfocus] unless score @s shift matches 1.. if score @s happyfocus matches 1..12 unless score @s happyaimc matches 1.. at @s run function eternal_return:system/character/happychaos/214s_hs_shot

#정조준 hs 딜, 파티클
execute as @e[tag=happyaimshot] at @s run tag @e[tag=!happy,tag=!happyaimshot,distance=..1.5,limit=1,sort=nearest] add happyaimhit
damage @e[tag=happyaimhit,tag=!cus,limit=1,sort=nearest] 7 thrown by @a[tag=happy,limit=1,sort=nearest]
damage @e[tag=happyaimhit,tag=cus,limit=1,sort=nearest] 9 thrown by @a[tag=happy,limit=1,sort=nearest]
effect give @e[tag=happyaimhit] levitation 1 1 true
tag @e[tag=happyaimhit] remove happyaimhit
execute as @e[tag=happyaimshot] at @s run particle dust{color:[1.000,0.682,0.129],scale:1} ~ ~ ~ 0 0 0 0.03 15 normal

#정조준 hs 발사 시간 간격
execute as @a[tag=happy,tag=!happyaim,tag=happyaim1] run tag @s add happyaim
execute as @a[tag=happy,tag=happyaim1] run tag @s remove happyaim1

execute as @a[tag=happy,scores={happyaimc=1..}] run scoreboard players add @s happyaimc 1
execute as @a[tag=happy,scores={happyaimc=20..}] run scoreboard players reset @s happyaimc

#정조준 해제
execute as @a[tag=happy,tag=happyaim,nbt={Inventory:[{id:"minecraft:carrot_on_a_stick",Slot:-106b}]}] run tag @s add happyaim1
execute as @a[tag=happy,tag=happyaim,nbt={Inventory:[{id:"minecraft:carrot_on_a_stick",Slot:-106b}]}] run item replace entity @s weapon.mainhand from entity @s weapon.offhand
execute as @a[tag=happy,tag=happyaim,nbt={Inventory:[{id:"minecraft:carrot_on_a_stick",Slot:-106b}]}] run item replace entity @s weapon.offhand with air
execute as @a[tag=happy,tag=happyaim,tag=happyaim1] run effect clear @s slowness
execute as @a[tag=happy,tag=happyaim,tag=happyaim1] run tag @s remove happyaim
execute as @a[tag=happy,tag=happyaim1] run tag @s remove happyaim1
execute as @a[tag=happy] unless entity @s[tag=happyaim] run scoreboard players reset @s happyaimc



#발사체 제거
execute as @a[tag=happy,scores={happyright=1..}] at @s run kill @e[tag=happynormalshot]
execute as @a[tag=happy,scores={happyright=1..}] at @s run kill @e[tag=happyaimshot]
execute as @a[tag=happy,scores={happyright=1..}] run scoreboard players reset @s happyright


## 커스
# 커스 던지기
execute as @e[tag=!happy,nbt={active_effects:[{id:"minecraft:unluck"}]}] run tag @s add cus
execute as @e[tag=cus] at @s run particle dust{color:[0.231,1.000,0.847],scale:1} ~ ~1 ~ 0.5 0.5 0.5 0 5 force
execute as @e[tag=cus] at @s run particle dust{color:[0.188,0.188,0.188],scale:1} ~ ~1 ~ 0.5 0.5 0.5 0 5 force
execute as @e[tag=cus] unless entity @s[nbt={active_effects:[{id:"minecraft:unluck"}]}] run tag @s remove cus

#커스 쿨타임
execute as @a[tag=happy] unless entity @s[nbt={Inventory:[{id:"minecraft:splash_potion"}]}] run scoreboard players add @s SC 1
execute as @a[tag=happy,scores={SC=100..}] run give @s minecraft:splash_potion[item_name='{"color":"aqua","text":"[ 커스 ]"}',lore=['{"italic":true,"text":"상대에게 적중 시 5초간 디버프가 걸린다."}','{"italic":true,"text":"디버프에 걸린 적은 받는 총알 데미지가 2 증가한다."}'],hide_additional_tooltip={},potion_contents={custom_color:3735518,custom_effects:[{id:"minecraft:unluck",amplifier:1,duration:100,show_particles:0b,show_icon:1b,ambient:0b}]}] 1
execute as @a[tag=happy,scores={SC=40}] run tellraw @s ["",{"text":"3초 후 [ 커스 ] 스킬을 다시 사용할 수 있습니다.","color":"yellow"}]
execute as @a[tag=happy,scores={SC=60}] run tellraw @s ["",{"text":"2초 후 [ 커스 ] 스킬을 다시 사용할 수 있습니다.","color":"yellow"}]
execute as @a[tag=happy,scores={SC=80}] run tellraw @s ["",{"text":"1초 후 [ 커스 ] 스킬을 다시 사용할 수 있습니다.","color":"yellow"}]
execute as @a[tag=happy,scores={SC=100}] run tellraw @s ["",{"text":"[ 커스 ] 스킬 사용 가능!","color":"aqua"}]
execute as @a[tag=happy,scores={SC=100..}] run scoreboard players reset @s SC
execute as @a[tag=happy] if entity @s[nbt={Inventory:[{id:"minecraft:splash_potion"}]}] run scoreboard players reset @s SC

## 집중
function eternal_return:system/character/happychaos/concentration
scoreboard players set @a[scores={happyfocus=..0}] happyfocus 0
scoreboard players set @a[scores={happyfocus=12..}] happyfocus 12
execute as @a[tag=happy,scores={dmggive=0..}] unless score @s happyfocus matches 12 run scoreboard players add @s happyfocus 1

execute as @a[tag=happy,scores={happydrop=0..}] at @s run data merge entity @e[type=item,limit=1,sort=nearest] {PickupDelay:0,Item:{}}
execute as @a[tag=happy,scores={happydrop=0..}] run tag @s add happyfocus
scoreboard players reset @a[scores={happydrop=0..}] happydrop

execute as @a[tag=happyfocus] run tag @s remove happyaim
execute as @a[tag=happyfocus] run effect give @s slowness infinite 100 true
execute as @a[tag=happyfocus] run attribute @s minecraft:generic.jump_strength base set 0
execute as @a[tag=happyfocus] at @s run particle minecraft:trial_spawner_detection_ominous ~ ~ ~ 0.5 0.5 0.5 0 2 force
execute as @a[scores={happyfocus_player=1}] at @s run playsound minecraft:block.trial_spawner.spawn_item_begin master @a ~ ~ ~ 2 1 0
execute as @a[tag=happyfocus] run scoreboard players add @s happyfocus_player 1
execute as @a[scores={happyfocus_player=5}] run scoreboard players add @s happyfocus 1
execute as @a[scores={happyfocus_player=10}] run scoreboard players add @s happyfocus 1
execute as @a[scores={happyfocus_player=15}] run scoreboard players add @s happyfocus 1
execute as @a[scores={happyfocus_player=20}] run scoreboard players add @s happyfocus 1
execute as @a[scores={happyfocus_player=20..}] run tag @s remove happyfocus
execute as @a[scores={happyfocus_player=20..}] run effect clear @s slowness
execute as @a[scores={happyfocus_player=20..}] run attribute @s minecraft:generic.jump_strength base set 0.42
execute as @a[scores={happyfocus_player=20..}] run tag @s add happyfocus_decrease
execute as @a[scores={happyfocus_player=20..}] run scoreboard players reset @s happyfocus_player

execute as @a[tag=happyfocus_decrease] run scoreboard players add @s happyfocus_decrease 1
execute as @a[scores={happyfocus_decrease=100..}] run tag @s remove happyfocus_decrease
execute as @a[scores={happyfocus_decrease=100..}] run scoreboard players reset @s happyfocus_decrease

#해피 태그 제거
execute as @a[tag=player] unless entity @s[tag=happy] run tag @s remove happybullet
execute as @a[tag=player] unless entity @s[tag=happy] run tag @s remove happyaim1
execute as @a[tag=player] unless entity @s[tag=happy] run tag @s remove happyaim
execute as @a[tag=player] unless entity @s[tag=happy] run tag @s remove happyreload
execute as @a[tag=player] unless entity @s[tag=happy] run scoreboard players reset @s happyaimc
execute as @a[tag=player] unless entity @s[tag=happy] run scoreboard players reset @s happyright
execute as @a[tag=player] unless entity @s[tag=happy] run scoreboard players reset @s happybullet
execute as @a[tag=player] unless entity @s[tag=happy] run scoreboard players reset @s happyreload

## 해피 태그 보유 시 팀 가입
execute as @a[tag=happy,tag=team1] run team join happy @s
execute as @a[tag=happy,tag=team2] run team join happy @s
execute as @a[tag=happy,tag=team3] run team join happy @s
execute as @a[tag=happy,tag=team4] run team join happy @s
execute as @a[tag=happy,tag=team5] run team join happy @s
execute as @a[tag=happy,tag=team6] run team join happy @s
execute as @a[tag=happy,tag=team7] run team join happy @s
execute as @a[tag=happy,tag=team8] run team join happy @s
execute as @a[tag=happy,tag=team9] run team join happy @s
execute as @a[tag=happy,tag=team10] run team join happy @s
execute as @a[tag=happy,tag=team11] run team join happy @s
execute as @a[tag=happy,tag=team12] run team join happy @s
execute as @a[tag=happy,tag=team13] run team join happy @s
execute as @a[tag=happy,tag=team14] run team join happy @s
execute as @a[tag=happy,tag=team15] run team join happy @s
execute as @a[tag=happy,tag=team16] run team join happy @s
execute as @a[tag=happy,tag=team17] run team join happy @s
execute as @a[tag=happy,tag=team18] run team join happy @s