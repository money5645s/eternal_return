# 
# How to use
# if A->B, how to find B.
# 
# execute as <A> run <this_function>
# As this function, B got a tag prev_node
# 
#  And YOU HAVE TO USE THIS FUNCTION WITH "node_end" !!!!! 
# 
# 
scoreboard players operation #PREV UUID1 = @s PREV1
scoreboard players operation #PREV UUID2 = @s PREV2
scoreboard players operation #PREV UUID3 = @s PREV3
scoreboard players operation #PREV UUID4 = @s PREV4

tag @s[tag=prev_node] remove prev_node

execute as @e if score @s UUID1 = #PREV UUID1 if score @s UUID2 = #PREV UUID2 if score @s UUID3 = #PREV UUID3 if score @s UUID4 = #PREV UUID4 run tag @s add prev_node
