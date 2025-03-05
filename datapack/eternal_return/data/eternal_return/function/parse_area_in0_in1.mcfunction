# 
# def parse_area(
#   parse_area.in0 ER.sys
#   parse_area.in1 ER.sys
#   )

#2비트를 취하고, 2비트만큼 R-shift
scoreboard players operation #alley ER.sys = #parse_area.in0 ER.sys
scoreboard players operation #alley ER.sys %= #4 ER.sys
scoreboard players operation #area_scb1 ER.sys /= #4 ER.sys



