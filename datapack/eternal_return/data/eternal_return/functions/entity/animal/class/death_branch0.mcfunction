


#===================================================================================================
#>  eternal_return : 
#>  function eternal_return:entity/animal/death
#===================================================================================================

execute as @e[tag=this, tag= ER.animal.hitbox] run kill @s
execute as @e[tag=this, tag= ER.animal.HPbar ] run kill @s
$execute as @e[tag=this, tag= ER.animal.model ] run function animated_java:animal_$(animal)/remove/this
kill @s
    


