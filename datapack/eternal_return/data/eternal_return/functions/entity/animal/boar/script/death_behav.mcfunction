


#===================================================================================================
#>  eternal_return : 
#>  function eternal_return:entity/animal/boar/script/main
#>  function eternal_return:entity/animal/boar/script/death_behav
#>  function eternal_return:entity/animal/boar/script/death
#>  function animated_java:animal_boar/remove/this
#
#>  animated_java :
#>  function animated_java:animal_boar/animations/death/play
#
#   Tags
#>  ER.animal.hitbox
#>  ER.animal.root
#>  ER.animal.model
#>  ER.animal.boar
#>  ER.optimized
#                
#===================================================================================================




#
# 체력이 0 이하인 경우 사망 처리

execute if score #this.AI ER.sys matches 1 run data modify entity @s NoAI set value 1b

execute if entity @e[\
    scores = {aj.anim_time = 39},\
    tag= aj.animal_boar.animation.death,\
    tag= ER.animal.model,\
    tag= this] run function eternal_return:entity/animal/boar/script/death

#> 애니메이션 모두 정지
execute as @e[\
    tag= aj.animal_boar.animation.attack,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_boar/animations/attack/stop

execute as @e[\
    tag= aj.animal_boar.animation.move,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_boar/animations/move/stop

execute as @e[\
    tag= aj.animal_boar.animation.skill,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_boar/animations/move/stop

#> 사망 애니메이션 play
execute as @e[\
    tag= !aj.animal_boar.animation.death,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_boar/animations/death/play



