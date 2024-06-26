
# 보어를 소환할 아머 스탠드를 소환하는 함수입니다.
#> function eternal_return:entity/animal_spawn_point/main



execute if entity @s[nbt= {SelectedItem:{components:{"minecraft:custom_data":{summon:"ER.animal.boar"}}}}] if score @s click matches 1.. run function eternal_return:entity/animal_spawn_point/summon {animal:"boar"}
execute if entity @s[nbt= {SelectedItem:{components:{"minecraft:custom_data":{summon:"ER.animal.bear"}}}}] if score @s click matches 1.. run function eternal_return:entity/animal_spawn_point/summon {animal:"bear"}
execute if entity @s[nbt= {SelectedItem:{components:{"minecraft:custom_data":{summon:"ER.animal.wolf"}}}}] if score @s click matches 1.. run function eternal_return:entity/animal_spawn_point/summon {animal:"wolf"}
execute if entity @s[nbt= {SelectedItem:{components:{"minecraft:custom_data":{summon:"ER.animal.alpha"}}}}] if score @s click matches 1.. run function eternal_return:entity/animal_spawn_point/summon {animal:"alpha"}

scoreboard players set @s click 0

