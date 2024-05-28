
# function eternal_return:entity/animal_spawn/detect_entity
# function eternal_return:entity/animal_spawn/find_root

#> ER.animal.root ER.animal.hitbox 에 this 추가
execute as @e[tag= ER.animal.hitbox] if score @s df_id = #this.id ER.sys run tag @s add this
tag @s add this

#> 현재 야생동물의 최대체력 #this.MaxHP에 추가
scoreboard players operation #this.MaxHP ER.health = @s ER.health

#> #isExist = true
scoreboard players set #isExist ER.sys 1


