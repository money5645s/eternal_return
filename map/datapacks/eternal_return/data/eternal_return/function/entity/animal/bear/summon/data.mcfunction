


#> function eternal_return:entity/animal/bear/summon/data
#>곰 소환

#>SUMMON ENTITY
#좀비 소환
$summon minecraft:zombie ~$(dx) ~$(dy) ~$(dz) {          \
        Tags:["this","ER.animal.root"],\
        NoAI:1b,                                         \
        Silent:1b,                                       \
        Invulnerable:1b,                                 \
        DeathTime:18,                                    \
        CustomName:'{"color":"green","text":"[ER][ER.animal.root bear]"}',\
        HasVisualFire:0b,PersistenceRequired:1b,         \
        attributes:[{id:"generic.scale",base: 1.0d},{id:"generic.attack_damage",base: 0.0d},{id:"generic.movement_speed",base: 0.3d}]}

# 히트박스 소환
$summon ghast ~$(dx) ~$(dy) ~$(dz) {\
        Tags:["this","ER.animal.hitbox"],\
        NoAI:1b,\
        Silent:1b,\
        Health:1000f,\
        DeathTime:18,\
        attributes:[{id:"generic.max_health", base: 1000.0d},{id:"generic.scale", base: 0.8d}]}

# 체력바 소환
$summon text_display ~$(dx) ~$(dy) ~$(dz) {\
        Tags:["this","ER.animal.HPbar"],\
        CustomNameVisible:0b,\
        billboard:"center",\
        text:'{"text":"test.HPshow \\n test.HPbar \\n If you are seeing this massage, that means animal/<this_animal>/main is not working"}'}



#> 모델 소환
#   tag : ER.animal.model
execute as @e[  tag= ER.animal.root,tag=this] at @s run function animated_java:animal_bear/summon
execute as @e[  tag= aj.animal_bear.root] if score @s aj.id = .aj.last_id aj.id run tag @s add this
tag @e[tag=this,tag= aj.animal_bear.root] add ER.animal.model
ride @e[tag= this, tag= ER.animal.model, limit= 1] mount @e[tag= this, tag= ER.animal.root, limit= 1]
ride @e[tag= this, tag= ER.animal.HPbar, limit= 1] mount @e[tag= this, tag= ER.animal.hitbox, limit= 1]

#> 안 보이게 하기
effect give @e[tag= this, tag= ER.animal.root  ] invisibility infinite 1 true
effect give @e[tag= this, tag= ER.animal.hitbox] invisibility infinite 1 true

#================================================[야생동물 설정]================================================
#> 체력값 
scoreboard players operation @e[tag= this, tag= ER.animal.root  ] ER.health = #ER.animal.bear.health ER.sys
scoreboard players operation @e[tag= this, tag= ER.animal.hitbox] ER.health = #ER.animal.bear.health ER.sys
#> 스킬 쿨타임
scoreboard players operation @e[tag =this, tag= ER.animal.root  ] ER.cooltime = #ER.animal.bear.cooltime ER.sys
#> 공통 태그 부여
tag @e[tag=this] add ER.animal.bear
tag @e[tag=this] add ER.animal
tag @e[tag=this] add ER


#> 아이디 부여
function df_library:id/set_id

function eternal_return:entity/store_id

#> this 태그 제거
tag @e[tag=this] remove this




