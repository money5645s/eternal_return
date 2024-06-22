## 게임에서 나갔을 때
# temp id 불러오기
execute if score @s quit matches 1.. run function eternal_return:temp/input/id
# 보스바 재표시
execute if score @s[tag=ingame_ui] quit matches 1.. run function eternal_return:system/bossbar/remove with storage player_data temp[0]
execute if score @s[tag=ingame_ui] quit matches 1.. run tag @s add add_bossbar
# 스코어 초기화
execute if score @s quit matches 1.. run scoreboard players reset @s quit
# temp id 초기화
execute if score @s quit matches 1.. run function eternal_return:temp/free/id