




$execute if score #PlayerX ER.sys matches 0..9 run \
    data modify storage minecraft:temp temp set value {$(posX) : "00$(x)"}
$execute if score #PlayerX ER.sys matches 0..99 run \
    data modify storage minecraft:temp temp set value {$(posX) : "0$(x)"}
$execute if score #PlayerX ER.sys matches 100.. run \
    data modify storage minecraft:temp temp set value {$(posX) : "$(x)"}

$execute if score #PlayerY ER.sys matches 0..9 run \
    data modify storage minecraft:temp temp merge value {$(posY) : "00$(y)"}
$execute if score #PlayerY ER.sys matches 0..99 run \
    data modify storage minecraft:temp temp merge value {$(posY) : "0$(y)"}
$execute if score #PlayerY ER.sys matches 100.. run \
    data modify storage minecraft:temp temp merge value {$(posY) : "$(y)"}