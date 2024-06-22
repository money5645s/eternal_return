
# 보스바 추가
execute as @a[tag=player] run function eternal_return:system/bossbar/player 


# 대기방 보스바
execute if entity @a[tag=wait_bar] run function eternal_return:system/bossbar/wait_bar