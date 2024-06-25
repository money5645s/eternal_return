## 당근 낚싯대 F 방지
execute as @a[nbt={Inventory:[{id:"minecraft:carrot_on_a_stick",Slot:-106b}]}] run item replace entity @s weapon.mainhand from entity @s weapon.offhand
execute as @a[nbt={Inventory:[{id:"minecraft:carrot_on_a_stick",Slot:-106b}]}] run item replace entity @s weapon.offhand with air

## 방벽 F 방지
execute as @a[nbt={Inventory:[{id:"minecraft:barrier",Slot:-106b}]}] run item replace entity @s weapon.mainhand from entity @s weapon.offhand
execute as @a[nbt={Inventory:[{id:"minecraft:barrier",Slot:-106b}]}] run item replace entity @s weapon.offhand with air

## 네더의 별 F 방지
execute as @a[nbt={Inventory:[{id:"minecraft:nether_star",Slot:-106b}]}] run item replace entity @s weapon.mainhand from entity @s weapon.offhand
execute as @a[nbt={Inventory:[{id:"minecraft:nether_star",Slot:-106b}]}] run item replace entity @s weapon.offhand with air