## 하이퍼루프 채팅창을 클릭했을 때
# 채팅 지우기
execute if entity @s[tag=!loop] run function df_library:window/resetchat
# 태그 부여
tag @s add loop

# 각 위치로 이동
execute if score @s CT1 matches 60 run function eternal_return:system/hyperloop/warp