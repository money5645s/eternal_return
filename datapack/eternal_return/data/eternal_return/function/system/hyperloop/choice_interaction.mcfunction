$execute unless predicate eternal_return:is_sneaking positioned ~ ~1.3 ~ run tp @e[tag=choice_warp$(id)] ~ ~ ~
$execute if predicate eternal_return:is_sneaking positioned ~ ~1 ~ run tp @e[tag=choice_warp$(id)] ~ ~ ~
$execute as @e[tag=choice_warp$(id)] at @s on attacker run function eternal_return:system/hyperloop/display