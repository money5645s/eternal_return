## 스코어보드 생성
scoreboard objectives add Cradit dummy
scoreboard objectives add ChatClick trigger
scoreboard objectives add shift minecraft.custom:minecraft.sneak_time
scoreboard objectives add hp health {"text":"♥","color":"red"}

# 쿨타임
scoreboard objectives add CT1 dummy
scoreboard objectives add CT2 dummy
scoreboard objectives add CT3 dummy
scoreboard objectives add SC dummy

# 정수
scoreboard objectives add NUM dummy

# 플레이어 기본 설정
function eternal_return:system/player/init
# 보스바 기본 설정
function eternal_return:system/bossbar/initfunction eternal_return:system/bossbar/init
# 액션바 기본 설정
function eternal_return:system/actionbar/init