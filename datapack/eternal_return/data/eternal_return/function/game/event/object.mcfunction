# 운석
execute if score day time matches 1 if score halfday time matches 1 if score tick time matches 601 run function eternal_return:object/meteor/set
execute if score day time matches 2 if score halfday time matches 1 if score tick time matches 601 run function eternal_return:object/meteor/set
execute if score day time matches 3 if score halfday time matches 1 if score tick time matches 601 run function eternal_return:object/meteor/set
execute if score day time matches 1 if score halfday time matches 1 if score tick time matches 600 run scoreboard players set msg skript 3
execute if score day time matches 2 if score halfday time matches 1 if score tick time matches 600 run scoreboard players set msg skript 3
execute if score day time matches 3 if score halfday time matches 1 if score tick time matches 600 run scoreboard players set msg skript 3


# 생명의 나무
execute if score day time matches 1 if score halfday time matches 1 if score tick time matches 600 run function eternal_return:object/tree_of_life/set {day:2}
execute if score day time matches 2 if score halfday time matches 1 if score tick time matches 600 run function eternal_return:object/tree_of_life/set {day:3}
# 키오스크
execute if score day time matches 1 if score halfday time matches 1 if score tick time matches 1 run tag @e[tag=kiosk] add on
execute if score day time matches 1 if score halfday time matches 1 if score tick time matches 1 run scoreboard players set msg skript 8


# CCTV
execute if score day time matches 6 if score halfday time matches 0 if score tick time matches 1 run tag @e[tag=cctv] remove on


# 항공보급
# 음식
execute if score day time matches 1 if score halfday time matches 0 if score tick time matches 1 run function eternal_return:object/box/set_box {type:"food",count:"3"}
execute if score day time matches 1 if score halfday time matches 1 if score tick time matches 1 run function eternal_return:object/box/set_box {type:"food",count:"3"}
execute if score day time matches 2 if score halfday time matches 0 if score tick time matches 1 run function eternal_return:object/box/set_box {type:"food",count:"2"}
execute if score day time matches 2 if score halfday time matches 1 if score tick time matches 1 run function eternal_return:object/box/set_box {type:"food",count:"2"}

# 에픽
execute if score day time matches 2 if score halfday time matches 0 if score tick time matches 1 run function eternal_return:object/box/set_box {type:"epic",count:"2"}
execute if score day time matches 2 if score halfday time matches 1 if score tick time matches 1 run function eternal_return:object/box/set_box {type:"epic",count:"2"}
execute if score day time matches 3 if score halfday time matches 0 if score tick time matches 1 run function eternal_return:object/box/set_box {type:"epic",count:"2"}
execute if score day time matches 3 if score halfday time matches 1 if score tick time matches 1 run function eternal_return:object/box/set_box {type:"epic",count:"1"}
execute if score day time matches 2 if score halfday time matches 0 if score tick time matches 1 run scoreboard players set msg skript 7
execute if score day time matches 2 if score halfday time matches 1 if score tick time matches 1 run scoreboard players set msg skript 7
execute if score day time matches 3 if score halfday time matches 0 if score tick time matches 1 run scoreboard players set msg skript 7
execute if score day time matches 3 if score halfday time matches 1 if score tick time matches 1 run scoreboard players set msg skript 7

# 초월
execute if score day time matches 4 if score halfday time matches 0 if score tick time matches 1 run function eternal_return:object/box/set_box {type:"mythic",count:"2"}
execute if score day time matches 4 if score halfday time matches 0 if score tick time matches 1 run scoreboard players set msg skript 7


# 알파
execute if score day time matches 2 if score halfday time matches 0 if score tick time matches 1 run advancement grant @a only eternal_return:alpha
# 오메가
execute if score day time matches 3 if score halfday time matches 0 if score tick time matches 600 run advancement grant @a only eternal_return:omega_30
execute if score day time matches 3 if score halfday time matches 0 if score tick time matches 1 run advancement grant @a only eternal_return:omega

# 금구 알림
execute if score day time matches 2 if score halfday time matches 0 if score tick time matches 1 run scoreboard players set msg skript 10
execute if score day time matches 2 if score halfday time matches 1 if score tick time matches 1 run scoreboard players set msg skript 11
execute if score day time matches 3 if score halfday time matches 0 if score tick time matches 1 run scoreboard players set msg skript 12
execute if score day time matches 3 if score halfday time matches 1 if score tick time matches 1 run scoreboard players set msg skript 13
execute if score day time matches 4 if score halfday time matches 0 if score tick time matches 1 run scoreboard players set msg skript 14
execute if score day time matches 4 if score halfday time matches 1 if score tick time matches 1 run scoreboard players set msg skript 15
execute if score day time matches 5 if score halfday time matches 0 if score tick time matches 1 run scoreboard players set msg skript 16
execute if score day time matches 5 if score halfday time matches 1 if score tick time matches 1 run scoreboard players set msg skript 17
execute if score day time matches 6 if score halfday time matches 0 if score tick time matches 1 run scoreboard players set msg skript 18
