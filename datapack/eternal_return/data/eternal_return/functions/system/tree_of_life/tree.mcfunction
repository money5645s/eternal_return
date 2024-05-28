execute as @a[scores={tree=1}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 1% ]","color":"yellow"}]
execute as @a[scores={tree=16}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 10% ]","color":"yellow"}]
execute as @a[scores={tree=32}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 20% ]","color":"yellow"}]
execute as @a[scores={tree=48}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 30% ]","color":"yellow"}]
execute as @a[scores={tree=64}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 40% ]","color":"yellow"}]
execute as @a[scores={tree=80}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 50% ]","color":"yellow"}]
execute as @a[scores={tree=96}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 60% ]","color":"yellow"}]
execute as @a[scores={tree=112}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 70% ]","color":"yellow"}]
execute as @a[scores={tree=128}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 80% ]","color":"yellow"}]
execute as @a[scores={tree=144}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 90% ]","color":"yellow"}]
execute as @a[scores={tree=160}] run tellraw @s ["",{"text":"채집 중입니다. ","color":"green"},{"text":"[ 100% ]","color":"yellow"}]
execute as @a[scores={tree=160..}] run tellraw @s ["",{"text":"채집이 완료되었습니다.","color":"yellow"}]
execute as @a[scores={tree=160..}] run give @s minecraft:oak_sapling[item_name='{"bold":true,"color":"green","text":"생명의 나무"}'] 1
execute as @a[scores={tree=160..}] run scoreboard players reset @s tree