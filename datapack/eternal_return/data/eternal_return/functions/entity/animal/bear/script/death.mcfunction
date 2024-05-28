


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

execute as @e[tag=this, tag= ER.animal.hitbox] run kill @s
execute as @e[tag=this, tag= ER.animal.HPbar ] run kill @s
execute as @e[tag=this, tag= ER.animal.model ] run function animated_java:animal_bear/remove/this
kill @s
    


