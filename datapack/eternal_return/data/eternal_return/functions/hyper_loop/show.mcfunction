bossbar set minecraft:hud_1 visible true
bossbar set minecraft:hud_2 visible true
bossbar set minecraft:hud_3 visible true
bossbar set minecraft:hud_4 visible true
bossbar set minecraft:hud_5 visible true

bossbar set minecraft:hud_1 players @s
bossbar set minecraft:hud_2 players @s
bossbar set minecraft:hud_3 players @s
bossbar set minecraft:hud_4 players @s
bossbar set minecraft:hud_5 players @s


bossbar set minecraft:hud_5 name [\
    {"font":"minecraft:map","text":"\u3a16","color":"white"},\
    {"font":"minecraft:x_transform","text":"\u4060"},\
    {"font":"minecraft:map","text":"\u3a08","color":"white"},\
    {"font":"minecraft:x_transform","text":"\u4054"},\
    {"font":"minecraft:map","text":"\u3a09","color":"white"},\
    {"font":"minecraft:x_transform","text":"\u4034"},\
    {"font":"minecraft:map","text":"\u3a10","color":"white"},\
    {"font":"minecraft:x_transform","text":"\u4058"},\
    {"font":"minecraft:map","text":"\u3a11","color":"white"},\
    {"font":"minecraft:x_transform","text":"\u4027"},\
    {"font":"minecraft:map","text":"\u3a12","color":"white"},\
    {"font":"minecraft:x_transform","text":"\u4064"},\
    {"font":"minecraft:map","text":"\u3a13","color":"white"},\
    {"font":"minecraft:x_transform","text":"\u4036"},\
    {"font":"minecraft:map","text":"\u3a14","color":"white"},\
    {"font":"minecraft:x_transform","text":"\u4021"},\
    {"font":"minecraft:map","text":"\u3a15","color":"white"}\
    ]

#    {"font":"minecraft:player_icon","text":"0"}

execute run tp @s ~ ~ ~ 0 0
tag @s add choosing_hyper_loop









