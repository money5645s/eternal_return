execute unless entity @e[tag=noarea,name="골목길"] run tag @a[tag=player,tag=alley] add safearea
execute unless entity @e[tag=noarea,name="양궁장"] run tag @a[tag=player,tag=archery_range] add safearea
execute unless entity @e[tag=noarea,name="모래사장"] run tag @a[tag=player,tag=beach] add safearea
execute unless entity @e[tag=noarea,name="묘지"] run tag @a[tag=player,tag=cemetery] add safearea
execute unless entity @e[tag=noarea,name="성당"] run tag @a[tag=player,tag=chapel] add safearea
execute unless entity @e[tag=noarea,name="항구"] run tag @a[tag=player,tag=dock] add safearea
execute unless entity @e[tag=noarea,name="공장"] run tag @a[tag=player,tag=factory] add safearea
execute unless entity @e[tag=noarea,name="소방서"] run tag @a[tag=player,tag=fire_station] add safearea
execute unless entity @e[tag=noarea,name="숲"] run tag @a[tag=player,tag=forest] add safearea
execute unless entity @e[tag=noarea,name="주유소"] run tag @a[tag=player,tag=gas_station] add safearea
execute unless entity @e[tag=noarea,name="병원"] run tag @a[tag=player,tag=hospital] add safearea
execute unless entity @e[tag=noarea,name="호텔"] run tag @a[tag=player,tag=hotel] add safearea
execute unless entity @e[tag=noarea,name="경찰서"] run tag @a[tag=player,tag=police_station] add safearea
execute unless entity @e[tag=noarea,name="연못"] run tag @a[tag=player,tag=pond] add safearea
execute unless entity @e[tag=noarea,name="학교"] run tag @a[tag=player,tag=school] add safearea
execute unless entity @e[tag=noarea,name="개울"] run tag @a[tag=player,tag=stream] add safearea
execute unless entity @e[tag=noarea,name="절"] run tag @a[tag=player,tag=temple] add safearea
execute unless entity @e[tag=noarea,name="고급 주택가"] run tag @a[tag=player,tag=uptown] add safearea
execute unless entity @e[tag=noarea,name="창고"] run tag @a[tag=player,tag=warehouse] add safearea

execute if entity @e[tag=noarea,name="골목길"] run tag @a[tag=player,tag=alley] remove safearea
execute if entity @e[tag=noarea,name="양궁장"] run tag @a[tag=player,tag=archery_range] remove safearea
execute if entity @e[tag=noarea,name="모래사장"] run tag @a[tag=player,tag=beach] remove safearea
execute if entity @e[tag=noarea,name="묘지"] run tag @a[tag=player,tag=cemetery] remove safearea
execute if entity @e[tag=noarea,name="성당"] run tag @a[tag=player,tag=chapel] remove safearea
execute if entity @e[tag=noarea,name="항구"] run tag @a[tag=player,tag=dock] remove safearea
execute if entity @e[tag=noarea,name="공장"] run tag @a[tag=player,tag=factory] remove safearea
execute if entity @e[tag=noarea,name="소방서"] run tag @a[tag=player,tag=fire_station] remove safearea
execute if entity @e[tag=noarea,name="숲"] run tag @a[tag=player,tag=forest] remove safearea
execute if entity @e[tag=noarea,name="주유소"] run tag @a[tag=player,tag=gas_station] remove safearea
execute if entity @e[tag=noarea,name="병원"] run tag @a[tag=player,tag=hospital] remove safearea
execute if entity @e[tag=noarea,name="호텔"] run tag @a[tag=player,tag=hotel] remove safearea
execute if entity @e[tag=noarea,name="경찰서"] run tag @a[tag=player,tag=police_station] remove safearea
execute if entity @e[tag=noarea,name="연못"] run tag @a[tag=player,tag=pond] remove safearea
execute if entity @e[tag=noarea,name="학교"] run tag @a[tag=player,tag=school] remove safearea
execute if entity @e[tag=noarea,name="개울"] run tag @a[tag=player,tag=stream] remove safearea
execute if entity @e[tag=noarea,name="절"] run tag @a[tag=player,tag=temple] remove safearea
execute if entity @e[tag=noarea,name="고급 주택가"] run tag @a[tag=player,tag=uptown] remove safearea
execute if entity @e[tag=noarea,name="창고"] run tag @a[tag=player,tag=warehouse] remove safearea
tag @a[tag=player,tag=center] remove safearea
execute at @a[tag=player] if block ~ ~ ~ water run tag @s remove safearea