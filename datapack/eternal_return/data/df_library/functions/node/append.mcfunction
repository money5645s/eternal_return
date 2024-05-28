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
scoreboard players operation @s NEXT1 = @e[tag=next_node] UUID1
scoreboard players operation @s NEXT2 = @e[tag=next_node] UUID2
scoreboard players operation @s NEXT3 = @e[tag=next_node] UUID3
scoreboard players operation @s NEXT4 = @e[tag=next_node] UUID4

execute as @e[tag=next_node] run tag @s remove next_node