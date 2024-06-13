execute as @s run item replace block 0 -62 0 container.0 from entity @s weapon.offhand
item replace entity @s weapon.offhand from entity @s weapon.mainhand
execute as @s run item replace entity @s weapon.mainhand from block 0 -62 0 container.0