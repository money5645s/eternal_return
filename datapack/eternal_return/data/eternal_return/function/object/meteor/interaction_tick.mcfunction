## 주변에 로딩이 끝난 플레이어가 있을 때
setblock ~ ~ ~ air
kill @e[type=marker,tag=impacted,limit=1,distance=..2]
kill @s
give @a[distance=..11,tag=done.loading] slime_ball[lore=[{"color":"#993bda","italic":false,"text":"영웅"},{"color":"gray","italic":false,"text":"재료"}],tooltip_style="meteorite",max_stack_size=3] 1
tag @a[distance=..11,tag=done.loading] remove done.loading
tag @a[distance=..11,tag=!done.loading,tag=colleting_meteorite] add stopload
tag @a[distance=..11,tag=colleting_meteorite] remove colleting_meteorite
