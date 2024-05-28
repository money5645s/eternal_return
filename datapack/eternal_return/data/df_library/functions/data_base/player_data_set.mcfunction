data modify storage minecraft:userinfo Players prepend value {}
data modify storage minecraft:userinfo Players[0] merge value {UUID:[0,0,0,0]}
data modify storage minecraft:userinfo Players[0] merge value {data:[]}
data modify storage minecraft:userinfo Players[0].UUID[0] set from entity @s UUID[0]
data modify storage minecraft:userinfo Players[0].UUID[1] set from entity @s UUID[1]
data modify storage minecraft:userinfo Players[0].UUID[2] set from entity @s UUID[2]
data modify storage minecraft:userinfo Players[0].UUID[3] set from entity @s UUID[3]