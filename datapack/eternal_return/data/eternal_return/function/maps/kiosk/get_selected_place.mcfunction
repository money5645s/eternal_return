#
# function eternal_return:maps/kiosk/main
#> function eternal_return:maps/kiosk/get_selected_place
# function eternal_return:maps/kiosk/show_selected
# function eternal_return:maps/kiosk/cursor_in_place/..
# function eternal_return:maps/kiosk/select_place




#>플레이어 버튼 위치 상수값 세팅
scoreboard players operation @s ER.sys = #ER.place.null ER.sys

#>플레이어 회전값 구하기
data modify storage minecraft:temp temp.Rot set from entity @s Rotation
execute store result score #rotX ER.sys run data get storage minecraft:temp temp.Rot[0]
execute store result score #rotY ER.sys run data get storage minecraft:temp temp.Rot[1]

#> {posX : x, posY : y} -> {mouseX : 스코어보드(#rotX ER.sys), mouseY : 스코어보드(#rotY ER.sys)}
#> 좌표 변환 : 98 -> 098 , 7 -> 007 이렇게

scoreboard players set #temp ER.sys -3
scoreboard players operation #rotX ER.sys *= #temp ER.sys
scoreboard players set #temp ER.sys 3
scoreboard players operation #rotY ER.sys *= #temp ER.sys
scoreboard players add #rotX ER.sys 200
scoreboard players add #rotY ER.sys 154

execute if score #rotX ER.sys matches 550.. run scoreboard players set #rotX ER.sys 550
execute if score #rotX ER.sys matches ..0 run scoreboard players set #rotX ER.sys 0
execute if score #rotY ER.sys matches 256.. run scoreboard players set #rotY ER.sys 256


execute store result storage minecraft:temp coord.x int 1 run scoreboard players get #rotX ER.sys
execute store result storage minecraft:temp coord.y int 1 run scoreboard players get #rotY ER.sys
data modify storage minecraft:temp coord.scoreboardX set value "#rotX"
data modify storage minecraft:temp coord.scoreboardY set value "#rotY"
data modify storage minecraft:temp coord.posX set value "mouseX"
data modify storage minecraft:temp coord.posY set value "mouseY"
function eternal_return:maps/kiosk/set_player_pos with storage minecraft:temp coord
data remove storage minecraft:temp coord


$data modify storage minecraft:temp temp merge value {id : $(id), beach:"white",hotel:"white",yanggung:"white",gas_station:"white",school:"white",fire:"white",golmok:"white",police:"white",jul:"white",lab:"white",forest:"white",graveyard:"white",pond:"white",stream:"white",hospital:"white",factory:"white",cathedral:"white",port:"white",village:"white",storage:"white"}

#> 하이퍼루프 중 시야각 제어
#execute at @s if score #rotX ER.sys matches 90.. run tp @s ~ ~ ~ 0 ~
#execute at @s if score #rotX ER.sys matches ..-90 run tp @s ~ ~ ~ 0 ~


##> 공장
#execute if score #cnt ER.sys matches 0 if function eternal_return:maps/kiosk/cursor_in_place/factory run \
#    function eternal_return:maps/kiosk/select_place {place:"factory",color:"blue"}
#

title @a actionbar [{"storage":"minecraft:temp","nbt":"temp.mouseX"},{"text":"  "},{"storage":"minecraft:temp","nbt":"temp.mouseY"}]

#scoreboard players set #cnt ER.sys 0
function eternal_return:maps/kiosk/show_selected with storage minecraft:temp temp
data remove storage minecraft:temp temp
