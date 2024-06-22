
## Entity Euler axis
execute as @s store result entity @s Pose.Head[0] float 0.01 run scoreboard players get @s df_rotation_x
execute as @s store result entity @s Pose.Head[1] float 0.01 run scoreboard players get @s df_rotation_y
execute as @s store result entity @s Pose.Head[2] float 0.01 run scoreboard players get @s df_rotation_z

## Player (r, theta_x, theta_y) -> (theta_x, theta_y, theta_z)
execute as @a store result score @s df_rotation_x run data get entity @s Rotation[1] 100
execute as @a store result score @s df_rotation_y run data get entity @s Rotation[0] 100