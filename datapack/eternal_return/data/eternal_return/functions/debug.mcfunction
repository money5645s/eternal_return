
#> 디버그
execute as TDanfung at @s run scoreboard players operation this.root.ID ER.debug = @e[limit=1,sort=nearest,distance=..6,tag=ER.animal.root] df_id
execute as TDanfung at @s run scoreboard players operation this.model.ID ER.debug = @e[limit=1,sort=nearest,distance=..6,tag=ER.animal.model] df_id
execute as TDanfung at @s run scoreboard players operation this.hitbox.ID ER.debug = @e[limit=1,sort=nearest,distance=..6,tag=ER.animal.hitbox] df_id
execute as TDanfung at @s run scoreboard players operation this.HPbar.ID ER.debug = @e[limit=1,sort=nearest,distance=..6,tag=ER.animal.HPbar] df_id
execute as TDanfung at @s run scoreboard players operation this.hitbox.HP ER.debug = @e[limit=1,sort=nearest,distance=..6,tag=ER.animal.hitbox] ER.health
execute as TDanfung at @s run scoreboard players operation this.Max.HP ER.debug = @e[limit=1,sort=nearest,distance=..6,tag=ER.animal.root] ER.health
execute as TDanfung at @s run scoreboard players operation this.aj.anim_time ER.debug = @e[limit=1,sort=nearest,distance=..6,tag=ER.animal.model] aj.anim_time
execute as TDanfung at @s run execute store result score this.root.NoAI ER.debug run data get entity @e[limit=1,sort=nearest,distance=..6,tag=ER.animal.root  ] NoAI


#execute at @a as @e[distance= ..5,tag= ER.animal.model] run tellraw @a [{"text":"my id is "},{"color":"green","score":{"name":"@s","objective":"df_id"}}]
#execute at @a as @e[distance= ..5,tag= ER.animal.model] run tellraw @a [{"text":"my tags is "},{"color":"green","entity":"@s","nbt":"Tags"}]
