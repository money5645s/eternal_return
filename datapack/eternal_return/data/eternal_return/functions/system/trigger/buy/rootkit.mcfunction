# 구매 실패
execute as @s at @s[scores={Cradit=..149}] run playsound minecraft:block.anvil.land master @s ~ ~ ~ 1 2

# 구매 성공
execute as @s at @s[scores={Cradit=150..}] run playsound minecraft:entity.player.levelup master @s ~ ~ ~ 1 2
give @s[scores={Cradit=150..}] slime_ball[item_name='{"bold":true,"color":"light_purple","italic":false,"text":"루트킷"}'] 1
scoreboard players remove @s[scores={Cradit=150..}] Cradit 150