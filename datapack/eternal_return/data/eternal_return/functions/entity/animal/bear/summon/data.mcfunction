


#> function eternal_return:entity/animal/bear/summon/data
#>곰 소환

#>SUMMON ENTITY
#좀비 소환
$summon minecraft:zombie ~$(dx) ~$(dy) ~$(dz) {          \
        Tags:["this","ER.animal.root"],\
        NoAI:1b,                                         \
        Silent:1b,                                       \
        Invulnerable:1b,                                 \
        CustomName:'{"color":"green","text":"[ER][ER.animal.root bear]"}',\
        Attributes:[                                     \
            {Name:generic.scale,Base:1},                 \
            {Name:generic.attack_damage,Base:0},         \
            {Name:generic.movement_speed,Base:0.34}      \
            ]                                            \
        }
# 히트박스 소환
$summon ghast ~$(dx) ~$(dy) ~$(dz) {\
        Tags:["this","ER.animal.hitbox"],\
        NoAI:1b,\
        Silent:1b,\
        Health:1000f,\
        Attributes:[\
            {Name:generic.max_health,Base:1000},\
            {Name:generic.scale,Base:0.8}\
            ]\
        }

$summon text_display ~$(dx) ~$(dy) ~$(dz) {\
        Tags:["this","ER.animal.HPbar"],\
        CustomNameVisible:0b,\
        billboard:"center",\
        text:'{"text":"test.HPshow \\n test.HPbar"}'}

#> 안 보이게 하기
effect give @e[tag= this, tag= ER.animal.root  ] invisibility infinite 1 true
effect give @e[tag= this, tag= ER.animal.hitbox] invisibility infinite 1 true

#> 체력값 
scoreboard players set @e[tag= this, tag= ER.animal.root  ] ER.health 100
scoreboard players set @e[tag= this, tag= ER.animal.hitbox] ER.health 100

#> 곰 모델 소환
#   tag : ER.animal.model
execute as @e[  tag= ER.animal.root,tag=this] at @s run function animated_java:animal_bear/summon
execute as @e[  tag= aj.animal_bear.root] if score @s aj.id = .aj.last_id aj.id run tag @s add this
tag @e[tag=this,tag= aj.animal_bear.root] add ER.animal.model
ride @e[tag= this, tag= ER.animal.model, limit= 1] mount @e[tag= this, tag= ER.animal.root, limit= 1]
ride @e[tag= this, tag= ER.animal.HPbar, limit= 1] mount @e[tag= this, tag= ER.animal.hitbox, limit= 1]

#> 공통 태그 부여
#   tag : ER.animal.bear
tag @e[tag=this] add ER.animal.bear
tag @e[tag=this] add ER.animal


#> 아이디 부여
function df_library:id/set_id

#아이디 리턴해서 시전자에게 append 알고리즘
# 아이디를 temp 스토리지로 넣는다
# temp 스토리지에 넣은 아이디를 목적지 스토리지에 append 한다.
scoreboard players operation #temp df_id = @e[tag=this,limit=1] df_id
execute store result storage df_temp temp.id int 1 run scoreboard players get #temp df_id
data modify storage df_temp temp.Pos set from entity @e[tag=this, tag=ER.animal.root, limit=1] Pos
data modify entity @s ArmorItems[3].components."minecraft:custom_data".animal append from storage minecraft:df_temp temp
data remove storage df_temp temp


#> this 태그 제거
tag @e[tag=this] remove this




## Debug
#execute as @e[tag=this] run say summoned




