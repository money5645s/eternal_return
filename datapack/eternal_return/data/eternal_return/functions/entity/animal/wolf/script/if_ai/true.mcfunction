




#>앞에 공격 감지 마커 소환
execute at @s run summon minecraft:marker ^ ^ ^2 {Tags:["ER.animal.attackDetection","this"]}
#>Motion 얻어오기
execute store result score #motion_x ER.sys run data get entity @s Motion[0] 100000
execute store result score #motion_z ER.sys run data get entity @s Motion[2] 100000
execute unless score #motion_x ER.sys matches 0 run scoreboard players set #MotionExist ER.sys 1
execute unless score #motion_z ER.sys matches 0 run scoreboard players set #MotionExist ER.sys 1
#===================================================================================================
#>  function eternal_return:entity/animal/wolf/script/alive_behav
#>  function eternal_return:entity/animal/wolf/script/if_ai/true
#>  function eternal_return:entity/animal/wolf/script/attack
#   Tags
#>  ER.animal.hitbox
#>  ER.animal.root
#>  ER.animal.model
#>  ER.animal.boar
#>  ER.optimized
#                
#   MotionExist = this.root에 모션이 있는지 없는지 판단해주는 bool
#===================================================================================================


#>this.AI = 1 이면 ready 애니메이션 종료
execute as @e[\
    tag= aj.animal_wolf.animation.ready,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_wolf/animations/ready/stop


#>attack 애니메이션 실행 및 로직 발동
execute at @e[tag= this,tag= ER.animal.attackDetection] \
    if entity @e[\
    tag=!aj.animal_wolf.animation.attack,\
    tag=!aj.animal_wolf.animation.skill,\
    tag= ER.animal.model,\
    tag= this,\
    ] if entity @p[distance=0..1] run function eternal_return:entity/animal/wolf/script/attack


#>#>skill 애니메이션 실행 및 로직 발동
#>execute at @e[tag= this,tag= ER.animal.attackDetection] \
#>    if entity @e[\
#>    tag=!aj.animal_wolf.animation.attack,\
#>    tag=!aj.animal_wolf.animation.skill,\
#>    tag= ER.animal.model,\
#>    tag= this,\
#>    ] if entity @p[distance=0..1] run function eternal_return:entity/animal/wolf/script/skill


#>공격 중엔 그대로 멈춰라
execute if entity @e[\
    tag= aj.animal_wolf.animation.attack,\
    tag= ER.animal.model,\
    tag= this,\
    ] run data modify entity @s Motion set value [0.0,0.0,0.0]


#>스킬 중엔 그대로 멈춰라
execute if entity @e[\
    tag= aj.animal_wolf.animation.skill,\
    tag= ER.animal.model,\
    tag= this,\
    ] run data modify entity @s Motion set value [0.0,0.0,0.0]


#>run 애니메이션 종료
execute if score #MotionExist ER.sys matches 0 run execute as @e[\
    tag= aj.animal_wolf.animation.move,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_wolf/animations/move/stop
execute as @e[\
    tag= aj.animal_wolf.animation.attack,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_wolf/animations/move/stop
execute as @e[\
    tag= aj.animal_wolf.animation.skill,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_wolf/animations/move/stop


#>run 애니메이션 실행
execute if score #MotionExist ER.sys matches 1 run execute as @e[\
    tag=!aj.animal_wolf.animation.move,\
    tag=!aj.animal_wolf.animation.attack,\
    tag=!aj.animal_wolf.animation.skill,\
    tag= ER.animal.model, \
    tag= this] run function animated_java:animal_wolf/animations/move/play


#>모델 및 엔티티 시선처리
execute as @e[tag= this, tag= ER.animal.model] at @s facing entity @p[distance=0..20,sort=nearest] feet run tp @s ~ ~ ~ ~ 0
#>공격 지시 마커 제거
execute if entity @e[tag= this, tag= ER.animal.attackDetection] run kill @e[tag= this, tag= ER.animal.attackDetection]