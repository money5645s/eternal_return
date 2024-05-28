execute unless entity @e[tag=noarea,name="골목길"] as @a[tag=player,tag=alley] run tag @s add safearea
execute unless entity @e[tag=noarea,name="양궁장"] as @a[tag=player,tag=archeryrange] run tag @s add safearea
execute unless entity @e[tag=noarea,name="모래사장"] as @a[tag=player,tag=beach] run tag @s add safearea
execute unless entity @e[tag=noarea,name="묘지"] as @a[tag=player,tag=cemetery] run tag @s add safearea
execute unless entity @e[tag=noarea,name="성당"] as @a[tag=player,tag=chapel] run tag @s add safearea
execute unless entity @e[tag=noarea,name="항구"] as @a[tag=player,tag=dock] run tag @s add safearea
execute unless entity @e[tag=noarea,name="공장"] as @a[tag=player,tag=factory] run tag @s add safearea
execute unless entity @e[tag=noarea,name="소방서"] as @a[tag=player,tag=firestation] run tag @s add safearea
execute unless entity @e[tag=noarea,name="숲"] as @a[tag=player,tag=forest] run tag @s add safearea
execute unless entity @e[tag=noarea,name="주유소"] as @a[tag=player,tag=gasstation] run tag @s add safearea
execute unless entity @e[tag=noarea,name="병원"] as @a[tag=player,tag=hospital] run tag @s add safearea
execute unless entity @e[tag=noarea,name="호텔"] as @a[tag=player,tag=hotel] run tag @s add safearea
execute unless entity @e[tag=noarea,name="경찰서"] as @a[tag=player,tag=policestation] run tag @s add safearea
execute unless entity @e[tag=noarea,name="연못"] as @a[tag=player,tag=pond] run tag @s add safearea
execute unless entity @e[tag=noarea,name="학교"] as @a[tag=player,tag=school] run tag @s add safearea
execute unless entity @e[tag=noarea,name="개울"] as @a[tag=player,tag=stream] run tag @s add safearea
execute unless entity @e[tag=noarea,name="절"] as @a[tag=player,tag=temple] run tag @s add safearea
execute unless entity @e[tag=noarea,name="고급 주택가"] as @a[tag=player,tag=uptown] run tag @s add safearea
execute unless entity @e[tag=noarea,name="창고"] as @a[tag=player,tag=warehouse] run tag @s add safearea

execute if entity @e[tag=noarea,name="골목길"] as @a[tag=player,tag=alley] run tag @s remove safearea
execute if entity @e[tag=noarea,name="양궁장"] as @a[tag=player,tag=archeryrange] run tag @s remove safearea
execute if entity @e[tag=noarea,name="모래사장"] as @a[tag=player,tag=beach] run tag @s remove safearea
execute if entity @e[tag=noarea,name="묘지"] as @a[tag=player,tag=cemetery] run tag @s remove safearea
execute if entity @e[tag=noarea,name="성당"] as @a[tag=player,tag=chapel] run tag @s remove safearea
execute if entity @e[tag=noarea,name="항구"] as @a[tag=player,tag=dock] run tag @s remove safearea
execute if entity @e[tag=noarea,name="공장"] as @a[tag=player,tag=factory] run tag @s remove safearea
execute if entity @e[tag=noarea,name="소방서"] as @a[tag=player,tag=firestation] run tag @s remove safearea
execute if entity @e[tag=noarea,name="숲"] as @a[tag=player,tag=forest] run tag @s remove safearea
execute if entity @e[tag=noarea,name="주유소"] as @a[tag=player,tag=gasstation] run tag @s remove safearea
execute if entity @e[tag=noarea,name="병원"] as @a[tag=player,tag=hospital] run tag @s remove safearea
execute if entity @e[tag=noarea,name="호텔"] as @a[tag=player,tag=hotel] run tag @s remove safearea
execute if entity @e[tag=noarea,name="경찰서"] as @a[tag=player,tag=policestation] run tag @s remove safearea
execute if entity @e[tag=noarea,name="연못"] as @a[tag=player,tag=pond] run tag @s remove safearea
execute if entity @e[tag=noarea,name="학교"] as @a[tag=player,tag=school] run tag @s remove safearea
execute if entity @e[tag=noarea,name="개울"] as @a[tag=player,tag=stream] run tag @s remove safearea
execute if entity @e[tag=noarea,name="절"] as @a[tag=player,tag=temple] run tag @s remove safearea
execute if entity @e[tag=noarea,name="고급 주택가"] as @a[tag=player,tag=uptown] run tag @s remove safearea
execute if entity @e[tag=noarea,name="창고"] as @a[tag=player,tag=warehouse] run tag @s remove safearea
execute as @a[tag=player,tag=center] run tag @s remove safearea
execute as @a[tag=player] at @s if block ~ ~ ~ water run tag @s remove safearea