##syntax : 
# 
# #angle = <get in a value with scoreboard command and the scoreboard df_math>
# #rot_y = <get in a value with scoreboard command and the scoreboard df_math>
# #rot_z = <get in a value with scoreboard command and the scoreboard df_math>  
# 
# function df_library:mathmatics/trigonometric/get_tan
# 
# <get value #tan df_math>
#
function df_library:mathmatics/trigonometric/get_cos
function df_library:mathmatics/trigonometric/get_sin
scoreboard players operation #temp df_math = #sin df_math
scoreboard players operation #temp df_math *= #10000 df_math
scoreboard players operation #temp df_math /= #cos df_math
scoreboard players operation #tan df_math = #sin df_math
scoreboard players operation #tan df_math /= #10000 df_math





