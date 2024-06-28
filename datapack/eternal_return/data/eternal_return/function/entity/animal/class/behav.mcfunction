
# 공격 중인 엔티티 얻어오기
execute on target run tag @s add targeted

#>Motion 얻어오기
execute store result score #motion_x ER.sys run data get entity @s Motion[0] 10000000
execute store result score #motion_z ER.sys run data get entity @s Motion[2] 10000000
execute unless score #motion_x ER.sys matches 0 run scoreboard players set #MotionExist ER.sys 1
execute unless score #motion_z ER.sys matches 0 run scoreboard players set #MotionExist ER.sys 1
#===================================================================================================
#>  function eternal_return:entity/animal/class/behav
#                
#   MotionExist = this.root에 모션이 있는지 없는지 판단해주는 bool
#===================================================================================================

#>this.AI = 1 이면 ready 애니메이션 종료
$execute as @e[tag= this, tag= aj.animal_$(animal).animation.ready,tag= ER.animal.model] run function animated_java:animal_$(animal)/animations/ready/stop


#>ATTACK 애니메이션 실행 및 로직 발동

$execute at @s if entity @p[tag=targeted, distance=0..$(attackDistance)] \
         as @e[tag=  this,\
               tag= !aj.animal_$(animal).animation.attack,\
               tag= !aj.animal_$(animal).animation.skill,\
               tag=  ER.animal.model] run function animated_java:animal_$(animal)/animations/attack/play

$execute at @s if entity @p[tag=targeted, distance=0..$(attackDistance)] \
         if entity @e[tag= this,\
                      tag=   aj.animal_$(animal).animation.attack,\
                      tag=  !aj.animal_$(animal).animation.skill,\
                      tag=   ER.animal.model,\
                      scores= {aj.anim_time = $(attackTick)}] run \
    function eternal_return:entity/animal/class/attack with storage minecraft:temp temp

#>SKILL 애니메이션 실행 및 로직 발동
$execute at @e[tag= this,tag= ER.animal.attackDetection] if score @s ER.cooltime matches 0 if entity @e[tag=!aj.animal_$(animal).animation.attack,tag=!aj.animal_$(animal).animation.skill,tag= ER.animal.model,tag= this] if entity @p[distance=0..1] run function eternal_return:entity/animal/$(animal)/script/skill


#>공격 중엔 그대로 멈춰라
$execute if entity @e[tag= this, tag= aj.animal_$(animal).animation.attack, tag= ER.animal.model] run data modify entity @s Motion set value [0.0d,0.0d,0.0d]


#>스킬 중엔 그대로 멈춰라
$execute if entity @e[tag= this, tag= aj.animal_$(animal).animation.skill,  tag= ER.animal.model] run data modify entity @s Motion set value [0.0d,0.0d,0.0d]


#>MOVE 애니메이션 종료
$execute if score #MotionExist ER.sys matches 0 as @e[tag= this, tag= aj.animal_$(animal).animation.move,tag= ER.animal.model] run function animated_java:animal_$(animal)/animations/move/stop
$execute as @e[tag= aj.animal_$(animal).animation.move,\
               tag= aj.animal_$(animal).animation.attack,tag= ER.animal.model, tag= this] run function animated_java:animal_$(animal)/animations/move/stop
$execute as @e[tag= aj.animal_$(animal).animation.move,\
               tag= aj.animal_$(animal).animation.skill, tag= ER.animal.model, tag= this] run function animated_java:animal_$(animal)/animations/move/stop


#>MOVE 애니메이션 실행
$execute if score #MotionExist ER.sys matches 1 as @e[tag= this, tag=!aj.animal_$(animal).animation.move,tag=!aj.animal_$(animal).animation.attack,tag=!aj.animal_$(animal).animation.skill,tag= ER.animal.model] run function animated_java:animal_$(animal)/animations/move/play



#>모델 및 엔티티 시선처리
execute at @s run tp @e[tag= this, tag= ER.animal.hitbox] ~ ~ ~
execute as @e[tag= this, tag= ER.animal.model] at @s facing entity @n[distance=0..20,tag=targeted] feet run tp @s ~ ~ ~ ~ 0
execute on target run tag @s remove targeted
#>공격 지시 마커 제거