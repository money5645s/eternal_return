# Syntax : execute as <Player> run function df_library:mathmatics/vector/get
# get_vector(Player@s)
# return : Player's view vector3 (x, y, z) #dx , #dy, #dz df_math

execute at @s run summon marker ^ ^ ^1 {Tags:["vector"]}
execute as @e[tag=vector] store result score #dx df_math run data get entity @s Pos[0] 10000
execute as @e[tag=vector] store result score #dy df_math run data get entity @s Pos[1] 10000
execute as @e[tag=vector] store result score #dz df_math run data get entity @s Pos[2] 10000
execute as @s store result score #-dx df_math run data get entity @s Pos[0] 10000
execute as @s store result score #-dy df_math run data get entity @s Pos[1] 10000
execute as @s store result score #-dz df_math run data get entity @s Pos[2] 10000
scoreboard players operation #dx df_math -= #-dx df_math
scoreboard players operation #dy df_math -= #-dy df_math
scoreboard players operation #dz df_math -= #-dz df_math
kill @e[tag=vector]


