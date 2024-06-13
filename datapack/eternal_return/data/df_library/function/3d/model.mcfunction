# posX posY posZ
# Tags
# ModelItem
# CustomModelData
# 기본적으로 execute as <player>로 실행 시 플레이어 위치에서 소환됨


$execute at @s run summon armor_stand ^$(x) ^$(y) ^$(z) {NoGravity:1b,Silent:1b,Invulnerable:1b,Marker:1b,Invisible:1b,Tags:$(Tags),Pose:{Head:$(Head)},ArmorItems:[{},{},{},{id:"$(modelItem)",Count:1b,components:{"minecraft:custom_model_data":$(CustomModelData)}}]}

