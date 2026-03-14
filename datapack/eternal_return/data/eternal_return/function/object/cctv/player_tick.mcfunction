


execute if entity @s[tag=!activing,tag=click_cctv] if entity @e[type=item_display,tag=cooltime,tag=cctv,distance=..2.5,limit=1] run playsound minecraft:er.ui.error_sound1 master @s ~ ~ ~ 1 1
execute if entity @s[tag=!activing,tag=click_cctv] unless entity @a[distance=..5,tag=activing] if entity @e[type=item_display,tag=!cooltime,tag=cctv,distance=..2.5,limit=1] run function eternal_return:object/cctv/click_cctv
tag @s remove click_cctv
# 플레이어가 클락하고 주변을 벗어났을 때
execute if entity @e[type=item_display,distance=2.5..7,limit=1] run tag @s[tag=activing] add stopload
execute if entity @e[type=item_display,distance=2.5..7,limit=1] run tag @s[tag=activing] remove activing


execute if score @s cctv_tick matches 0 run attribute @s minecraft:waypoint_receive_range base set 0
execute if score @s cctv_tick matches 1.. run attribute @s minecraft:waypoint_receive_range base set 40
execute if score @s cctv_tick matches 1.. run scoreboard players remove @s cctv_tick 1