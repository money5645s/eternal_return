execute if score @s character matches 1 run function eternal_return:game/event/give_start_item/bow
execute if score @s character matches 11 run function eternal_return:game/event/give_start_item/bow
execute if score @s character matches 12 run function eternal_return:game/event/give_start_item/gloves
execute if score @s character matches 14 run function eternal_return:game/event/give_start_item/axe
execute if score @s character matches 29 run function eternal_return:game/event/give_start_item/fishing_rod
execute if score @s character matches 31 run function eternal_return:game/event/give_start_item/sword
execute if score @s character matches 32 run function eternal_return:game/event/give_start_item/spear
execute if score @s character matches 33 run function eternal_return:game/event/give_start_item/gloves


give @s bread[consumable={consume_seconds:0,on_consume_effects:[{type:"minecraft:apply_effects",effects:[{id:"minecraft:regeneration",amplifier:1,duration:200,show_particles:0b}]}]},lore=[{"color":"#B7B7B7","italic":false,"text":"일반"},{"color":"gray","italic":false,"text":"음식"},{"color":"white","italic":false,"text":" "},{"color":"white","italic":false,"text":"소비 효과 :"},{"color":"blue","italic":false,"text":"+8 체력 재생"}],food={nutrition:0,saturation:0,can_always_eat:true}] 6
item replace entity @s armor.head with minecraft:copper_helmet[unbreakable={}]
item replace entity @s armor.chest with copper_chestplate[unbreakable={}]
item replace entity @s armor.legs with minecraft:copper_leggings[unbreakable={}]
item replace entity @s armor.feet with minecraft:copper_boots[unbreakable={}]