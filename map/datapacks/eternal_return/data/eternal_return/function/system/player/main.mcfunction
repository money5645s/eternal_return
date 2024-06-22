# 플레이어 자동 넘버링
execute unless score @s[tag=!player_id] player_id matches 0 run function eternal_return:system/player/numbering

# 은신
function eternal_return:system/player/hide
# 부시
function eternal_return:system/player/bush

# 크래딧
function eternal_return:system/player/credit
# 사이드 바(로그)
function eternal_return:system/player/sidebar


# 게임에서 나갔을 때
function eternal_return:system/player/leave_game
# 포화 무한 
effect give @s saturation infinite 10 true
# 기본 도전과제 끄기
function eternal_return:system/advencement/revoke_all