    
# function eternal_return:entity/animal_spawn/remove_id
# function eternal_return:entity/animal_spawn/if_exist



$execute as @e[tag= ER.animal.root,\
              tag= this, distance= $(Radius)..] run function eternal_return:entity/animal_spawn/return/stop_anim
$data modify entity @e[tag= ER.animal.root  , \
                      tag= this, distance= $(Radius).., limit= 1] Pos set from storage minecraft:temp temp.animal[0].Pos
$data modify entity @e[tag= ER.animal.root  , \
                      tag= this, distance= $(Radius).., limit= 1] Rotation set from entity @s Rotation