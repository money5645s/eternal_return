scoreboard players set #i ER.sys 0
data modify storage minecraft:temp temp.HPdata set value []
function eternal_return:entity/hitbox/hitbox/hitbox/hitbox/1 with storage minecraft:temp temp
$data modify entity @s text set value '[{"text":"["},{"text":"$(HP)"},{"text":"]\\n"},{"text":"10"},{"translate":"space.2","font":"minecraft:default"},{"storage":"minecraft:temp","nbt":"temp.HPdata","interpret":true,"font":"minecraft:bar"}]'
