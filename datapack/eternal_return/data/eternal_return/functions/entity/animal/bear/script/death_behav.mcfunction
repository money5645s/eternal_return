


#===================================================================================================
#>  eternal_return : 
#>  function eternal_return:entity/animal/bear/script/main
#>  function eternal_return:entity/animal/bear/script/death_behav
#>  function eternal_return:entity/animal/bear/script/death
#>  function animated_java:animal_bear/remove/this
#
#>  animated_java :
#>  function animated_java:animal_bear/animations/death/play
#
#   Tags
#>  ER.animal.hitbox
#>  ER.animal.root
#>  ER.animal.model
#>  ER.animal.bear
#>  ER.optimized
#                
#===================================================================================================


#
# 체력이 0 이하인 경우 사망 처리

execute if score #this.AI ER.sys matches 1 run data modify entity @s NoAI set value 1b

execute if entity @e[\
    scores = {aj.anim_time = 30},\
    tag= aj.animal_bear.animation.death,\
    tag= ER.animal.model,\
    tag= this] run function eternal_return:entity/animal/bear/script/death

execute as @e[\
    tag= !aj.animal_bear.animation.death,\
    tag= aj.animal_bear.animation.attack,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_bear/animations/attack/stop

execute as @e[\
    tag= !aj.animal_bear.animation.death,\
    tag= aj.animal_bear.animation.move,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_bear/animations/move/stop

execute as @e[\
    tag= !aj.animal_bear.animation.death,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_bear/animations/death/play








