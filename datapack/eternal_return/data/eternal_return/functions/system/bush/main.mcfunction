## 큰 잔디에 들어갈 때
execute as @a at @s if block ~ ~ ~ tall_grass run tag @s add bush

# 갑옷 정보 저장
execute as @a[tag=bush,tag=!saveing_armor] at @s run function eternal_return:system/bush/save_armor
# 입었던 갑옷 만큼의 기초방어력 부여
execute as @a[tag=bush,tag=!saveing_armor] store result storage player_data temp[1].armor_temp float 1 run attribute @s generic.armor get
execute as @a[tag=bush,tag=!saveing_armor] at @s run function eternal_return:system/bush/give_armor with storage player_data temp[1]
# 깁옷 슬롯 비우기
execute as @a[tag=bush,tag=!saveing_armor] at @s run function eternal_return:system/bush/clear_armor
# 투명화 부여
effect give @a[tag=bush,tag=!saveing_armor] invisibility infinite 1 true

# 저장된 사람에게 태그 부여
tag @a[tag=bush,tag=!saveing_armor] add saveing_armor


## 큰 잔디에서 나올 때
execute as @a at @s if block ~ ~ ~ air run tag @s remove bush
# 기초 방어력 제거
execute as @a[tag=!bush,tag=saveing_armor] at @s run attribute @s minecraft:generic.armor base set 0
# 갑옷 회수
execute as @a[tag=!bush,tag=saveing_armor] at @s run function eternal_return:system/bush/load_armor
# 투명화 제거
effect clear @a[tag=!bush,tag=saveing_armor] invisibility

# 태그 제거
execute as @a at @s if block ~ ~ ~ air run tag @s remove saveing_armor