
# 보어를 소환할 아머 마커를 소환하는 함수입니다.
#> function eternal_return:entity/animal_spawn/point/boar

$summon marker ~ ~ ~ {Tags:["this","ER","ER.spawn","ER.spawn.animal","ER.spawn.animal.$(animal)"],data:{animal:[]}}
$say $(animal) spawn point has been summoned!
scoreboard players set @e[type=minecraft:marker,tag=this,limit=1] ER.sys 0
scoreboard players set @e[type=minecraft:marker,tag=this,limit=1] ER.cooltime 0
tag @e[tag=this] remove this


