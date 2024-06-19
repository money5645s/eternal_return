
#> function eternal_return:entity/animal_spawn/main
#>                                            /remove_id

$data modify storage minecraft:temp temp.animal set from entity @s $(nbt).animal


#현재 야생동물 중 해당하는 아이디가 없는 경우 아이디 제거
execute if data storage minecraft:temp temp.animal[0] run function eternal_return:entity/animal_spawn/detect_entity
execute unless data storage minecraft:temp temp.animal[0].id run scoreboard players set #HasNoID df_id 1
execute unless data storage minecraft:temp temp.animal run scoreboard players set #HasNoID df_id 0



#>쿨타임 다 돌면 + 모두 사망하면 + 플레이어가 근처에 있으면 + 소환
# 맷돼지
execute if score #HasNoID df_id matches 1 at @s[tag=ER.spawn.animal.boar] if entity @p[distance=0..20] run function eternal_return:entity/animal/boar/summon/this
# 곰
execute if score #HasNoID df_id matches 1 at @s[tag=ER.spawn.animal.bear] if entity @p[distance=0..20] run function eternal_return:entity/animal/bear/summon/this
# 곰
execute if score #HasNoID df_id matches 1 at @s[tag=ER.spawn.animal.wolf] if entity @p[distance=0..20] run function eternal_return:entity/animal/wolf/summon/this

#> 소환 전용 엔티티에게 데이터 입력
$data modify entity @s $(nbt).animal set from storage minecraft:temp temp.animal
#title TDanfung actionbar [{"nbt":"temp.animal[0]","storage":"minecraft:temp"}]

scoreboard players set #HasNoID df_id 0
scoreboard players set #bool df_id 0
data remove storage minecraft:temp temp