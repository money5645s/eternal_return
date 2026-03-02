$execute if score #pedzero TEMP matches 0..9 run data modify storage temp pedzero.result set value "\u100$(input)"
$execute if score #pedzero TEMP matches 10..99 run data modify storage temp pedzero.result set value "\u10$(input)"
$execute if score #pedzero TEMP matches 100..999 run data modify storage temp pedzero.result set value "\u1$(input)"
$execute if score #pedzero TEMP matches 1000..9999 run data modify storage temp pedzero.result set value "\u$(input)"