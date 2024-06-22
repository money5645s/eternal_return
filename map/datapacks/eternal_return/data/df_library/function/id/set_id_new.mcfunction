#
#   태그가 this인 엔티티에다가 id 기입
#
#   function df_library:id/set_id
# 
#   
#       1 2 4 5 6
#
#

#마지막 아이디 오프셋에 1을 더함
scoreboard players add #id.offset df_id 1

#태그가 this인 엔티티의 id에 새로운 id 오프셋을 더함
scoreboard players operation @e[tag=this] df_id = #id.offset df_id







