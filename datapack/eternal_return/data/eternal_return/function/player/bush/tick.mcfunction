## 큰 잔디에 들어갈 때
execute if block ~ ~ ~ dead_bush run tag @s add hide_in_bush
execute if block ~ ~ ~ dead_bush run team join bush @s

## 큰 잔디에서 나올 때
execute if block ~ ~ ~ air run tag @s[tag=hide_in_bush] remove hide_in_bush
execute if block ~ ~ ~ air run team join player @s

# 잔디 안에 있을 때
execute if entity @s[tag=hide_in_bush] run function eternal_return:player/armors/clear
execute if entity @s[tag=hide_in_bush] run effect give @s invisibility 1 1 true

# 잔디 밖에 있을 때
execute unless entity @s[tag=hide_in_bush] run effect clear @s invisibility
# 가죽
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.head #leather_armor run function eternal_return:player/armors/leather/head
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.chest #leather_armor run function eternal_return:player/armors/leather/chest
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.legs #leather_armor run function eternal_return:player/armors/leather/legs
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.feet #leather_armor run function eternal_return:player/armors/leather/feet
# 구리
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.head #copper_armor run function eternal_return:player/armors/copper/head
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.chest #copper_armor run function eternal_return:player/armors/copper/chest
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.legs #copper_armor run function eternal_return:player/armors/copper/legs
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.feet #copper_armor run function eternal_return:player/armors/copper/feet
# 사슬
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.head #chainmail_armor run function eternal_return:player/armors/chainmail/head
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.chest #chainmail_armor run function eternal_return:player/armors/chainmail/chest
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.legs #chainmail_armor run function eternal_return:player/armors/chainmail/legs
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.feet #chainmail_armor run function eternal_return:player/armors/chainmail/feet
# 금
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.head #golden_armor run function eternal_return:player/armors/golden/head
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.chest #golden_armor run function eternal_return:player/armors/golden/chest
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.legs #golden_armor run function eternal_return:player/armors/golden/legs
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.feet #golden_armor run function eternal_return:player/armors/golden/feet
# 철
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.head #iron_armor run function eternal_return:player/armors/iron/head
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.chest #iron_armor run function eternal_return:player/armors/iron/chest
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.legs #iron_armor run function eternal_return:player/armors/iron/legs
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.feet #iron_armor run function eternal_return:player/armors/iron/feet
# 다이아몬드
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.head #diamond_armor run function eternal_return:player/armors/diamond/head
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.chest #diamond_armor run function eternal_return:player/armors/diamond/chest
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.legs #diamond_armor run function eternal_return:player/armors/diamond/legs
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.feet #diamond_armor run function eternal_return:player/armors/diamond/feet

# 네더라이트
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.head #netherite_armor run function eternal_return:player/armors/netherite/head
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.chest #netherite_armor run function eternal_return:player/armors/netherite/chest
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.legs #netherite_armor run function eternal_return:player/armors/netherite/legs
execute unless entity @s[tag=hide_in_bush] if items entity @s armor.feet #netherite_armor run function eternal_return:player/armors/netherite/feet