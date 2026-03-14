## 점프패드 함수
# 상위 함수 :function eternal_return:object/tick
# 입출력 없음

        
# 점프중 낙하 무적
execute if data entity @s {OnGround:1b} unless entity @e[tag=jumppad,limit=1,distance=..2] run attribute @s minecraft:fall_damage_multiplier base reset