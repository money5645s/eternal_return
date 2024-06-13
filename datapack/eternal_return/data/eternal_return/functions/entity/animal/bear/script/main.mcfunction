
#> 곰의 스크립트
#===================================================================================================
#> 야생동물 기본 로직
#
#>if 체력 > 0  :
#>        if AI = 0 :
#>               if 현재 체력 < 최대 체력 :
#>                   AI = 1
#>                   준비 애니메이션 종료()
#>                   스킬 발동()
#>                   쿨타임 = this.MaxCool
#>
#>               if  체력 = 최대 체력 & 플레이어가 근처에 있을 때:
#>                   준비 애니메이션()
#>
#>        if AI = 1 :
#>               if  준비 애니메이션 중 == true :
#>                       준비 애니메이션 종료()
#>
#>               if 모션이 존재하면 & 움직이는 중 == false :
#>                       움직이기()
#>               else if 모션이 존재 == false & 움직이는 중 == true:
#>                       움직이기 중지()
#>
#>               if 플레이어가 전방에 존재 시 :
#>                       if 쿨타임 조건 == false & 공격 중 == false:
#>                               움직이기 중지() 
#>                               모션 제거()
#>                               공격하기()
#>                       else if 쿨타임 조건 == true & 스킬 발동 중 == false:
#>                               움직이기 중지()
#>                               모션 제거()
#>                               스킬 발동()
#>                               쿨타임 = this.MaxCool
#>if 체력 <=0  :
#>        사망()
#>
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
execute as @e[tag=ER.animal.bear, tag=ER.animal.hitbox] if score @s df_id = #this.ID ER.sys run tag @s add this
execute as @e[tag=ER.animal.bear, tag=ER.animal.model ] if score @s df_id = #this.ID ER.sys run tag @s add this
execute as @e[tag=ER.animal.bear, tag=ER.animal.HPbar ] if score @s df_id = #this.ID ER.sys run tag @s add this
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


#> 최적화 [엔티티 쇼 / 노쇼]
function eternal_return:entity/animal/class/optimize/main {animal:"bear",distance: 30}

#> 엔티티의 행동
#> HP > 0
execute if score #this.HP ER.sys matches 1.. if score #this.AI ER.sys matches 0 run function eternal_return:entity/animal/class/ready {animal:"bear"}
execute if score #this.HP ER.sys matches 1.. if score #this.AI ER.sys matches 1 run function eternal_return:entity/animal/class/behav {animal:"bear",damage: 5}



#> HP <= 0
execute if score #this.HP ER.sys matches ..0 run function eternal_return:entity/animal/class/death {animal : "bear"}


# 히트박스 위치 조정
execute at @s run tp @e[tag= this, tag= ER.animal.hitbox] ~ ~ ~
# 다음 엔티티를 위해 모든 boolean 태그 제거 및 this 태그 제거
scoreboard players set #MotionExist ER.sys 0
scoreboard players set #this.AI ER.sys 0
tag @e[tag=this] remove this
