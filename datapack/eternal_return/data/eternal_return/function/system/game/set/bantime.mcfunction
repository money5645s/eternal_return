$execute if score @s CT3 matches ..9 run data modify storage text bantime_char set value ["0","$(bantime)"]
$execute if score @s CT3 matches 10.. run data modify storage text bantime_char set value ["$(bantime)"]