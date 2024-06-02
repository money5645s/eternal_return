


#> function eternal_return:entity/animal/boar/summon/data
#돼지 소환

#>SUMMON ENTITY
#>좀비 소환
$summon minecraft:zombie ~$(dx) ~$(dy) ~$(dz) {          \
        Tags:["this","ER.animal.root"],                  \
        NoAI:1b,                                         \
        Silent:1b,                                       \
        Invulnerable:1b,                                 \
        DeathTime:18,                                    \
        ArmorItems:[{},{},{},{id:"minecraft:stone_button",count:1}],\
        CustomName:'{"color":"green","text":"[ER][ER.animal.root boar]"}',\
        HasVisualFire:0b,PersistenceRequired:1b,         \
        Attributes:[                                     \
            {Name:generic.scale,Base:1},                 \
            {Name:generic.attack_damage,Base:0},         \
            {Name:generic.movement_speed,Base:0.34}      \
            ]                                            \
        }
#>히트박스 소환
$summon ghast ~$(dx) ~$(dy) ~$(dz) {\
        Tags:["this","ER.animal.hitbox"],\
        NoAI:1b,\
        Silent:1b,\
        Health:1000f,\
        DeathTime:18,\
        Attributes:[\
            {Name:generic.max_health,Base:1000},\
            {Name:generic.scale,Base:0.7}\
            ]\
        }
#>체력바 소환
$summon text_display ~$(dx) ~$(dy) ~$(dz) {\
        Tags:["this","ER.animal.HPbar"],\
        CustomNameVisible:0b,\
        billboard:"center",\
        text:'{"text":"test.HPshow \\n test.HPbar"}'}

#> 체력값 부여
scoreboard players set @e[tag= this, tag= ER.animal.root  ] ER.health 30
scoreboard players set @e[tag= this, tag= ER.animal.hitbox] ER.health 30
effect give @e[tag= this, tag= ER.animal.root  ] invisibility infinite 1 true
effect give @e[tag= this, tag= ER.animal.hitbox] invisibility infinite 1 true


#>맷돼지 모델 소환
execute as @e[tag= ER.animal.root,tag=this] at @s run function animated_java:animal_boar/summon
execute as @e[tag= aj.animal_boar.root] if score @s aj.id = .aj.last_id aj.id run tag @s add this

tag @e[ tag= this, tag= aj.animal_boar.root] add ER.animal.model
ride @e[tag= this, tag= ER.animal.model, limit= 1] mount @e[tag= this, tag= ER.animal.root,  limit= 1]
ride @e[tag= this, tag= ER.animal.HPbar, limit= 1] mount @e[tag= this, tag= ER.animal.hitbox,limit= 1]

#> 공통 태그 부여
tag @e[tag=this] add ER.animal.boar
tag @e[tag=this] add ER.animal



# 아이디 부여
function df_library:id/set_id

#> 아이디 저장
function eternal_return:entity/animal_spawn/store_id

#> this 태그 제거
tag @e[tag=this] remove this


