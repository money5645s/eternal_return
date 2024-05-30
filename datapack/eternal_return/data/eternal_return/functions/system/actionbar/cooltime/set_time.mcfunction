# uuid와 초 입력받고 출력
$scoreboard players set #$(uuid).loading.cool CT1 $(sec)
$scoreboard players operation @s CT2 = #$(uuid).loading.cool CT1




$scoreboard players reset #$(uuid).loading.cool CT1