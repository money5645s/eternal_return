
#> 곰의 스크립트
#=================================================================================================== 
#>  function eternal_return:entity/animal/bear/script/main
#>  function eternal_return:entity/animal/bear/script/skill
#
#   Tags
#>  ER.animal.hitbox
#>  ER.animal.root
#>  ER.animal.model
#>  ER.animal.bear
#>  ER.optimized
#
#>  
#                
#===================================================================================================


#> 아이디 체크 및 동일한 아이디의 구성 엔티티에게 this 태그 부여
tag @s add this
execute store result score #this.ID ER.sys run scoreboard players get @s df_id
execute as @e[type= minecraft:ghast,        tag=ER.animal.bear, tag=ER.animal.hitbox] if score @s df_id = #this.ID ER.sys run tag @s add this
execute as @e[type= minecraft:item_display, tag=ER.animal.bear, tag=ER.animal.model ] if score @s df_id = #this.ID ER.sys run tag @s add this
execute as @e[type= minecraft:text_display, tag=ER.animal.bear, tag=ER.animal.HPbar ] if score @s df_id = #this.ID ER.sys run tag @s add this
#> 변수 얻어오기
execute store result score #this.HP ER.sys run scoreboard players get @e[tag= ER.animal.hitbox, tag= this, limit= 1] ER.health
execute store result score #this.MaxHP ER.sys run scoreboard players get @s ER.health
#> AI 체크
execute if entity @s[nbt={NoAI:1b}] run scoreboard players set #this.AI ER.sys 0
execute unless entity @s[nbt={NoAI:1b}] run scoreboard players set #this.AI ER.sys 1

#===================================================================================================
#
#   ER.sys . this.hp   = ER.animal.bear의 현재 체력
#   ER.sys . this.ID   = ER.animal.bear의 df_id (스코어보드가 df_id -> ER.sys로 교체됨)
#   ER.sys . this.AI   = ER.animal.bear의 AI 존재 여부. 1이면 true 0이면 false
#   ER.sys . #motion_x = ER.animal.bear의 현재 모션값 x
#   ER.sys . #motion_y = ER.animal.bear의 현재 모션값 y
#   ER.sys . #motion_z = ER.animal.bear의 현재 모션값 z
#   tag = this : 이 스크립트에서 처리하는 야생동물 구성 엔티티들
#
#===================================================================================================


data modify storage minecraft:temp temp set value {animal : "bear", OPTdistance : 30, damage : 5, attackTick : 7, attackDistance : 4}

#> 최적화 [엔티티 쇼 / 노쇼]
function eternal_return:entity/animal/class/optimize/main with storage minecraft:temp temp


#> 엔티티의 행동
#> HP > 0
execute if score #this.HP ER.sys matches 1.. if score #this.AI ER.sys matches 0 run function eternal_return:entity/animal/class/ready with storage minecraft:temp temp
execute if score #this.HP ER.sys matches 1.. if score #this.AI ER.sys matches 1 run function eternal_return:entity/animal/class/behav with storage minecraft:temp temp

#> HP <= 0
execute if score #this.HP ER.sys matches ..0 run function eternal_return:entity/animal/class/death with storage minecraft:temp temp


# 히트박스 위치 조정
execute at @s run tp @e[tag= this, tag= ER.animal.hitbox] ~ ~ ~
# 다음 엔티티를 위해 모든 boolean 태그 제거 및 this 태그 제거
data remove storage minecraft:temp temp
scoreboard players set #MotionExist ER.sys 0
scoreboard players set #this.AI ER.sys 0
tag @e[tag=this] remove this
