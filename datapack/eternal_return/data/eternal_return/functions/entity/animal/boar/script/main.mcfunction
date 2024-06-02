#> 멧돼지의 스크립트


#===================================================================================================
#>  function eternal_return:entity/animal/boar/script/main
#>  function eternal_return:entity/animal/boar/script/attack
#>  function eternal_return:entity/animal/boar/script/show_model
#>  function eternal_return:entity/animal/boar/script/alive_behav
#>  function eternal_return:entity/animal/boar/script/death_behav
#>  function eternal_return:entity/animal/boar/script/optimize/disable_model
#>  function eternal_return:entity/animal/boar/script/optimize/enable_model
#>  function eternal_return:entity/animal/hitbox
#
#   Tags
#>  ER.animal.hitbox
#>  ER.animal.root
#>  ER.animal.model
#>  ER.animal.boar
#>  ER.optimized
#
#>  
#                
#===================================================================================================


# 아이디 체크
tag @s add this
scoreboard players operation #this.ID ER.sys = @s df_id
execute as @e[tag=ER.animal.boar, tag=ER.animal.hitbox] if score @s df_id = #this.ID ER.sys run tag @s add this
execute as @e[tag=ER.animal.boar, tag=ER.animal.HPbar ] if score @s df_id = #this.ID ER.sys run tag @s add this
execute as @e[tag=ER.animal.boar, tag=ER.animal.model ] if score @s df_id = #this.ID ER.sys run tag @s add this
# 변수 얻어오기
execute store result score #this.MaxHP ER.sys run scoreboard players get @s ER.health
execute store result score #this.HP ER.sys run scoreboard players get @e[tag= ER.animal.hitbox, tag= this, limit= 1] ER.health
# AI 체크
execute if entity @s[nbt={NoAI:1b}] run scoreboard players set #this.AI ER.sys 0
execute unless entity @s[nbt={NoAI:1b}] run scoreboard players set #this.AI ER.sys 1

#===================================================================================================
#
#   ER.sys . this.hp   = ER.animal.boar의 현재 체력
#   ER.sys . this.ID   = ER.animal.boar의 df_id (스코어보드가 df_id -> ER.sys로 교체됨)
#   ER.sys . this.AI   = ER.animal.boar의 AI 존재 여부. 1이면 true 0이면 false
#   ER.sys . #motion_x = ER.animal.boar의 현재 모션값 x
#   ER.sys . #motion_y = ER.animal.boar의 현재 모션값 y
#   ER.sys . #motion_z = ER.animal.boar의 현재 모션값 z
#   tag = this : 이 스크립트에서 처리하는 야생동물
#
#===================================================================================================


#> 최적화 [엔티티 쇼 / 노쇼]
execute if entity @s[tag= !ER.optimized] at @s \
        unless entity @p[distance=..20] run \
        execute as @e[tag= this, tag= ER.animal.model] run function eternal_return:entity/animal/boar/script/optimize/disable_model

execute if entity @s[tag= ER.optimized] at @s \
        if entity @p[distance=..20                   ] run function eternal_return:entity/animal/boar/script/optimize/enable_model




#> HP <= 0
execute if score #this.HP ER.sys matches ..0 run function eternal_return:entity/animal/boar/script/death_behav

#> HP > 0
execute if score #this.HP ER.sys matches 1.. run function eternal_return:entity/animal/boar/script/alive_behav



# 히트박스 위치 조정
execute at @s run tp @e[tag= this, tag= ER.animal.hitbox] ~ ~ ~
# 다음 엔티티를 위해 모든 boolean 태그 제거 및 this 태그 제거
scoreboard players set #MotionExist ER.sys 0
scoreboard players set #this.AI ER.sys 0
tag @e[tag=this] remove this

