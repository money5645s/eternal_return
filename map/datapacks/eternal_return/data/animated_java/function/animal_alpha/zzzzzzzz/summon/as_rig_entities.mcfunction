scoreboard players operation @s aj.id = .aj.last_id aj.id
tag @s remove aj.new
function #animated_java:animal_alpha/on_summon/as_rig_entities
execute if entity @s[tag=aj.animal_alpha.bone] run function #animated_java:animal_alpha/zzzzzzzz/on_summon/as_bones

