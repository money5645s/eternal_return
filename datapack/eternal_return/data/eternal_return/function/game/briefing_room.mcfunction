

## 타이머
function eternal_return:game/timer/tick
# 플레이어 카운트
execute store result score player time if entity @e[tag=selected_character]
execute if score player time matches ..9 run function eternal_return:game/timer/init
execute if score player time matches 10.. run function eternal_return:game/left_timer/tick
execute if score player time matches 14.. if score left.sec time matches 11.. run scoreboard players set left.sec time 10


function eternal_return:game/left_timer/timer_bg
function eternal_return:game/left_timer/start
