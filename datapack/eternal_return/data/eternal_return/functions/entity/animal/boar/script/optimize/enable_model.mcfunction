

#모델을 다시 새로 소환
#> function eternal_return:entity/animal/boar/script/show_model


function animated_java:animal_boar/summon
execute as @e[  tag=aj.animal_boar.root] if score @s aj.id = .aj.last_id aj.id run tag @s add this
tag @e[tag= this, tag= aj.animal_boar.root] add ER.animal.model
tag @e[tag= this, tag= aj.animal_boar.root] add ER.animal.boar
tag @e[tag= this, tag= aj.animal_boar.root] add ER.animal
scoreboard players operation @e[tag= this, tag= aj.animal_boar.root] df_id = @s df_id
ride @e[tag=this,tag=ER.animal.model ,limit=1] mount @s
tag @s remove ER.optimized