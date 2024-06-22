
#   execute as @s run function df_library:data_base/add/item_in_block
#   {  
#      D1,     D2,     N
#   }
#


$execute at @s store result score #temp1 df_math run data get block ~ ~-1 ~ $(D1).$(D2)
$scoreboard players set #temp2 df_math $(N)
scoreboard players operation #temp1 df_math -= #temp2 df_math
$execute at @s if score #temp1 df_math matches ..0 run data remove block ~ ~-1 ~ $(D1)
$execute at @s if score #temp1 df_math matches 1.. store result block ~ ~-1 ~ $(D1).$(D2) byte 1 run scoreboard players get #temp1 df_math








