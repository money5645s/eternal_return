


#===================================================================================================
#>  eternal_return : 
#>  function eternal_return:entity/animal/death
#===================================================================================================


#
# 체력이 0 이하인 경우 사망 처리

execute if score #this.AI ER.sys matches 1 run data modify entity @s NoAI set value 1b

$execute if entity @e[\
    scores = {aj.anim_time = 30},\
    tag= aj.animal_$(animal).animation.death,\
    tag= ER.animal.model,\
    tag= this] run function eternal_return:entity/animal/class/death_branch0 {animal:"$(animal)"}

$execute as @e[\
    tag= !aj.animal_$(animal).animation.death,\
    tag= aj.animal_$(animal).animation.attack,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_$(animal)/animations/attack/stop

$execute as @e[\
    tag= !aj.animal_$(animal).animation.death,\
    tag= aj.animal_$(animal).animation.move,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_$(animal)/animations/move/stop

$execute as @e[\
    tag= !aj.animal_$(animal).animation.death,\
    tag= ER.animal.model,\
    tag= this] run function animated_java:animal_$(animal)/animations/death/play








