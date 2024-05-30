## 스코어보드 받기
# 태그 부여
tag @s add loading

# 로딩 태그가 있으면 uuid를 temp에 임시저장
execute store result storage player_data temp[2].uuid int 1 run data get entity @s[tag=loading] UUID[0]
# 시간 받아오기
$data modify storage player_data temp[2].sec set value $(sec)
function eternal_return:system/actionbar/cooltime/set_time with storage player_data temp[2]

tag @s remove loading