# 구매 실패
execute as @s at @s[scores={Cradit=..249}] run playsound minecraft:block.anvil.land master @s ~ ~ ~ 1 2

# 구매 성공
execute as @s at @s[scores={Cradit=250..}] run playsound minecraft:entity.player.levelup master @s ~ ~ ~ 1 2
give @s[scores={Cradit=250..}] tide_armor_trim_smithing_template[item_name='{"bold":true,"color":"light_purple","italic":false,"text":"미스릴"}'] 1
scoreboard players remove @s[scores={Cradit=250..}] Cradit 250