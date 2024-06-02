
#> function eternal_return:entity/main



#> 야생동물 스폰 포인트 제어
execute if entity @s[tag= ER.spawn.animal] at @s if entity @p[distance=..20] run function eternal_return:entity/animal_spawn/main


#> 히트박스
execute if entity @s[tag= ER.animal.hitbox] if score @s ER.health matches 1.. run function eternal_return:entity/hitbox


#> 야생동물 스크립트
# 멧돼지
execute if entity @s[tag= ER.animal.root ,tag= ER.animal.boar] run function eternal_return:entity/animal/boar/script/main
# 곰
execute if entity @s[tag= ER.animal.root ,tag= ER.animal.bear] run function eternal_return:entity/animal/bear/script/main


