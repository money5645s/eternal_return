## 캐릭터 스탯 설정
# 상위 함수 : function eternal_return:load

scoreboard players set #team_count NUM 16
data modify storage minecraft:game team_color set value [aqua,black,blue,dark_aqua,dark_blue,dark_gray,dark_green,dark_purple,dark_red,gold,gray,green,light_purple,red,white,yellow] 
team remove aqua
team remove black
team remove blue
team remove dark_aqua
team remove dark_blue
team remove dark_gray
team remove dark_green
team remove dark_purple
team remove dark_red
team remove gold
team remove gray
team remove green
team remove light_purple
team remove red
team remove white
team remove yellow

team add aqua
team add black
team add blue
team add dark_aqua
team add dark_blue
team add dark_gray
team add dark_green
team add dark_purple
team add dark_red
team add gold
team add gray
team add green
team add light_purple
team add red
team add white
team add yellow

team modify aqua color aqua
team modify black color black
team modify blue color blue
team modify dark_aqua color dark_aqua
team modify dark_blue color dark_blue
team modify dark_gray color dark_gray
team modify dark_green color dark_green
team modify dark_purple color dark_purple
team modify dark_red color dark_red
team modify gold color gold
team modify gray color gray
team modify green color green
team modify light_purple color light_purple
team modify red color red
team modify white color white
team modify yellow color yellow