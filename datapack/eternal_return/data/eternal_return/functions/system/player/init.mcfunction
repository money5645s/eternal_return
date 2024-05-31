# 스코어보드 생성
scoreboard objectives add player_id dummy

# 스토리지 생성
data remove storage minecraft:player_data players
data modify storage player_data players set value []
data modify storage player_data temp set value [{id:0},{armor:0},{index:""},{},{text:''}]

# 플레이어 스코어 및 태그 지우기
tag @a remove player_id
scoreboard players reset @a player_id
# 플레이어 아이디 카운트 초기화
scoreboard players set #player_id_count player_id 0