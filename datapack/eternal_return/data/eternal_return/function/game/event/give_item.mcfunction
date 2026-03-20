execute if score @s character matches 1 run function eternal_return:game/event/give_start_item/bow
execute if score @s character matches 11 run function eternal_return:game/event/give_start_item/bow
execute if score @s character matches 12 run function eternal_return:game/event/give_start_item/gloves
execute if score @s character matches 14 run function eternal_return:game/event/give_start_item/axe
execute if score @s character matches 29 run function eternal_return:game/event/give_start_item/fishing_rod
execute if score @s character matches 31 run function eternal_return:game/event/give_start_item/sword
execute if score @s character matches 32 run function eternal_return:game/event/give_start_item/spear
execute if score @s character matches 33 run function eternal_return:game/event/give_start_item/gloves



item replace entity @s armor.head with minecraft:copper_helmet[unbreakable={}]
item replace entity @s armor.chest with copper_chestplate[unbreakable={}]
item replace entity @s armor.legs with minecraft:copper_leggings[unbreakable={}]
item replace entity @s armor.feet with minecraft:copper_boots[unbreakable={}]