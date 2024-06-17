# 날짜 입력 받기
$scoreboard players set #input.life_of_tree.day NUM $(day)





# 에러 메시지
$execute unless score #input.life_of_tree.day NUM matches 2..3 run tellraw @s [{"text":"$(day)일차에는 생명의 나무를 소환할 수 없습니다","color":"red"}]
execute if score #tree_of_life.summon CT1 matches 1..1199 run tellraw @s [{"text":"생명의 나무를 이미 소환하고 있습니다","color":"red"}]

# 스코어 부여
execute unless score #tree_of_life.summon CT1 matches 1..1199 if score #input.life_of_tree.day NUM matches 2 run tellraw @a [{"text":"[0","color":"gold"},{"score":{"name":"#timer.min","objective":"CT1"}},":",{"score":{"name":"#timer.sec","objective":"CT1"}},"] ",{"text":"1분 후에 호텔, 묘지, 절에서 생명의 나무가 나타납니다."}]
execute unless score #tree_of_life.summon CT1 matches 1..1199 if score #input.life_of_tree.day NUM matches 3 run tellraw @a [{"text":"[0","color":"gold"},{"score":{"name":"#timer.min","objective":"CT1"}},":",{"score":{"name":"#timer.sec","objective":"CT1"}},"] ",{"text":"1분 후에 숲, 개울에서 생명의 나무가 나타납니다."}]
execute unless score #tree_of_life.summon CT1 matches 1..1199 if score #input.life_of_tree.day NUM matches 2..3 run advancement revoke @a only eternal_return:tree_of_life_1min
execute unless score #tree_of_life.summon CT1 matches 1..1199 if score #input.life_of_tree.day NUM matches 2..3 run advancement grant @a only eternal_return:tree_of_life_1min
execute unless score #tree_of_life.summon CT1 matches 1..1199 if score #input.life_of_tree.day NUM matches 2..3 run scoreboard players set #tree_of_life.summon CT1 1
