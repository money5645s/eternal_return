## 쿨타임 연산

# 액티브 스킬
    scoreboard players add @s active_tick 1
    execute if score @s active_tick matches 20.. if score @s active_cool matches 1.. run scoreboard players remove @s active_cool 1
    execute if score @s active_tick matches 20.. run scoreboard players set @s active_tick 0
    # 태그 부여
        execute if score @s active_cool matches 0 run tag @s add active_ready
        execute if score @s active_cool matches 1.. run tag @s remove active_ready

# 패시브 스킬
    scoreboard players add @s passive_tick 1
    execute if score @s passive_tick matches 20.. if score @s passive_cool matches 1.. run scoreboard players remove @s passive_cool 1
    execute if score @s passive_tick matches 20.. run scoreboard players set @s passive_tick 0
    # 태그 부여
        execute if score @s passive_cool matches 0 run tag @s add passive_ready
        execute if score @s passive_cool matches 1.. run tag @s remove passive_ready