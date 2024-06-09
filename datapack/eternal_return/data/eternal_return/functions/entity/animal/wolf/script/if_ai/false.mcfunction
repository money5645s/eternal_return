#===================================================================================================
#>  function eternal_return:entity/animal/wolf/script/alive_behav
#>  function eternal_return:entity/animal/wolf/script/if_ai/false
#   Tags
#>  ER.animal.hitbox
#>  ER.animal.root
#>  ER.animal.model
#>  ER.animal.wolf
#>  ER.optimized
#                
#===================================================================================================

# 체력이 일정 100 미만인 경우 AI -> true
execute unless score #this.HP ER.sys = #this.MaxHP ER.sys run data modify entity @s NoAI set value 0b
execute unless score #this.HP ER.sys = #this.MaxHP ER.sys run scoreboard players set #this.AI ER.sys 1


# 플레이어가 근처에 가면 발동 -> ready 애니메이션
execute at @s if entity @p[distance=0..6] if score #this.HP ER.sys = #this.MaxHP ER.sys run execute as @e[\
    tag=!aj.animal_wolf.animation.ready,\
    tag=ER.animal.model,\
    tag=this] run function animated_java:animal_wolf/animations/ready/play



