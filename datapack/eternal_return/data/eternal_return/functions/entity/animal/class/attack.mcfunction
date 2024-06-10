
#> function eternal_return:entity/animal/class/attack


tag @p[distance=0..2] add ER.damage.this
$damage @p[tag=ER.damage.this] $(damage) minecraft:player_attack
tag @p[tag=ER.damage.this] remove ER.damage.this
$execute as @e[tag=this, tag=ER.animal.model] run function animated_java:animal_$(animal)/animations/attack/play

