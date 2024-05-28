# 플레이어 자동 넘버링
execute as @r unless score @s[tag=!player_id] player_id matches 0 run function eternal_return:system/player/numbering
# 권한이 없는 유저가 채팅창 명령어
execute as @a[scores={ChatCilck=1..}] at @s run function eternal_return:system/player/chatclick 