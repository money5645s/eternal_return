

# 게임 시작전 타이머
execute if score game.start time matches 0 run function eternal_return:game/briefing_room
# 상단 타이머
execute if score day time matches ..6 if score game.start time matches 1 run function eternal_return:game/gamestart_tick
# 이벤트 타임라인

execute if score game.start time matches 1 run function eternal_return:game/event/object