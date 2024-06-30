scoreboard players set #getDamage ER.sys 1000
scoreboard players operation #getDamage ER.sys -= #HPTMP ER.sys
scoreboard players operation @s ER.health -= #getDamage ER.sys
