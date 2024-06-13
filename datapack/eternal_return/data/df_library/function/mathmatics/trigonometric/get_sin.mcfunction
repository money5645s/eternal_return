##syntax : 
# 
# #angle = <get in a value with scoreboard command and the scoreboard df_math>
# 
# function df_library:mathmatics/trigonometric/get_sin
# 
# <get value #sin df_math>
#
#
scoreboard players operation @e[tag=trigonometric] df_rotation_x = #angle df_math
execute as @e[tag=trigonometric] at @s store result entity @s Rotation[0] float 0.01 run scoreboard players get #angle df_math
execute as @e[tag=trigonometric] at @s run tp @s ^ ^ ^1
execute as @e[tag=trigonometric] at @s store result score #sin df_math run data get entity @s Pos[0] 100000
execute as @e[tag=trigonometric] at @s run tp @s 0.0 0.0 0.0

