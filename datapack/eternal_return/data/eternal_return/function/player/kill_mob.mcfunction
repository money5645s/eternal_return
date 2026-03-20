## 몹을 사냥했을 때
# 상위 함수 : function eternal_return:player/tick
# 입력 : @s pdb:main

execute if entity @s[tag=kill_animal_bear] run loot give @s loot eternal_return:chests/bear
execute if entity @s[tag=kill_animal_bear] run scoreboard players add @s credit 7
execute if entity @s[tag=kill_animal_bear] run scoreboard players add @s exp 360

execute if entity @s[tag=kill_animal_wolf] run give @s beef[consumable={consume_seconds:0,on_consume_effects:[{type:"minecraft:apply_effects",effects:[{id:"minecraft:regeneration",amplifier:0,duration:200,show_particles:0b}]}]},lore=[{"color":"#B7B7B7","italic":false,"text":"일반"},{"color":"gray","italic":false,"text":"음식"},{"color":"white","italic":false,"text":" "},{"color":"white","italic":false,"text":"장착 효과 :"},{"color":"blue","italic":false,"text":"+4 체력 재생"}],food={nutrition:0,saturation:0,can_always_eat:true},max_stack_size=6] 1
execute if entity @s[tag=kill_animal_wolf] run scoreboard players add @s credit 5
execute if entity @s[tag=kill_animal_wolf] run scoreboard players add @s exp 150

execute if entity @s[tag=kill_animal_boar] run give @s beef[consumable={consume_seconds:0,on_consume_effects:[{type:"minecraft:apply_effects",effects:[{id:"minecraft:regeneration",amplifier:0,duration:200,show_particles:0b}]}]},lore=[{"color":"#B7B7B7","italic":false,"text":"일반"},{"color":"gray","italic":false,"text":"음식"},{"color":"white","italic":false,"text":" "},{"color":"white","italic":false,"text":"장착 효과 :"},{"color":"blue","italic":false,"text":"+4 체력 재생"}],food={nutrition:0,saturation:0,can_always_eat:true},max_stack_size=6] 1
execute if entity @s[tag=kill_animal_boar] run scoreboard players add @s credit 7
execute if entity @s[tag=kill_animal_boar] run scoreboard players add @s exp 210

execute if entity @s[tag=kill_animal_alpha] run give @s clay_ball[lore=[{"color":"#993bda","italic":false,"text":"영웅"},{"color":"gray","italic":false,"text":"재료"}],max_stack_size=3,tooltip_style="mithril"] 1
execute if entity @s[tag=kill_animal_alpha] run scoreboard players add @s credit 15
execute if entity @s[tag=kill_animal_alpha] run scoreboard players add @s exp 450


tag @s[tag=kill_animal_bear] remove kill_animal_bear
tag @s[tag=kill_animal_wolf] remove kill_animal_wolf
tag @s[tag=kill_animal_boar] remove kill_animal_boar
tag @s[tag=kill_animal_alpha] remove kill_animal_alpha