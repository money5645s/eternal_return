




#>앞에 공격 감지 마커 소환
execute at @s run summon minecraft:marker ^ ^ ^2 {Tags:["ER.animal.attackDetection","this"]}
#>Motion 얻어오기
execute store result score #motion_x ER.sys run data get entity @s Motion[0] 100000
execute store result score #motion_z ER.sys run data get entity @s Motion[2] 100000
execute unless score #motion_x ER.sys matches 0 run scoreboard players set #MotionExist ER.sys 1
execute unless score #motion_z ER.sys matches 0 run scoreboard players set #MotionExist ER.sys 1
#===================================================================================================
#>  function eternal_return:entity/animal/class/behav
#                
#   MotionExist = this.root에 모션이 있는지 없는지 판단해주는 bool
#===================================================================================================

#>this.AI = 1 이면 ready 애니메이션 종료
$execute as @e[tag= aj.animal_$(animal).animation.ready,tag= ER.animal.model,tag= this] run function animated_java:animal_$(animal)/animations/ready/stop


#>attack 애니메이션 실행 및 로직 발동
$execute at @e[tag= this,tag= ER.animal.attackDetection] if entity @p[distance=0..2] as @e[tag=!aj.animal_$(animal).animation.attack,tag=!aj.animal_$(animal).animation.skill,tag= ER.animal.model,tag= this] run function animated_java:animal_$(animal)/animations/attack/play

$execute at @e[tag= this,tag= ER.animal.attackDetection] if entity @e[tag=aj.animal_$(animal).animation.attack,tag=!aj.animal_$(animal).animation.skill,tag= ER.animal.model,tag= this, scores={aj.anim_time = 7}] run function eternal_return:entity/animal/class/attack {animal : "$(animal)", damage : $(damage)}

#>skill 애니메이션 실행 및 로직 발동
$execute at @e[tag= this,tag= ER.animal.attackDetection] if score @s ER.cooltime matches 0 if entity @e[tag=!aj.animal_$(animal).animation.attack,tag=!aj.animal_$(animal).animation.skill,tag= ER.animal.model,tag= this] if entity @p[distance=0..1] run function eternal_return:entity/animal/$(animal)/script/skill


#>공격 중엔 그대로 멈춰라
$execute if entity @e[tag= aj.animal_$(animal).animation.attack,tag= ER.animal.model,tag= this] run data modify entity @s Motion set value [0.0,0.0,0.0]


#>스킬 중엔 그대로 멈춰라
$execute if entity @e[tag= aj.animal_$(animal).animation.skill,tag= ER.animal.model,tag= this] run data modify entity @s Motion set value [0.0,0.0,0.0]


#>run 애니메이션 종료
$execute if score #MotionExist ER.sys matches 0 run execute as @e[tag= aj.animal_$(animal).animation.move,tag= ER.animal.model,tag= this] run function animated_java:animal_$(animal)/animations/move/stop
$execute as @e[tag= aj.animal_$(animal).animation.attack,tag= ER.animal.model,tag= this] run function animated_java:animal_$(animal)/animations/move/stop
$execute as @e[tag= aj.animal_$(animal).animation.skill,tag= ER.animal.model,tag= this] run function animated_java:animal_$(animal)/animations/move/stop


#>run 애니메이션 실행
$execute if score #MotionExist ER.sys matches 1 run execute as @e[tag=!aj.animal_$(animal).animation.move,tag=!aj.animal_$(animal).animation.attack,tag=!aj.animal_$(animal).animation.skill,tag= ER.animal.model, tag= this] run function animated_java:animal_$(animal)/animations/move/play



#>모델 및 엔티티 시선처리
execute on target run tag @s add targeted
execute as @e[tag= this, tag= ER.animal.model] at @s facing entity @n[distance=0..20,tag=targeted] feet run tp @s ~ ~ ~ ~ 0
execute on target run tag @s remove targeted
#>공격 지시 마커 제거
execute if entity @e[tag= this, tag= ER.animal.attackDetection] run kill @e[tag= this, tag= ER.animal.attackDetection]