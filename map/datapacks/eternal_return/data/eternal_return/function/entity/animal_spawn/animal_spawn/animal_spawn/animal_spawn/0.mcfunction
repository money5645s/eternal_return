execute as @e[tag=ER.animal.hitbox] if score @s df_id = #this.id ER.sys run tag @s add this
scoreboard players operation #this.MaxHP ER.health = @s ER.health
scoreboard players set #isExist ER.sys 1
tag @s add this
