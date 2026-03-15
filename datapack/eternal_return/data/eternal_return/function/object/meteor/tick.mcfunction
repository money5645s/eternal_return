## 스코어보드
execute if score @s ct1 matches ..599 run scoreboard players add @s ct1 1
execute if score @s ct1 matches 499 run playsound minecraft:er.meteorite.impact master @a[distance=..10] ~ ~ ~ 9

# 충돌 후
execute if score @s ct1 matches 600 run function eternal_return:object/meteor/impact



# 떨어진후 채팅창 출력 : <>,<>에,운석이 떨어졌습니다.
