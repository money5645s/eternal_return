




#아이디 리턴해서 시전자에게 append 알고리즘
# 아이디를 temp 스토리지로 넣는다
# temp 스토리지에 넣은 아이디를 목적지 스토리지에 append 한다.
scoreboard players operation #temp df_id = @e[tag=this,limit=1] df_id
execute store result storage minecraft:df_temp temp.id int 1 run scoreboard players get #temp df_id
data modify storage minecraft:df_temp temp.Pos set from entity @e[tag=this, tag=ER.animal.root, limit=1] Pos
data modify storage minecraft:temp temp.animal append from storage minecraft:df_temp temp
data remove storage minecraft:df_temp temp




