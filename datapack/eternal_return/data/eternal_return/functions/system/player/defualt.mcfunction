# 스코어보드 생성
scoreboard objectives add player_id dummy

# 스토리지 생성
data remove storage minecraft:player_data players
data merge storage player_data {players:[],temp:[{id_temp:0},{armor_temp:0}]}

# 플레이어 스코어및 태그 지우기
tag @a remove player_id
scoreboard players reset @a player_id
# 플레이어 아이디 카운트 초기화
scoreboard players set #player_id_count player_id 0