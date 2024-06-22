# 용례 :
#
#   tag @s add this
#   function df_library:id/set_id
#   tag @e[tag=this] remove this
#
#   

#새 아이디 만들기
execute unless data storage df_id id.array.deleted[0] run function df_library:id/set_id_new

#삭제된 아이디 리스트 찾아서 만들기
execute if data storage df_id id.array.deleted[0] run function df_library:id/set_id_with_del








