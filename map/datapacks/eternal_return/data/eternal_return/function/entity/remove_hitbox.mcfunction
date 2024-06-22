
#> function eternal_return:entity/remove_hitbox

execute if score @s ER.health matches ..0 on passengers if entity @s[tag=ER.animal.HPbar] run kill @s
execute if score @s ER.health matches ..0 run kill @s
