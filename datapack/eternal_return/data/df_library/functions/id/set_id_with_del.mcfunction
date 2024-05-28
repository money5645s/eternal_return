#
#   태그가 this인 엔티티에다가 id 기입
#
#   function df_library:id/set_id
# 
#   
#       1 2 4 5 6
#
#

# 삭제된 아이디 스택의 top을 스코어보드로 가져옴
execute store result score #id.temp df_id run data get storage df_id id.array.deleted[0]

# 삭제된 아이디 스택의 top을 pop
data remove storage df_id id.array.deleted[0]

# 삭제된 아이디 스택의 top을 스코어보드로 저장
scoreboard players operation @e[tag=this] df_id = #id.temp df_id 






