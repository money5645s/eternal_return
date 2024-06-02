#
# function eternal_return:maps/hyper_loop/main
#>   function eternal_return:maps/hyper_loop/show_selected
#


execute at @s unless entity @s[tag=choosing_hyper_loop] run function eternal_return:maps/hyper_loop/show


#>플레이어 회전값 구하기
data modify storage minecraft:temp temp.Rot set from entity @s Rotation
execute store result score #rotX ER.sys run data get storage minecraft:temp temp.Rot[0]
execute store result score #rotY ER.sys run data get storage minecraft:temp temp.Rot[1]

#>지도상에서 플레이어 위치 구하기 @s
data modify storage minecraft:temp temp.Pos set from entity @s Pos
execute store result score #PlayerX ER.sys run data get storage minecraft:temp temp.Pos[0]
execute store result score #PlayerY ER.sys run data get storage minecraft:temp temp.Pos[2]
data remove storage minecraft:temp temp.Rot
data remove storage minecraft:temp temp.Pos
scoreboard players operation #PlayerX ER.sys -= ER.centorX ER.debug
scoreboard players operation #PlayerY ER.sys -= ER.centorY ER.debug
scoreboard players add #PlayerX ER.sys 100
scoreboard players add #PlayerY ER.sys 100


#> for debug
scoreboard players operation PlayerX ER.debug = #PlayerX ER.sys
scoreboard players operation PlayerY ER.debug = #PlayerY ER.sys
#say hi

#> {posX : x, posY : y} -> {PlayerX : 스코어보드(#PlayerX ER.sys), PlayerY : 스코어보드(#PlayerX ER.sys)}
#> 좌표 변환 : 98 -> 098 , 7 -> 007 이렇게
execute store result storage minecraft:temp coord.x int 1 run scoreboard players get #PlayerX ER.sys
execute store result storage minecraft:temp coord.y int 1 run scoreboard players get #PlayerY ER.sys
data modify storage minecraft:temp coord.scoreboardX set value "#PlayerX"
data modify storage minecraft:temp coord.scoreboardY set value "#PlayerY"
data modify storage minecraft:temp coord.posX set value "PlayerX"
data modify storage minecraft:temp coord.posY set value "PlayerY"
function eternal_return:maps/hyper_loop/set_player_pos with storage minecraft:temp coord
data remove storage minecraft:temp coord

#> {posX : x, posY : y} -> {mouseX : 스코어보드(#rotX ER.sys), mouseY : 스코어보드(#rotY ER.sys)}
#> 좌표 변환 : 98 -> 098 , 7 -> 007 이렇게

scoreboard players set #temp ER.sys -2
scoreboard players operation #rotX ER.sys *= #temp ER.sys
scoreboard players set #temp ER.sys 2
scoreboard players operation #rotY ER.sys *= #temp ER.sys
scoreboard players add #rotX ER.sys 130
scoreboard players add #rotY ER.sys 154

execute if score #rotX ER.sys matches 226.. run scoreboard players set #rotX ER.sys 226
execute if score #rotY ER.sys matches 256.. run scoreboard players set #rotY ER.sys 256


execute store result storage minecraft:temp coord.x int 1 run scoreboard players get #rotX ER.sys
execute store result storage minecraft:temp coord.y int 1 run scoreboard players get #rotY ER.sys
data modify storage minecraft:temp coord.scoreboardX set value "#rotX"
data modify storage minecraft:temp coord.scoreboardY set value "#rotY"
data modify storage minecraft:temp coord.posX set value "mouseX"
data modify storage minecraft:temp coord.posY set value "mouseY"
function eternal_return:maps/hyper_loop/set_player_pos with storage minecraft:temp coord
data remove storage minecraft:temp coord


data modify storage minecraft:temp temp merge value {\
beach:"white",hotel:"white",yanggung:"white",gas_station:"white",school:"white",fire:"white",golmok:"white",police:"white",jul:"white",lab:"white",forest:"white",graveyard:"white",pond:"white",stream:"white",hospital:"white",factory:"white",cathedral:"white",port:"white",village:"white",storage:"white"}

#> 하이퍼루프 중 시야각 제어
#execute at @s if score #rotX ER.sys matches 90.. run tp @s ~ ~ ~ 0 ~
#execute at @s if score #rotX ER.sys matches ..-90 run tp @s ~ ~ ~ 0 ~


#> 모래사장
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/beach run \
    data modify storage minecraft:temp temp.beach set value "blue"

##> 주유소
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/gas_station run \
    data modify storage minecraft:temp temp.gas_station set value "blue"

##> 골목
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/golmok run \
    data modify storage minecraft:temp temp.golmok set value "blue"

#> 양궁장
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/yanggung run \
    data modify storage minecraft:temp temp.yanggung set value "blue"

#> 학교
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/school run \
    data modify storage minecraft:temp temp.school set value "blue"

#> 호텔
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/hotel run \
    data modify storage minecraft:temp temp.hotel set value "blue"

#> 소방서
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/fire run \
    data modify storage minecraft:temp temp.fire set value "blue"

#> 경찰서
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/police run \
    data modify storage minecraft:temp temp.police set value "blue"

#> 절
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/jul run \
    data modify storage minecraft:temp temp.jul set value "blue"

#> 숲
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/forest run \
    data modify storage minecraft:temp temp.forest set value "blue"

#> 연못
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/pond run \
    data modify storage minecraft:temp temp.pond set value "blue"

#> 개울
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/stream run \
    data modify storage minecraft:temp temp.stream set value "blue"

#> 묘지
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/graveyard run \
    data modify storage minecraft:temp temp.graveyard set value "blue"

#> 병원
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/hospital run \
    data modify storage minecraft:temp temp.hospital set value "blue"

#> 고급 주택가
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/village run \
    data modify storage minecraft:temp temp.village set value "blue"

#> 성당
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/cathedral run \
    data modify storage minecraft:temp temp.cathedral set value "blue"

#> 창고
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/storage run \
    data modify storage minecraft:temp temp.storage set value "blue"

#> 항구
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/port run \
    data modify storage minecraft:temp temp.port set value "blue"

#> 공장
execute if score #cnt ER.sys matches 0 if function eternal_return:maps/hyper_loop/cursor_in_place/factory run \
    data modify storage minecraft:temp temp.factory set value "blue"

scoreboard players set #cnt ER.sys 0

#title TDanfung actionbar [{"nbt":"Rotation","entity":"@s"}]

function eternal_return:maps/hyper_loop/show_selected with storage minecraft:temp temp
title TDanfung actionbar [{"nbt":"temp.mouseX","storage":"minecraft:temp"},{"text":"  "},{"nbt":"temp.mouseY","storage":"minecraft:temp"}]


data remove storage minecraft:temp temp
