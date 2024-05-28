# 
# How to use
# A->B
# tag <B> add next_node
# execute as <A> run <this_function>
# 
# 
# 
# 
# 
scoreboard players operation @s PREV1 = @e[tag=prev_node] UUID1
scoreboard players operation @s PREV2 = @e[tag=prev_node] UUID2
scoreboard players operation @s PREV3 = @e[tag=prev_node] UUID3
scoreboard players operation @s PREV4 = @e[tag=prev_node] UUID4

tag @e[tag=prev_node] remove prev_node