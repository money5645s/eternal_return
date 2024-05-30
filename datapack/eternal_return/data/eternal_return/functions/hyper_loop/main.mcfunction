#
# function eternal_return:hyper_loop/main
#>   function eternal_return:hyper_loop/show_selected
#
#
#


execute at @s unless entity @s[tag=choosing_hyper_loop] run function eternal_return:hyper_loop/show

#>플레이어 회전값 구하기
data modify storage minecraft:temp temp.Rot set from entity @s Rotation
execute store result score #rotX ER.sys run data get storage minecraft:temp temp.Rot[0]

#>지도상에서 플레이어 위치 구하기 @s
data modify storage minecraft:temp temp.Pos set from entity @s Pos
execute store result score #rotY ER.sys run data get storage minecraft:temp temp.Rot[1]
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
execute store result storage minecraft:temp coord.x int 1 run scoreboard players get #PlayerX ER.sys
execute store result storage minecraft:temp coord.y int 1 run scoreboard players get #PlayerY ER.sys
data modify storage minecraft:temp coord.posX set value "PlayerX"
data modify storage minecraft:temp coord.posY set value "PlayerY"

#> 좌표 변환 : 98 -> 098 , 7 -> 007 이렇게
function eternal_return:hyper_loop/set_player_pos with storage minecraft:temp coord

title TDanfung actionbar [{"nbt":"temp","storage":"minecraft:temp"}]

data modify storage minecraft:temp temp merge value {\
beach:"white",hotel:"white",yanggung:"white",gas_station:"white",school:"white",fire:"white",golmok:"white",police:"white",jul:"white"}



#> 하이퍼루프 중 시야각 제어
#execute at @s if score #rotX ER.sys matches 90.. run tp @s ~ ~ ~ 0 ~
#execute at @s if score #rotX ER.sys matches ..-90 run tp @s ~ ~ ~ 0 ~



#> 골목
execute if score #rotX ER.sys matches -10..45 if score #rotY ER.sys matches -90..-30 run \ 
    data modify storage minecraft:temp temp.golmok set value "blue"

#> 주유소
execute if score #rotX ER.sys matches -30..-10 if score #rotY ER.sys matches -90..-30 run \ 
    data modify storage minecraft:temp temp.gas_station set value "blue"

#> 소방서
execute if score #rotX ER.sys matches -10..15 if score #rotY ER.sys matches -30..-10 run \ 
    data modify storage minecraft:temp temp.fire set value "blue"

#> 경찰서
execute if score #rotX ER.sys matches 15..45 if score #rotY ER.sys matches -30..-10 run \ 
    data modify storage minecraft:temp temp.police set value "blue"
#> 절
execute if score #rotX ER.sys matches 45..90 if score #rotY ER.sys matches -90..-10 run \ 
    data modify storage minecraft:temp temp.jul set value "blue"

#> 학교
execute if score #rotX ER.sys matches -30..-15 if score #rotY ER.sys matches -30..0 run \ 
    data modify storage minecraft:temp temp.school set value "blue"

#> 양궁장
execute if score #rotX ER.sys matches -45..-30 if score #rotY ER.sys matches -90..-10 run \ 
    data modify storage minecraft:temp temp.yanggung set value "blue"

#> 호텔
execute if score #rotX ER.sys matches -60..-45 if score #rotY ER.sys matches -90..0 run \ 
    data modify storage minecraft:temp temp.hotel set value "blue"

#> 모래사장
execute if score #rotX ER.sys matches -90..-60 if score #rotY ER.sys matches -60..60 run \ 
    data modify storage minecraft:temp temp.beach set value "blue"


function eternal_return:hyper_loop/show_selected with storage minecraft:temp temp



