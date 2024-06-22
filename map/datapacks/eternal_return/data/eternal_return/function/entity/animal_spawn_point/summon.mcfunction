
# 보어를 소환할 아머 마커를 소환하는 함수입니다.
#> function eternal_return:entity/animal_spawn/point/boar

$summon marker ~ ~ ~ {Tags:["ER","ER.spawn","ER.spawn.animal","ER.spawn.animal.$(animal)"],data:{animal:[]}}
$say $(animal) spawn point has been summoned!



