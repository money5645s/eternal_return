
#> function eternal_return:entity/animal/wolf/script/attack


tag @p[distance=0..2] add ER.damage.this
damage @p[tag=ER.damage.this] 10
tag @p[tag=ER.damage.this] remove ER.damage.this
execute as @e[tag=this, tag=ER.animal.model] run function animated_java:animal_wolf/animations/attack/play
