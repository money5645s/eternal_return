# 맵 저장
data remove storage map_data location
data modify storage map_data location set value []




## 맵 초깃값 설정

# 장소 값 저장

# type 값에 따른 장소 타입 지정
# safe = 0
# scheduled = 1
# banned = 2
# battlezone = 3

data modify storage map_data location append value {type:0,place:alley,ui_name:"   골목길  "}
data modify storage map_data location append value {type:0,place:archery_range,ui_name:"   양궁장  "}
data modify storage map_data location append value {type:0,place:beach,ui_name:"  모래사장 "}
data modify storage map_data location append value {type:0,place:cemetery,ui_name:"    묘지   "}
data modify storage map_data location append value {type:0,place:chapel,ui_name:"    성당   "}
data modify storage map_data location append value {type:0,place:dock,ui_name:"    항구   "}
data modify storage map_data location append value {type:0,place:factory,ui_name:"    공장   "}
data modify storage map_data location append value {type:0,place:fire_station,ui_name:"   소방서  "}
data modify storage map_data location append value {type:0,place:forest,ui_name:"     숲    "}
data modify storage map_data location append value {type:0,place:gas_station,ui_name:"   주유소  "}
data modify storage map_data location append value {type:0,place:hospital,ui_name:"    병원   "}
data modify storage map_data location append value {type:0,place:hotel,ui_name:"    호텔   "}
data modify storage map_data location append value {type:0,place:police_station,ui_name:"   경찰서  "}
data modify storage map_data location append value {type:0,place:pond,ui_name:"    연못   "}
data modify storage map_data location append value {type:0,place:school,ui_name:"    학교   "}
data modify storage map_data location append value {type:0,place:stream,ui_name:"    개울   "}
data modify storage map_data location append value {type:0,place:temple,ui_name:"     절    "}
data modify storage map_data location append value {type:0,place:uptown,ui_name:"고급 주택가"}
data modify storage map_data location append value {type:0,place:warehouse,ui_name:"    창고   "}