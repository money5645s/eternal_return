##
## function random()
## 반환값 : df_math 스코어보드의 #random 값
##
##
##
##
##
##
##
summon marker 0.0 0.0 0.0 {Tags:["random"]}
execute as @e[tag=random] store result score #random df_math run data get entity @s UUID[0]
kill @e[tag=random]