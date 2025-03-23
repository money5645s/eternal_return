## 현우 스킬 효과 (이동)
function eternal_return:character/list/hyunwoo/active/skill/skill_move

## 현우 스킬 효과 (맞은 애 같이 돌진)
execute if entity @s[tag=hyunwooskill] at @s run tag @e[tag=!hyunwooskill,distance=..1] add hyunwooskill1
execute as @e[tag=hyunwooskill1] at @s run tp @s @e[tag=hyunwooskill,limit=1,sort=nearest]

execute if entity @s[tag=hyunwooskill,scores={active_level=1}] at @s run damage @n[tag=hyunwooskill1,distance=..1,tag=!hyunwoo_active_dmg_cool] 7 cactus by @n[tag=hyunwoo]
execute if entity @s[tag=hyunwooskill,scores={active_level=2}] at @s run damage @n[tag=hyunwooskill1,distance=..1,tag=!hyunwoo_active_dmg_cool] 8 cactus by @n[tag=hyunwoo]
execute if entity @s[tag=hyunwooskill,scores={active_level=3}] at @s run damage @n[tag=hyunwooskill1,distance=..1,tag=!hyunwoo_active_dmg_cool] 9 cactus by @n[tag=hyunwoo]
execute if entity @s[tag=hyunwooskill,scores={active_level=4}] at @s run damage @n[tag=hyunwooskill1,distance=..1,tag=!hyunwoo_active_dmg_cool] 10 cactus by @n[tag=hyunwoo]
execute if entity @s[tag=hyunwooskill,scores={active_level=5}] at @s run damage @n[tag=hyunwooskill1,distance=..1,tag=!hyunwoo_active_dmg_cool] 12 cactus by @n[tag=hyunwoo]
execute if entity @s[tag=hyunwooskill] at @s run tag @e[tag=hyunwooskill1,distance=..1] add hyunwoo_active_dmg_cool
execute as @e[tag=hyunwoo_active_dmg_cool] at @s if entity @n[tag=hyunwoo,scores={hyunwooskill=12..}] run tag @s remove hyunwoo_active_dmg_cool

execute if entity @s[tag=hyunwooskill] at @s unless block ^ ^ ^1 air run tag @e[tag=hyunwooskill1,distance=..1] add stun
execute if entity @s[tag=hyunwooskill] at @s unless block ^ ^ ^1 air run tag @e[tag=hyunwooskill1,distance=..1] add hyunwoo_active_wall_dmg
execute as @e[tag=hyunwoo_active_wall_dmg] if entity @n[tag=hyunwoo,scores={active_level=1}] run damage @s 3 cactus by @n[tag=hyunwoo]
execute as @e[tag=hyunwoo_active_wall_dmg] if entity @n[tag=hyunwoo,scores={active_level=2}] run damage @s 4 cactus by @n[tag=hyunwoo]
execute as @e[tag=hyunwoo_active_wall_dmg] if entity @n[tag=hyunwoo,scores={active_level=3}] run damage @s 5 cactus by @n[tag=hyunwoo]
execute as @e[tag=hyunwoo_active_wall_dmg] if entity @n[tag=hyunwoo,scores={active_level=4}] run damage @s 6 cactus by @n[tag=hyunwoo]
execute as @e[tag=hyunwoo_active_wall_dmg] if entity @n[tag=hyunwoo,scores={active_level=5}] run damage @s 8 cactus by @n[tag=hyunwoo]
tag @s[tag=hyunwoo_active_wall_dmg] remove hyunwoo_active_wall_dmg

## 현우 스킬 맞은 애 효과 제거
execute if entity @s[tag=hyunwooskill] at @s unless block ^ ^ ^1 air run tag @s remove hyunwooskill1
execute if entity @s[scores={hyunwooskill=12..}] at @s run tag @e[distance=..1,tag=hyunwooskill1] remove hyunwooskill1

## 현우 스킬 효과 제거
function eternal_return:character/list/hyunwoo/active/skill/kill_marker_bush

execute if entity @s[tag=hyunwooskill] at @s unless block ^ ^ ^1 air unless block ^ ^ ^1 short_grass run scoreboard players reset @s hyunwooskill
execute if entity @s[tag=hyunwooskill] at @s unless block ^ ^ ^1 air unless block ^ ^ ^1 short_grass run tag @s remove hyunwooskill

function eternal_return:character/list/hyunwoo/active/skill/kill_marker_default

execute if entity @s[scores={hyunwooskill=12..}] run tag @s remove hyunwooskill
execute if entity @s[scores={hyunwooskill=12..}] run scoreboard players reset @s hyunwooskill