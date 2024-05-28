#기본 hs 발사
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim] unless score @s shift matches 1.. at @s run summon marker ^ ^1.5 ^0 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happynormalshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim] unless score @s shift matches 1.. at @s run summon marker ^ ^1.5 ^1 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happynormalshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim] unless score @s shift matches 1.. at @s run summon marker ^ ^1.5 ^2 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happynormalshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim] unless score @s shift matches 1.. at @s run summon marker ^ ^1.5 ^3 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happynormalshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim] unless score @s shift matches 1.. at @s run summon marker ^ ^1.5 ^4 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happynormalshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim] unless score @s shift matches 1.. at @s run summon marker ^ ^1.5 ^5 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happynormalshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim] unless score @s shift matches 1.. at @s run summon marker ^ ^1.5 ^6 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happynormalshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim] unless score @s shift matches 1.. at @s run summon marker ^ ^1.5 ^7 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happynormalshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim] unless score @s shift matches 1.. at @s run summon marker ^ ^1.5 ^8 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happynormalshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim] unless score @s shift matches 1.. at @s run summon marker ^ ^1.5 ^9 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happynormalshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim] unless score @s shift matches 1.. at @s run summon marker ^ ^1.5 ^10 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happynormalshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim] unless score @s shift matches 1.. at @s run summon marker ^ ^1.5 ^11 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happynormalshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim] unless score @s shift matches 1.. at @s run summon marker ^ ^1.5 ^12 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happynormalshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=!happyaim] unless score @s shift matches 1.. at @s run scoreboard players remove @s happybullet 1

#기본 hs 딜, 파티클
execute as @e[tag=happynormalshot] at @s run tag @e[tag=!happy,tag=!happynormalshot,distance=..1.5,limit=1,sort=nearest] add happynormalhit
damage @e[tag=happynormalhit,limit=1] 3 thrown by @a[tag=happy,limit=1,sort=nearest]
tag @e[tag=happynormalhit] remove happynormalhit
execute as @e[tag=happynormalshot] at @s run particle dust{color:[0.251,0.800,1.000],scale:1} ~ ~ ~ 0 0 0 0.03 15 normal

#총알 갯수 표기
execute as @a[tag=happy,scores={happybullet=6}] run xp set @s 6 levels
execute as @a[tag=happy,scores={happybullet=5}] run xp set @s 5 levels
execute as @a[tag=happy,scores={happybullet=4}] run xp set @s 4 levels
execute as @a[tag=happy,scores={happybullet=3}] run xp set @s 3 levels
execute as @a[tag=happy,scores={happybullet=2}] run xp set @s 2 levels
execute as @a[tag=happy,scores={happybullet=1}] run xp set @s 1 levels
execute as @a[tag=happy,scores={happybullet=0}] run xp set @s 0 levels
execute as @a[tag=happy,scores={happybullet=6..}] run scoreboard players set @s happybullet 6
execute as @a[tag=happy,scores={happybullet=1..}] run tag @s add happybullet
execute as @a[tag=happy,scores={happybullet=0}] run tag @s remove happybullet

#총알 장전
execute as @a[tag=happy,scores={happyright=1..,shift=1..}] unless score @s happybullet matches 6.. run tag @s add happyreload

execute as @a[tag=happy,tag=happyreload] run scoreboard players add @s happyreload 1
execute as @a[tag=happy,tag=happyreload] run effect clear @s slowness
execute as @a[tag=happy,tag=happyreload] run tag @s remove happyaim
execute as @a[tag=happy,tag=happyreload,scores={happyreload=4}] run scoreboard players add @s happybullet 1
execute as @a[tag=happy,tag=happyreload,scores={happyreload=4}] run scoreboard players set @s happyreload 0
execute as @a[tag=happy,tag=happyreload,scores={happybullet=6..}] run tag @s remove happyreload
execute as @a[tag=happy,tag=happyreload,scores={shift=0}] run tag @s remove happyreload
execute as @a[tag=happy,tag=happyreload] at @s run particle smoke ~ ~0.75 ~ 0.1 0.1 0.1 0.03 15 normal
execute as @a[tag=happy] unless entity @s[tag=happyreload] run scoreboard players reset @s happyreload
execute as @a[scores={shift=1..}] run scoreboard players set @s shift 0

#정조준 hs 발사
execute as @a[tag=happy,tag=!happyaim,nbt={Inventory:[{id:"minecraft:carrot_on_a_stick",Slot:-106b}]}] run tag @s add happyaim1
execute as @a[tag=happy,tag=!happyaim,nbt={Inventory:[{id:"minecraft:carrot_on_a_stick",Slot:-106b}]}] run item replace entity @s weapon.mainhand with carrot_on_a_stick
execute as @a[tag=happy,tag=!happyaim,nbt={Inventory:[{id:"minecraft:carrot_on_a_stick",Slot:-106b}]}] run item replace entity @s weapon.offhand with air
execute as @a[tag=happy,tag=happyaim] run effect give @s slowness 1 100 true
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^0 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^1 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^2 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^3 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^4 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^5 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^6 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^7 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^8 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^9 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^10 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^11 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^12 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^13 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^14 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^15 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^16 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^17 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^18 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^19 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^20 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^21 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^22 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^23 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run summon marker ^ ^1.5 ^24 {NoGravity:1b,Silent:1b,Invulnerable:1b,Tags:["happyaimshot"]}

#정조준 hs 딜, 파티클
execute as @e[tag=happyaimshot] at @s run tag @e[tag=!happy,tag=!happyaimshot,distance=..1.5,limit=1,sort=nearest] add happyaimhit
damage @e[tag=happyaimhit,limit=1] 7 thrown by @a[tag=happy,limit=1,sort=nearest]
effect give @e[tag=happyaimhit] levitation 1 1 true
tag @e[tag=happyaimhit] remove happyaimhit
execute as @e[tag=happyaimshot] at @s run particle dust{color:[1.000,0.682,0.129],scale:1} ~ ~ ~ 0 0 0 0.03 15 normal

#정조준 hs 발사 시간 간격
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. at @s run scoreboard players remove @s happybullet 1
execute as @a[tag=happy,scores={happyright=1..},tag=happybullet,tag=happyaim] unless score @s shift matches 1.. unless score @s happyaimc matches 1.. run scoreboard players add @s happyaimc 1
execute as @a[tag=happy,tag=!happyaim,tag=happyaim1] run tag @s add happyaim
execute as @a[tag=happy,tag=happyaim1] run tag @s remove happyaim1

execute as @a[tag=happy,scores={happyaimc=1..}] run scoreboard players add @s happyaimc 1
execute as @a[tag=happy,scores={happyaimc=20..}] run scoreboard players reset @s happyaimc

#정조준 해제
execute as @a[tag=happy,tag=happyaim,nbt={Inventory:[{id:"minecraft:carrot_on_a_stick",Slot:-106b}]}] run tag @s add happyaim1
execute as @a[tag=happy,tag=happyaim,nbt={Inventory:[{id:"minecraft:carrot_on_a_stick",Slot:-106b}]}] run item replace entity @s weapon.mainhand with carrot_on_a_stick
execute as @a[tag=happy,tag=happyaim,nbt={Inventory:[{id:"minecraft:carrot_on_a_stick",Slot:-106b}]}] run item replace entity @s weapon.offhand with air
execute as @a[tag=happy,tag=happyaim,tag=happyaim1] run effect clear @s slowness
execute as @a[tag=happy,tag=happyaim,tag=happyaim1] run tag @s remove happyaim
execute as @a[tag=happy,tag=happyaim1] run tag @s remove happyaim1
execute as @a[tag=happy] unless entity @s[tag=happyaim] run scoreboard players reset @s happyaimc



#발사체 제거
execute as @a[tag=happy,scores={happyright=1..}] at @s run kill @e[tag=happynormalshot]
execute as @a[tag=happy,scores={happyright=1..}] at @s run kill @e[tag=happyaimshot]
execute as @a[tag=happy,scores={happyright=1..}] run scoreboard players reset @s happyright


#해피 태그 제거
execute as @a[tag=player] unless entity @s[tag=happy] run tag @s remove happybullet
execute as @a[tag=player] unless entity @s[tag=happy] run tag @s remove happyaim1
execute as @a[tag=player] unless entity @s[tag=happy] run tag @s remove happyaim
execute as @a[tag=player] unless entity @s[tag=happy] run tag @s remove happyreload
execute as @a[tag=player] unless entity @s[tag=happy] run scoreboard players reset @s happyaimc
execute as @a[tag=player] unless entity @s[tag=happy] run scoreboard players reset @s happyright
execute as @a[tag=player] unless entity @s[tag=happy] run scoreboard players reset @s happybullet
execute as @a[tag=player] unless entity @s[tag=happy] run scoreboard players reset @s happyreload