scoreboard players operation @s aj.id = .aj.last_id aj.id
tag @s remove aj.new
function #animated_java:animal_boar/on_summon/as_rig_entities
execute if entity @s[tag=aj.animal_boar.bone] run function #animated_java:animal_boar/zzzzzzzz/on_summon/as_bones

