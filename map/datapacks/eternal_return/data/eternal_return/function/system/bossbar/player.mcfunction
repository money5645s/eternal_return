## 보스바
# temp id 저장
function eternal_return:temp/input/id

## 기초 태그
# 보스바 추가
execute if entity @s[tag=add_bossbar] run function eternal_return:system/bossbar/add with storage player_data temp[0]
# 보스바 제거
execute if entity @s[tag=remove_bossbar] run function eternal_return:system/bossbar/remove with storage player_data temp[0]
# 보스바 나타내기
execute if entity @s[tag=show_bossbar] run function eternal_return:system/bossbar/show with storage player_data temp[0]
# 보스바 숨기기
execute if entity @s[tag=hide_bossbar] run function eternal_return:system/bossbar/hide with storage player_data temp[0]

## 표기 태그
execute if entity @s[tag=ingame_ui] run function eternal_return:system/bossbar/set/interface with storage player_data temp[0]
execute if entity @s[tag=wait_bar] run function eternal_return:system/bossbar/set/wait_bar with storage player_data temp[0]






## 태그 제거
tag @s[tag=add_bossbar] remove add_bossbar
tag @s[tag=remove_bossbar] remove remove_bossbar
tag @s[tag=show_bossbar] remove show_bossbar
tag @s[tag=hide_bossbar] remove hide_bossbar


# temp id 초기화
function eternal_return:temp/free/id