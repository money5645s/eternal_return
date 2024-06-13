



execute store result score #angle df_math run data get entity @s Rotation[0] 100

function df_library:mathmatics/trigonometric/get_cos
function df_library:mathmatics/trigonometric/get_sin

scoreboard players operation #motion_x df_math = @s df_motion_x
scoreboard players operation #motion_z df_math = @s df_motion_z

execute if score #motion_x df_library matches 0 run scoreboard players set #motion_z df_math 0
execute if score #motion_z df_library matches 0 run scoreboard players set #motion_x df_math 0

execute unless score #sin df_math matches 0 unless score #cos df_math matches 0 unless score #motion_z df_math matches 0 run scoreboard players operation #motion_z df_math /= #cos df_math
execute unless score #sin df_math matches 0 unless score #cos df_math matches 0 unless score #motion_x df_math matches 0 run scoreboard players operation #motion_x df_math /= #sin df_math

scoreboard players operation #wasd df_math = #motion_x df_math
scoreboard players operation #wasd df_math -= #motion_z df_math

#execute if score #motion_x df_math matches 100..300 if score #motion_z df_math matches 100..300 run scoreboard players set @s df_forward_axis 1
#execute unless score #motion_x df_math matches 100..300 unless score #motion_z df_math matches 100..300 run scoreboard players set @s df_forward_axis 0

