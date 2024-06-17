# 플레이어 자동 넘버링
execute unless score @s[tag=!player_id] player_id matches 0 run function eternal_return:system/player/numbering

# 은신
function eternal_return:system/player/hide
# 크래딧
function eternal_return:system/player/credit
# 사이드 바(로그)
function eternal_return:system/player/sidebar