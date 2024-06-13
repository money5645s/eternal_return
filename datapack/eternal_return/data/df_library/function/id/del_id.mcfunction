#
#   아이디가 있는 엔티티를 삭제할 때 사용.
#   function df_library:id/del_id
#   
#   deleted 배열에 삭제할 엔티티의 아이디를 넣어서 다음 아이디로 사용할 수 있도록 함.
#
#
#
#
#
#

#삭제할 엔티티의 아이디를 storage로 가져옴
execute store result storage df_id id.temp int 1 run scoreboard players get @s df_id

#삭제할 엔티티의 아이디를 deleted 스택에 쌓기
data modify storage df_id id.array.deleted prepend from storage df_id id.temp 







