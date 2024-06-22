
#> function eternal_return:entity/hitbox


execute store result score #this.HP ER.health run data get entity @s Health
execute unless score #this.HP ER.health matches 1000 run scoreboard players set #getDamage ER.health 1000
execute unless score #this.HP ER.health matches 1000 run scoreboard players operation #getDamage ER.health -= #this.HP ER.health
execute unless score #this.HP ER.health matches 1000 run scoreboard players operation @s ER.health -= #getDamage ER.health



execute store result storage minecraft:temp temp.HP int 1 run scoreboard players get @s ER.health

#> 사망 시 제거 (음수값 띄우기 방지)
execute if score @s ER.health matches ..0 on passengers if entity @s[tag=ER.animal.HPbar] run data modify entity @s text set value '[{"text":"[   "},{"text":"0"},{"text":"   ]"}]'

#> HP 바 폰트로 표시
execute if score @s ER.health matches 1.. on passengers if entity @s[tag=ER.animal.HPbar] run function eternal_return:entity/hp_bar with storage minecraft:temp temp


data remove storage minecraft:temp temp
execute unless score #this.HP ER.health matches 1000 run data modify entity @s Health set value 1000
