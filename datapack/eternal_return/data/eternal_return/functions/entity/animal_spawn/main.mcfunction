
#> function eternal_return:entity/animal_spawn/main
#>                                            /remove_id

data modify storage minecraft:temp temp.animal set from entity @s ArmorItems[3].components."minecraft:custom_data".animal


#현재 야생동물 중 해당하는 아이디가 없는 경우 아이디 제거
execute if data storage minecraft:temp temp.animal[0] run function eternal_return:entity/animal_spawn/detect_entity
execute unless data storage minecraft:temp temp.animal[0] run scoreboard players set #HasNoID df_id 1
execute unless data storage minecraft:temp temp.animal run scoreboard players set #HasNoID df_id 0



#>쿨타임 다 돌면 + 모두 사망하면 + 플레이어가 근처에 있으면 + 소환
#맷돼지
execute at @s[tag=ER.spawn.animal.boar] if entity @p[distance=0..12] if score #HasNoID df_id matches 1 run \
    function eternal_return:entity/animal/boar/summon/this
#
execute at @s[tag=ER.spawn.animal.bear] if entity @p[distance=0..12] if score #HasNoID df_id matches 1 run \
    function eternal_return:entity/animal/bear/summon/this


#> 소환 전용 엔티티에게 데이터 입력
data modify entity @s ArmorItems[3].components."minecraft:custom_data".animal set from storage minecraft:temp temp.animal

scoreboard players set #HasNoID df_id 0
scoreboard players set #bool df_id 0
#data remove storage minecraft:temp temp