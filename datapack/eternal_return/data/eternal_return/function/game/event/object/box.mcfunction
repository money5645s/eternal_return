
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
execute if score day time matches 2 if score halfday time matches 0 if score tick time matches 600 run scoreboard players set msg skript 6
execute if score day time matches 2 if score halfday time matches 1 if score tick time matches 600 run scoreboard players set msg skript 6
execute if score day time matches 3 if score halfday time matches 0 if score tick time matches 600 run scoreboard players set msg skript 6
execute if score day time matches 3 if score halfday time matches 1 if score tick time matches 600 run scoreboard players set msg skript 6

# 초월
execute if score day time matches 4 if score halfday time matches 0 if score tick time matches 1 run function eternal_return:object/box/set_box {type:"mythic",count:"2"}
execute if score day time matches 4 if score halfday time matches 0 if score tick time matches 1 run scoreboard players set msg skript 7
