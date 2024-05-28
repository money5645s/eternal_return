
#> function eternal_return:entity/animal_spawn/main
#>                                            /remove_id

#현재 야생동물 중 해당하는 아이디가 없는 경우 아이디 제거
execute if data entity @s ArmorItems[3].components."minecraft:custom_data".animal[0] run function eternal_return:entity/animal_spawn/detect_entity
execute unless data entity @s ArmorItems[3].components."minecraft:custom_data".animal[0] run scoreboard players set #HasNoID df_id 1



#>쿨타임 다 돌면 + 모두 사망하면 + 플레이어가 근처에 있으면 + 소환
#맷돼지
execute at @s[tag=ER.spawn.animal.boar] if entity @p[distance=0..15] if score #HasNoID df_id matches 1 run \
    function eternal_return:entity/animal/boar/summon/this
#곰
execute at @s[tag=ER.spawn.animal.bear] if entity @p[distance=0..15] if score #HasNoID df_id matches 1 run \
    function eternal_return:entity/animal/bear/summon/this





scoreboard players set #HasNoID df_id 0
scoreboard players set #bool df_id 0
