# 생나 클릭
execute if entity @s[tag=click_tree,tag=!colleting_tree] if entity @n[type=interaction,tag=tree,limit=1,distance=..3] run tag @s add show_tree_loading
# 반 채굴 후 이탈
execute if entity @n[tag=tree,type=interaction,distance=..3] run tag @s[tag=pauseload,tag=colleting_tree,tag=done.half.loading,tag=click_tree] remove pauseload 
execute if entity @n[tag=tree,type=interaction,distance=3..11] run tag @s[tag=colleting_tree,tag=done.half.loading] add pauseload
# 반 채굴 전 이탈
execute if entity @n[tag=tree,type=interaction,distance=3..11] run tag @s[tag=colleting_tree,tag=!done.half.loading] add stopload
execute if entity @n[tag=tree,type=interaction,distance=3..11] run playsound minecraft:er.ui.error_sound1 master @s[tag=colleting_tree,tag=!done.half.loading] ~ ~ ~ 1
execute if entity @n[tag=tree,type=interaction,distance=3..11] run tag @s[tag=colleting_tree,tag=!done.half.loading] remove colleting_tree
# 완전히 멀리 갔을때
execute if entity @s[tag=colleting_tree,tag=done.half.loading] if entity @n[tag=tree,type=interaction,distance=11..15] run tag @s add stopload
execute if entity @s[tag=colleting_tree,tag=done.half.loading] if entity @n[tag=tree,type=interaction,distance=11..15] run playsound minecraft:er.ui.error_sound1 master @s ~ ~ ~ 1
execute if entity @s[tag=colleting_tree,tag=done.half.loading] if entity @n[tag=tree,type=interaction,distance=11..15] run tag @s remove colleting_tree

# 클릭 태그 제거
tag @s remove click_tree

