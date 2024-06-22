# 
# How to use
# if A->B, how to find B.
# 
# execute as <A> run <this_function>
# YOU HAVE TO USE THIS FUNCTION AFTER THE FUNCTION "next_node.mcfunction" !!!!
# 
# 
# 
# 

execute if entity @e[tag=next_node] run tag @e[tag=next_node] remove next_node
execute if entity @e[tag=prev_node] run tag @e[tag=prev_node] remove prev_node