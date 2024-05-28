# 
# How to use
# if A->B, how to find B.
# 
# execute as <A> run <this_function>
# As this function, B got a tag next_node
# 
#  And YOU HAVE TO USE THIS FUNCTION WITH "node_end" !!!!! 
# 
# 

execute if entity @e[tag=prev_node] run tag @e[tag=prev_node] remove prev_node

scoreboard players operation #NEXT UUID1 = @s NEXT1
scoreboard players operation #NEXT UUID2 = @s NEXT2
scoreboard players operation #NEXT UUID3 = @s NEXT3
scoreboard players operation #NEXT UUID4 = @s NEXT4

execute if entity @e[tag=next_node] run tag @s[tag=next_node] remove next_node

execute as @e if score @s UUID1 = #NEXT UUID1 if score @s UUID2 = #NEXT UUID2 if score @s UUID3 = #NEXT UUID3 if score @s UUID4 = #NEXT UUID4 run tag @s add next_node
