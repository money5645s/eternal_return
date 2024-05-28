# 임시 저장된 방어력 초기화
data modify storage player_data temp[1].armor_temp set value 0
# 방어력 부여
$attribute @s minecraft:generic.armor base set $(armor_temp)