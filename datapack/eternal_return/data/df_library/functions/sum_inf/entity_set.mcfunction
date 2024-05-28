## sumInf 스토리지에 넣은 값을 지정된 엔티티에 입력 : execute as <entity> run <this_function>
data modify entity @s Attributes[{Name:"minecraft:generic.max_health"}].Base set from storage rpg sumInf.entity_health
data modify entity @s Health set from storage rpg sumInf.entity_health
data modify entity @s HandItems[0] set from storage rpg sumInf.entity_hold[0]
data modify entity @s HandItems[1] set from storage rpg sumInf.entity_hold[1]
data modify entity @s ArmorItems[0] set from storage rpg sumInf.entity_armor[0]
data modify entity @s ArmorItems[1] set from storage rpg sumInf.entity_armor[1]
data modify entity @s ArmorItems[2] set from storage rpg sumInf.entity_armor[2]
data modify entity @s ArmorItems[3] set from storage rpg sumInf.entity_armor[3]
data modify entity @s HandDropChances set from storage rpg sumInf.HandDropChances
data modify entity @s ArmorDropChances set from storage rpg sumInf.ArmorDropChances
data modify entity @s Tags set from storage rpg sumInf.Tags