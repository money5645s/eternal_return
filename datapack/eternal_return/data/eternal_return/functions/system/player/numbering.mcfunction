## 카운트 +1
scoreboard players add #player_id_count player_id 1
## 현재 카운트를 플레이어 아아디로 주기
scoreboard players operation @s player_id = #player_id_count player_id
## 플레이어 아이디 임시 저장
function eternal_return:system/player/idtotemp
## 스토리지 아이디 입력
function eternal_return:system/player/storeid with storage player_data temp[0]
## 태그 부여
tag @s add player_id