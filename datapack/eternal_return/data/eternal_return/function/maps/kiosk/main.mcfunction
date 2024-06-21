#
#> function eternal_return:maps/kiosk/main
# function eternal_return:maps/kiosk/get_selected_place

#> 하이퍼루프에 막 들어온 경우 choosing_kiosk 태그 부여
execute as @s store result storage minecraft:temp temp.id int 1 run scoreboard players get @s player_id

#> FOR debug
#tellraw @a [{"nbt":"temp","storage":"minecraft:temp"}]

execute at @s if entity @s[tag= !choosing_kiosk] run function eternal_return:maps/kiosk/show with storage minecraft:temp temp

#> 플레이어 커서 위치에 따른 버튼 감지 함수
function eternal_return:maps/kiosk/get_selected_place with storage minecraft:temp temp

#> 플레이어의 스코어가 위치를 지시하는 스코어와 같은 경우 :




data remove storage minecraft:temp temp