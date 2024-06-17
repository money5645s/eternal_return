## 스코어보드 생성
scoreboard objectives add Cradit dummy
scoreboard objectives add ChatClick trigger
scoreboard objectives add shift minecraft.custom:minecraft.sneak_time
scoreboard objectives add hp health {"text":"♥","color":"red"}

# 쿨타임
scoreboard objectives add CT1 dummy
scoreboard objectives add CT2 dummy
scoreboard objectives add CT3 dummy
scoreboard objectives add SC dummy

# 정수
scoreboard objectives add NUM dummy

# sidebar 로그 스코어 생성
scoreboard objectives add ER.log dummy

# 야생동물 관련 스코어보드 생성
scoreboard objectives add ER.sys dummy
scoreboard players set #ER.place.null ER.sys 0
scoreboard players set #ER.place.beach ER.sys 1
scoreboard players set #ER.place.gas_station ER.sys 2
scoreboard players set #ER.place.golmok ER.sys 3
scoreboard players set #ER.place.yanggung ER.sys 4
scoreboard players set #ER.place.school ER.sys 5
scoreboard players set #ER.place.hotel ER.sys 6
scoreboard players set #ER.place.fire ER.sys 7
scoreboard players set #ER.place.police ER.sys 8
scoreboard players set #ER.place.forest ER.sys 9
scoreboard players set #ER.place.pond ER.sys 10
scoreboard players set #ER.place.stream ER.sys 11
scoreboard players set #ER.place.graveyard ER.sys 12
scoreboard players set #ER.place.hospital ER.sys 13
scoreboard players set #ER.place.village ER.sys 14
scoreboard players set #ER.place.cathedral ER.sys 15
scoreboard players set #ER.place.storage ER.sys 16
scoreboard players set #ER.place.port ER.sys 17
scoreboard players set #ER.place.factory ER.sys 18
scoreboard players set #ER.place.jul ER.sys 19

#> 야생동물 체력 및 공격력 설정
#멧돼지
scoreboard players set #ER.animal.boar.health ER.sys 15
scoreboard players set #ER.animal.boar.attack ER.sys 4
scoreboard players set #ER.animal.boar.cooltime ER.sys 120
#곰
scoreboard players set #ER.animal.bear.health ER.sys 45
scoreboard players set #ER.animal.bear.attack ER.sys 6
scoreboard players set #ER.animal.bear.cooltime ER.sys 120
#늑대
scoreboard players set #ER.animal.wolf.health ER.sys 25
scoreboard players set #ER.animal.wolf.attack ER.sys 4
scoreboard players set #ER.animal.wolf.cooltime ER.sys 120


scoreboard objectives add ER.health dummy
scoreboard objectives add ER.coolTime dummy
scoreboard objectives add df_id dummy

# 플레이어 uuid 스코어보드 생성
scoreboard objectives add ER.UUID.0 dummy
scoreboard objectives add ER.UUID.1 dummy
scoreboard objectives add ER.UUID.2 dummy
scoreboard objectives add ER.UUID.3 dummy


# df_library 전용 스코어보드 생성
#function df_library:init


# 플레이어 기본 설정
function eternal_return:system/player/init
# 보스바 기본 설정
function eternal_return:system/bossbar/init
# 액션바 기본 설정
function eternal_return:system/actionbar/init
# 사이드바 로그 기본 설정
function eternal_return:system/sidebar/init