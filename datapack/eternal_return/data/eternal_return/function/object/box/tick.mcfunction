## 상자
# 상위 함수 : function eternal_return:object/tick
# 입출력 없음

# 소환 스코어보드
    execute as @e[type=marker,tag=box_place,tag=selected_box] at @s run function eternal_return:object/box/summon_tick
# 빈 상자인 경우 삭제
    execute as @e[type=item_display,tag=box] at @s unless data block ~ ~ ~ LootTable unless data block ~ ~ ~ Items[0] run function eternal_return:object/box/is_empty_box
## 상자 소환 마커 이미지 회전
    execute as @e[type=item_display,tag=box_info_rotate] at @s run tp @s ~ ~ ~ ~5 ~