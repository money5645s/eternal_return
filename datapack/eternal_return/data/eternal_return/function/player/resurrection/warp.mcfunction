
# 지정된 위치로 이동
execute if score @s select matches 401 run tp @s -722 82 639 ~ 0
execute if score @s select matches 402 run tp @s -694 82 682 ~ 0
execute if score @s select matches 403 run tp @s -598 85 703 ~ 0
execute if score @s select matches 404 run tp @s -606 82 627 ~ 0
execute if score @s select matches 405 run tp @s -733 82 550 ~ 0
execute if score @s select matches 406 run tp @s -757 82 496 ~ 0
execute if score @s select matches 407 run tp @s -508 82 630 ~ 0
execute if score @s select matches 408 run tp @s -658 82 594 ~ 0
execute if score @s select matches 409 run tp @s -631 82 500 ~ 0
execute if score @s select matches 410 run tp @s -710 82 440 ~ 0
execute if score @s select matches 411 run tp @s -644 82 403 ~ 0
execute if score @s select matches 412 run tp @s -437 80 549 ~ 0
execute if score @s select matches 413 run tp @s -510 82 538 ~ 0
execute if score @s select matches 414 run tp @s -518 82 424 ~ 0
execute if score @s select matches 415 run tp @s -561 82 444 ~ 0
execute if score @s select matches 416 run tp @s -545 82 341 ~ 0
execute if score @s select matches 417 run tp @s -418 82 478 ~ 0
execute if score @s select matches 418 run tp @s -461 82 418 ~ 0
execute if score @s select matches 419 run tp @s -455 82 370 ~ 0
tag @s remove freeze
tag @s remove is_resurretion
effect clear @s
gamemode adventure @s
# 스코어 초기화
execute at @s run playsound minecraft:er.player.max_credit master @s ~ ~ ~
execute at @s run particle minecraft:trial_spawner_detection ~ ~0.5 ~ 0.5 0.6 0.5 0 40 force
scoreboard players set @s select -1
# 화면 끄기
dialog clear @s
scoreboard players reset @s resurretion.sec
scoreboard players set @s bantime 10