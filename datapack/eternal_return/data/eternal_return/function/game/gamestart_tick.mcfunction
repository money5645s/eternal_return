# --- [기본 타이머 로직] ---
# 1. 틱 증가
execute if score tick time matches 1.. run scoreboard players remove tick time 1

scoreboard players operation sec time = tick time
scoreboard players operation min time = tick time

scoreboard players operation sec time /= #20 CONST
scoreboard players operation sec time %= #60 CONST

scoreboard players operation min time /= #20 CONST
scoreboard players operation min time /= #60 CONST

# 1. 밤(0)이 끝났을 때 -> 날짜(day)를 1 올리고 낮(1)으로 변경
# "밤에서 낮이 될 때 하루가 바뀐다"는 조건을 충족합니다.
execute if score tick time matches 0 run scoreboard players add halfday time 1
execute if score tick time matches 0 if score halfday time matches 2 run scoreboard players add day time 1

execute if score tick time matches 0 if score halfday time matches 1 run time set midnight
execute if score tick time matches 0 if score halfday time matches 2 run time set day

execute if score tick time matches 0 if score halfday time matches 2 run scoreboard players set halfday time 0


execute if score tick time matches 0 run function eternal_return:game/timer/get_tick_each_day