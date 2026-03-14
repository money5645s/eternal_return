# 운석 충돌

execute if score @e[tag=selected,limit=1] ct1 matches 595 run advancement revoke @s only eternal_return:meteor
execute if score @e[tag=selected,limit=1] ct1 matches 595 run advancement grant @s only eternal_return:meteor
# 운석 클릭
execute if entity @s[tag=click_meteorite,tag=!colleting_meteorite] if entity @n[type=interaction,tag=meteorite,limit=1,distance=..2] run tag @s add show_meteorite_loading
# 반 채굴 후 이탈
execute if entity @n[tag=meteorite,type=interaction,distance=..2] run tag @s[tag=pauseload,tag=colleting_meteorite,tag=done.half.loading,tag=click_meteorite] remove pauseload 
execute if entity @n[tag=meteorite,type=interaction,distance=2..11] run tag @s[tag=colleting_meteorite,tag=done.half.loading] add pauseload
# 반 채굴 전 이탈
execute if entity @n[tag=meteorite,type=interaction,distance=2..11] run tag @s[tag=colleting_meteorite,tag=!done.half.loading] add stopload
execute if entity @n[tag=meteorite,type=interaction,distance=2..11] run playsound minecraft:er.ui.error_sound1 master @s[tag=colleting_meteorite,tag=!done.half.loading] ~ ~ ~ 1
execute if entity @n[tag=meteorite,type=interaction,distance=2..11] run tag @s[tag=colleting_meteorite,tag=!done.half.loading] remove colleting_meteorite
# 완전히 멀리 갔을때
execute if entity @s[tag=colleting_meteorite,tag=done.half.loading] if entity @n[tag=meteorite,type=interaction,distance=11..15] run tag @s add stopload
execute if entity @s[tag=colleting_meteorite,tag=done.half.loading] if entity @n[tag=meteorite,type=interaction,distance=11..15] run playsound minecraft:er.ui.error_sound1 master @s ~ ~ ~ 1
execute if entity @s[tag=colleting_meteorite,tag=done.half.loading] if entity @n[tag=meteorite,type=interaction,distance=11..15] run tag @s remove colleting_meteorite

# 클릭 태그 제거
tag @s remove click_meteorite

