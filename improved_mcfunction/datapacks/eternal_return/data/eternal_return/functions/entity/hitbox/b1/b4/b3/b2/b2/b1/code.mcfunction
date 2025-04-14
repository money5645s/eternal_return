$execute if score #HPSPS ER.sys matches 0 run data modify storage minecraft:temp temp.HPdata[$(i)] set value "-2"
$execute if score #HPSPL ER.sys matches 0 run data modify storage minecraft:temp temp.HPdata[$(i)] set value "-0"
$execute unless score #HPSPS ER.sys matches 0 run data modify storage minecraft:temp temp.HPdata[$(i)] set value "-1"
