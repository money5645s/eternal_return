


#===================================================================================================
#>  eternal_return : 
#>  function eternal_return:entity/animal/wolf/script/main
#>  function eternal_return:entity/animal/wolf/script/death_behav
#>  function eternal_return:entity/animal/wolf/script/death
#>  function animated_java:animal_wolf/remove/this
#
#>  animated_java :
#>  function animated_java:animal_wolf/animations/death/play
#
#   Tags
#>  ER.animal.hitbox
#>  ER.animal.root
#>  ER.animal.model
#>  ER.animal.wolf
#>  ER.optimized
#                
#===================================================================================================


#
# 체력이 0 이하인 경우 사망 처리

execute if score #this.AI ER.sys matches 1 run data modify entity @s NoAI set value 1b

execute if entity @e[\
    scores = {aj.anim_time = 30},\
    tag= aj.animal_wolf.animation.death,\
    tag= ER.animal.model,\
    tag= this] run function eternal_return:entity/animal/wolf/script/death

execute as @e[\
    tag= !aj.animal_wolf.animation.death,\
    tag= aj.animal_wolf.animation.attack,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_wolf/animations/attack/stop

execute as @e[\
    tag= !aj.animal_wolf.animation.death,\
    tag= aj.animal_wolf.animation.move,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_wolf/animations/move/stop

execute as @e[\
    tag= !aj.animal_wolf.animation.death,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_wolf/animations/death/play








