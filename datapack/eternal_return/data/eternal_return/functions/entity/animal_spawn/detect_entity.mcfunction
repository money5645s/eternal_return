
# function eternal_return:entity/animal_spawn/detect_entity
# function eternal_return:entity/animal_spawn/find_root


#> animal 배열 회전해서 top 값 업데이트
data modify storage minecraft:temp temp.animal append from storage minecraft:temp temp.animal[0]
data remove storage minecraft:temp temp.animal[0]


#> 모든 ER.animal의 엔티티의 id와 이 아머 스탠드가 보유한 아이디와 모두 비교 -> 하나라도 있으면 bool 값 1로 ->
execute store result score #this.id ER.sys run data get storage minecraft:temp temp.animal[0].id

#> find animal root 
#   input  : 모든 ER.animal.root, ER.animal.hitbox
#   output : ER.animal.root[tag=this], ER.animal.hitbox[tag=this], isExist = {0 or 1}
execute as @e[tag=ER.animal.root] if score @s df_id = #this.id ER.sys run function eternal_return:entity/animal_spawn/is_exist

#=========================================================================================
#   
#   this.id     ER.sys     :   이 ER.animal.root의 아이디
#   isExist     ER.sys     :   이 ER.animal.root가 존재한다면 1, 아니면 0
#   this.MaxHP  ER.health  :   이 ER.animal.hitbox 최대 체력
#
#=========================================================================================


#> 엔티티가 존재한다면
#제자리로 복귀
execute if score #isExist ER.sys matches 1 at @s run function eternal_return:entity/animal_spawn/get_back/main {Radius : 20}

#> 엔티티가 없다면
# 만약 없다면 제거
execute if score #isExist ER.sys matches 0 run data remove storage minecraft:temp temp.animal[0]


scoreboard players set #isExist ER.sys 0
tag @e[tag=this] remove this
