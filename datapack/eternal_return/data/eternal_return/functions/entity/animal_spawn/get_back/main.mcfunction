    
# function eternal_return:entity/animal_spawn/remove_id
# function eternal_return:entity/animal_spawn/if_exist



$execute as @e[tag= ER.animal.root,tag= this] if entity @s[distance= $(Radius)..] run function eternal_return:entity/animal_spawn/get_back/stop_anim

$data modify entity @e[tag= ER.animal.root, tag= this, distance= $(Radius).., limit= 1] Pos set from storage minecraft:temp temp.animal[0].Pos