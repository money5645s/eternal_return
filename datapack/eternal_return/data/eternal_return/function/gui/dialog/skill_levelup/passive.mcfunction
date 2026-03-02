
execute if score @s skill_point matches 1.. if score @s passive_level matches ..4 run scoreboard players add @s passive_level 1
execute if score @s skill_point matches 1.. if score @s passive_level matches ..5 run scoreboard players remove @s skill_point 1

function eternal_return:gui/dialog/temp_skill_data
function eternal_return:gui/dialog/skill with storage temp skill