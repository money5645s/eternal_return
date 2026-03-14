execute if score @s credit matches 300.. store result score #random_buy NUM run random value 1..5
execute if score @s credit matches 300.. if score #random_buy NUM matches 1 run give @s turtle_scute[lore=[{"color":"#993bda","italic":false,"text":"영웅"},{"color":"gray","italic":false,"text":"재료"}],max_stack_size=3,tooltip_style="vf_blood_sample"] 1
execute if score @s credit matches 300.. if score #random_buy NUM matches 2 run give @s clay_ball[lore=[{"color":"#993bda","italic":false,"text":"영웅"},{"color":"gray","italic":false,"text":"재료"}],max_stack_size=3,tooltip_style="mithril"] 1
execute if score @s credit matches 300.. if score #random_buy NUM matches 3 run give @s slime_ball[lore=[{"color":"#993bda","italic":false,"text":"영웅"},{"color":"gray","italic":false,"text":"재료"}],max_stack_size=3,tooltip_style="meteorite"] 1
execute if score @s credit matches 300.. if score #random_buy NUM matches 4 run give @s minecraft:armadillo_scute[lore=[{"color":"#993bda","italic":false,"text":"영웅"},{"color":"gray","italic":false,"text":"재료"}],max_stack_size=3,tooltip_style="force_core"] 1
execute if score @s credit matches 300.. if score #random_buy NUM matches 5 run give @s prismarine_shard[lore=[{"color":"#993bda","italic":false,"text":"영웅"},{"color":"gray","italic":false,"text":"재료"}],max_stack_size=3,tooltip_style="tree_of_life"] 1

execute if score @s credit matches 300.. run scoreboard players reset #random_buy NUM
execute if score @s credit matches 300.. run scoreboard players remove @s credit 300