


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




execute as @e[tag=this, tag= ER.animal.model ] run function animated_java:animal_boar/remove/this
kill @s
    


