
# 지정된 위치로 이동
execute if score @s warp_point matches 1 run tp @s -722 82 639 ~ 0
execute if score @s warp_point matches 2 run tp @s -694 82 682 ~ 0
execute if score @s warp_point matches 3 run tp @s -598 85 703 ~ 0
execute if score @s warp_point matches 4 run tp @s -606 82 627 ~ 0
execute if score @s warp_point matches 5 run tp @s -733 82 550 ~ 0
execute if score @s warp_point matches 6 run tp @s -757 82 496 ~ 0
execute if score @s warp_point matches 7 run tp @s -508 82 630 ~ 0
execute if score @s warp_point matches 8 run tp @s -658 82 594 ~ 0
execute if score @s warp_point matches 9 run tp @s -631 82 500 ~ 0
execute if score @s warp_point matches 10 run tp @s -710 82 440 ~ 0
execute if score @s warp_point matches 11 run tp @s -644 82 403 ~ 0
execute if score @s warp_point matches 12 run tp @s -437 80 549 ~ 0
execute if score @s warp_point matches 13 run tp @s -510 82 538 ~ 0
execute if score @s warp_point matches 14 run tp @s -518 82 424 ~ 0
execute if score @s warp_point matches 15 run tp @s -561 82 444 ~ 0
execute if score @s warp_point matches 16 run tp @s -545 82 341 ~ 0
execute if score @s warp_point matches 17 run tp @s -418 82 478 ~ 0
execute if score @s warp_point matches 18 run tp @s -461 82 418 ~ 0
execute if score @s warp_point matches 19 run tp @s -455 82 370 ~ 0
# 스코어 초기화

execute at @s run particle minecraft:trial_spawner_detection_ominous ~ ~0.5 ~ 0.5 0.6 0.5 0 40 force
scoreboard players reset @s warp_point
# 태그 제거
tag @s remove hyper_loop_activing
tag @s remove done.loading