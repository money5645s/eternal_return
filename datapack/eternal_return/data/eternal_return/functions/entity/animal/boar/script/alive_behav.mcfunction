#===================================================================================================
#>  function eternal_return:entity/animal/boar/script/alive_behav
#>  function eternal_return:entity/animal/boar/script/if_ai/true
#>  function eternal_return:entity/animal/boar/script/if_ai/false
#   Tags
#>  ER.animal.hitbox
#>  ER.animal.root
#>  ER.animal.model
#>  ER.animal.boar
#>  ER.optimized
#                
#===================================================================================================

#> this.AI : true
execute if score #this.AI ER.sys matches 1 run function eternal_return:entity/animal/boar/script/if_ai/true

#> this.AI : false
execute if score #this.AI ER.sys matches 0 run function eternal_return:entity/animal/boar/script/if_ai/false


