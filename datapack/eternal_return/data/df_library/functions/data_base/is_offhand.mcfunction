
#check data from item or entity

# function df_library:data_base/find_item_tag {Slot:Slot,Tags:[Tags]}


$execute if entity @s[nbt={Inventory:[{Slot:-106b,components:{"minecraft:custom_data":{Tags:$(Tags)}}}]}] run return 1
