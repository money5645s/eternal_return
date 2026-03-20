## 하이퍼루프
# 상위 함수 :function eternal_return:object/tick
# 입출력 없음

## 클릭감지
execute if entity @e[type=interaction,tag=hyperloop,distance=..1.8,limit=1] run dialog show @s[tag=click_hyperloop,tag=!hyper_loop_activing] eternal_return:hyperloop


execute at @s[tag=hyper_loop_activing] if entity @e[type=interaction,tag=hyperloop,distance=1.8..9] run function eternal_return:object/hyperloop/cancle_warp


execute if score @s select matches 1..19 run tag @s add show_hyperloop_loading
execute if score @s select matches 1..19 run scoreboard players operation @s warp_point = @s select
execute if score @s select matches 1..19 run scoreboard players set @s select -1
execute if entity @s[tag=done.loading,tag=hyper_loop_activing] run function eternal_return:object/hyperloop/warp
tag @s remove click_hyperloop
