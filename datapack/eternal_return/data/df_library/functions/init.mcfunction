## 삼각함수 계산 마커 소환
function df_library:summon_trig

## for math calculation
scoreboard objectives add df_math dummy

scoreboard objectives add df_rotation_x dummy
scoreboard objectives add df_rotation_y dummy
scoreboard objectives add df_rotation_z dummy

scoreboard objectives add df_motion_x dummy
scoreboard objectives add df_motion_y dummy
scoreboard objectives add df_motion_z dummy

## math temp values or constants

scoreboard players set #angle df_math 0

scoreboard players set #cos df_math 0
scoreboard players set #sin df_math 0
scoreboard players set #tan df_math 0

scoreboard players set #dx df_math 0
scoreboard players set #dy df_math 0
scoreboard players set #dz df_math 0

scoreboard players set #-dx df_math 0
scoreboard players set #-dy df_math 0
scoreboard players set #-dz df_math 0

##10^n
scoreboard players set #10 df_math 10
scoreboard players set #100 df_math 100
scoreboard players set #1000 df_math 1000
scoreboard players set #10000 df_math 10000
scoreboard players set #100000 df_math 100000
scoreboard players set #1000000 df_math 1000000
scoreboard players set #10000000 df_math 10000000
scoreboard players set #100000000 df_math 100000000
scoreboard players set #1000000000 df_math 1000000000
scoreboard players set #3600 df_math 3600
scoreboard players set #5040 df_math 5040
scoreboard players set #1570 df_math 1570
scoreboard players set #900 df_math 900

## degree constants
scoreboard players set #pi df_math 18000

## negate
scoreboard players set #-1 df_math -1

##trigonometric
scoreboard objectives add df_id dummy














