#===================================================================================================
#>  function eternal_return:entity/animal/wolf/script/alive_behav
#>  function eternal_return:entity/animal/wolf/script/if_ai/true
#>  function eternal_return:entity/animal/wolf/script/if_ai/false
#   Tags
#>  ER.animal.hitbox
#>  ER.animal.root
#>  ER.animal.model
#>  ER.animal.wolf
#>  ER.optimized
#                
#===================================================================================================


#> this.AI : false
execute if score #this.AI ER.sys matches 0 run function eternal_return:entity/animal/wolf/script/if_ai/false

#> this.AI : true
execute if score #this.AI ER.sys matches 1 run function eternal_return:entity/animal/wolf/script/if_ai/true




