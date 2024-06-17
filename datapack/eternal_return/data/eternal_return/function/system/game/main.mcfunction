##> 게임 타임라인 별 실행 함수

#execute if score #timer.sec CT1 matches ..9 run title Money5645 actionbar [{"score":{"name":"#timer.day","objective":"CT1"},"font":"sidebar/up"},{"text":"일차 ","color":"gold"},"  /  ",{"text":"낮밤 ","color":"gold"},{"score":{"name":"#timer.halfday","objective":"CT1"}},"  /  ",{"text":"시간 ","color":"gold"},"0",{"score":{"name":"#timer.min","objective":"CT1"}},":0",{"score":{"name":"#timer.sec","objective":"CT1"}}]
#execute if score #timer.sec CT1 matches 10.. run title Money5645 actionbar [{"score":{"name":"#timer.day","objective":"CT1"},"font":"sidebar/up"},{"text":"일차 ","color":"gold"},"  /  ",{"text":"낮밤 ","color":"gold"},{"score":{"name":"#timer.halfday","objective":"CT1"}},"  /  ",{"text":"시간 ","color":"gold"},"0",{"score":{"name":"#timer.min","objective":"CT1"}},":",{"score":{"name":"#timer.sec","objective":"CT1"}}]

# 타이머
function eternal_return:system/game/timer
