# 구매 실패
execute as @s at @s[scores={Cradit=..349}] run playsound minecraft:block.anvil.land master @s ~ ~ ~ 1 2

# 구매 성공
execute as @s at @s[scores={Cradit=350..}] run playsound minecraft:entity.player.levelup master @s ~ ~ ~ 1 2
give @s[scores={Cradit=350..}] amethyst_shard[item_name='{"bold":true,"color":"light_purple","italic":false,"text":"포스 코어"}'] 1
scoreboard players remove @s[scores={Cradit=350..}] Cradit 350