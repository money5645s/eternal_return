$execute if score #timer.sec CT1 matches ..9 run data modify storage minecraft:text timer_char set value ["0","$(sec)"]
$execute if score #timer.sec CT1 matches 10.. run data modify storage minecraft:text timer_char set value ["$(sec)"]