## 배경 연산
execute if score left.time.bg.tick time matches ..2 if score left.time.bg.num time matches ..8 run scoreboard players add left.time.bg.tick time 1
execute if score left.time.bg.tick time matches 3 if score left.time.bg.num time matches ..8 run scoreboard players add left.time.bg.num time 1
execute if score left.time.bg.num time matches 9 run scoreboard players set left.time.bg.num time 1
execute if score left.time.bg.tick time matches 3 run scoreboard players set left.time.bg.tick time 0

execute if score left.sec time matches 5 if score now.bg time matches ..8 run scoreboard players add now.bg time 1
execute if score left.sec time matches 2 if score now.bg time matches 1.. run scoreboard players remove now.bg time 1