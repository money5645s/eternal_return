data modify storage minecraft:userinfo Temp set value {}
data modify storage minecraft:userinfo Players set value []
data modify storage minecraft:userinfo Players append value {}
data modify storage minecraft:userinfo Players[0] merge value {UUID:[0,0,0,0]}
data modify storage minecraft:userinfo Players[0] merge value {data:[]}