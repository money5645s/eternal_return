#숲
execute as @a[x=-485,y=70,z=480,dx=-63,dy=30,dz=104] run tag @s add forest
execute as @a[x=-548,y=100,z=584,dx=-2,dy=-30,dz=-25] run tag @s add forest
execute as @a[x=-552,y=70,z=530,dx=3,dy=30,dz=11] run tag @s add forest
execute as @a[x=-549,y=70,z=510,dx=-3,dy=30,dz=11] run tag @s add forest

execute as @a[tag=forest] unless entity @s[x=-485,y=70,z=480,dx=-63,dy=30,dz=104] unless entity @s[x=-548,y=100,z=584,dx=-2,dy=-30,dz=-25] unless entity @s[x=-552,y=70,z=530,dx=3,dy=30,dz=11] unless entity @s[x=-549,y=70,z=510,dx=-3,dy=30,dz=11] run tag @s remove forest
