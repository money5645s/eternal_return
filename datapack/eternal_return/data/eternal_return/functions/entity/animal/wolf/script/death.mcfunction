


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

execute as @e[tag=this, tag= ER.animal.hitbox] run kill @s
execute as @e[tag=this, tag= ER.animal.HPbar ] run kill @s
execute as @e[tag=this, tag= ER.animal.model ] run function animated_java:animal_wolf/remove/this
kill @s
    


