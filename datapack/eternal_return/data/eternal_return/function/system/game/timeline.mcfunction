##> 시간별 함수 실행

## 빈 도전과제 보이기
# 1일차 밤
#execute if score #timer.day CT1 matches 1 if score #timer.halfday CT1 matches 2 if score #timer.sec CT1 matches 59 if score #timer.tick CT1 matches 20 run function eternal_return:system/advencement/empty {count:3}
# 2일차 낮
#execute if score #timer.day CT1 matches 1 if score #timer.halfday CT1 matches 2 if score #timer.sec CT1 matches 59 if score #timer.tick CT1 matches 20 run function eternal_return:system/advencement/empty 


## 시스템
# 알파
execute if score #timer.day CT1 matches 2 \
    if score #timer.halfday CT1 matches 1 \
    if score #timer.sec CT1 matches 30 \
    if score #timer.tick CT1 matches 19 \
        run function eternal_return:send_msg {target:"@a",text:'{"text":"알파가 [위치] 지역에 30초 후 출현합니다."}',color:"gold",timer:1}
execute if score #timer.day CT1 matches 2 \
    if score #timer.halfday CT1 matches 2 \
    if score #timer.sec CT1 matches 59 \
    if score #timer.tick CT1 matches 19 \
        run function eternal_return:send_msg {target:"@a",text:'{"text":"알파가 [위치]에 등장했습니다!"}',color:"gold",timer:1}

execute if score #timer.day CT1 matches 2 \
    if score #timer.halfday CT1 matches 1 \
    if score #timer.sec CT1 matches 30 \
    if score #timer.tick CT1 matches 19 \
        run advancement grant @a only eternal_return:alpha_30
execute if score #timer.day CT1 matches 2 \
    if score #timer.halfday CT1 matches 2 \
    if score #timer.sec CT1 matches 59 \
    if score #timer.tick CT1 matches 19 \
        run advancement grant @a only eternal_return:alpha
# 오메가
execute if score #timer.day CT1 matches 3 \
    if score #timer.halfday CT1 matches 1 \
    if score #timer.sec CT1 matches 30 \
    if score #timer.tick CT1 matches 19 \
        run function eternal_return:send_msg {target:"@a",text:'{"text":"오메가가 [위치] 지역에 30초 후 출현합니다."}',color:"gold",timer:1}
execute if score #timer.day CT1 matches 3 \
    if score #timer.halfday CT1 matches 2 \
    if score #timer.sec CT1 matches 59 \
    if score #timer.tick CT1 matches 19 \
        run function eternal_return:send_msg {target:"@a",text:'{"text":"오메가가 [위치]에 등장했습니다!"}',color:"gold",timer:1}

execute if score #timer.day CT1 matches 3 \
    if score #timer.halfday CT1 matches 1 \
    if score #timer.sec CT1 matches 30 \
    if score #timer.tick CT1 matches 19 \
        run advancement grant @a only eternal_return:omega_30
execute if score #timer.day CT1 matches 3 \
    if score #timer.halfday CT1 matches 2 \
    if score #timer.sec CT1 matches 59 \
    if score #timer.tick CT1 matches 19 \
        run advancement grant @a only eternal_return:omega
# 위클라인
execute if score #timer.day CT1 matches 4 \
    if score #timer.halfday CT1 matches 1 \
    if score #timer.sec CT1 matches 30 \
    if score #timer.tick CT1 matches 19 \
        run function eternal_return:send_msg {target:"@a",text:'{"text":"30초 후, 위클라인 박사가 등장합니다. 조심하세요!"}',color:"gold",timer:1}
execute if score #timer.day CT1 matches 4 \
    if score #timer.halfday CT1 matches 2 \
    if score #timer.sec CT1 matches 59 \
    if score #timer.tick CT1 matches 19 \
        run function eternal_return:send_msg {target:"@a",text:'{"text":"위클라인 박사가 [위치]에서 등장했습니다."}',color:"gold",timer:1}

execute if score #timer.day CT1 matches 4 \
    if score #timer.halfday CT1 matches 1 \
    if score #timer.sec CT1 matches 30 \
    if score #timer.tick CT1 matches 19 \
        run advancement grant @a only eternal_return:wickeline_30
execute if score #timer.day CT1 matches 4 \
    if score #timer.halfday CT1 matches 2 \
    if score #timer.sec CT1 matches 59 \
    if score #timer.tick CT1 matches 19 \
        run advancement grant @a only eternal_return:wickeline
# 생명의나무
execute if score #timer.day CT1 matches 1 \
    if score #timer.halfday CT1 matches 2 \
    if score #timer.sec CT1 matches 30 \
    if score #timer.tick CT1 matches 19 \
        run function eternal_return:system/tree_of_life/set {day:2}
execute if score #timer.day CT1 matches 2 \
    if score #timer.halfday CT1 matches 2 \
    if score #timer.sec CT1 matches 30 \
    if score #timer.tick CT1 matches 19 \
        run function eternal_return:system/tree_of_life/set {day:3}
# 운석
execute if score #timer.day CT1 matches 1..3 \
    if score #timer.halfday CT1 matches 2 \
    if score #timer.sec CT1 matches 30 \
    if score #timer.tick CT1 matches 19 \
        run function eternal_return:system/meteor/set

# 전장
execute if score #timer.day CT1 matches 2 \
    if score #timer.halfday CT1 matches 1 \
    if score #timer.sec CT1 matches 30 \
    if score #timer.tick CT1 matches 19 \
        run advancement grant @a only eternal_return:battle_zone_30
execute if score #timer.day CT1 matches 2 \
    if score #timer.halfday CT1 matches 2 \
    if score #timer.sec CT1 matches 59 \
    if score #timer.tick CT1 matches 19 \
        run advancement grant @a only eternal_return:battle_zone
# 키오스크
# 
# 금지구역

