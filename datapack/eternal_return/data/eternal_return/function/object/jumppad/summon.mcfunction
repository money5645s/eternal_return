## 모델링 소환
    $summon armor_stand ~ ~0.05 ~ {NoGravity:1b,Silent:1b,Invisible:1b,Tags:["jumppad",$(place)],DisabledSlots:4144959,equipment:{head:{id:"minecraft:creeper_banner_pattern",count:1,components:{"minecraft:custom_model_data":{floats:[0],strings:["jumppad"]}}}}}
    # 시선 회전
        $execute as @e[tag=jumppad,type=armor_stand,distance=..3] at @s run tp @s ~ ~ ~ $(rotation) ~

    $scoreboard players set @e[limit=1,tag=jumppad,tag=$(place)] ct1 0
    $scoreboard players set @e[limit=1,tag=jumppad,tag=$(place)] ct2 0 
## 빛 블럭 설치
    setblock ~ ~ ~ light[level=8,waterlogged=false]