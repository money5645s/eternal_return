item replace entity @s container.1 with carrot_on_a_stick[item_name='{"color":"gray","text":"[ 달의 검 ]"}',lore=['{"italic":true,"text":"이름만 달의 검이지 이건 총이다."}']] 1
item replace entity @s container.3 with minecraft:splash_potion[item_name='{"color":"aqua","text":"[ 커스 ]"}',lore=['{"italic":true,"text":"상대에게 적중 시 5초간 디버프가 걸린다."}','{"italic":true,"text":"디버프에 걸린 적은 받는 총알 데미지가 2 증가한다."}'],hide_additional_tooltip={},potion_contents={custom_color:3735518,custom_effects:[{id:"minecraft:unluck",amplifier:1,duration:100,show_particles:0b,show_icon:1b,ambient:0b}]}] 1
item replace entity @s container.4 with wolf_armor[item_name='{"color":"gold","text":"[ 집중력 ]"}',max_damage=12,damage=0]
item replace entity @s container.6 with stone_button[item_name='{"color":"gray","text":"총알"}'] 6

scoreboard players set @s happybullet 6
scoreboard players set @s happyfocus 12