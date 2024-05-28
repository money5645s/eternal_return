#창고
execute as @a[x=-506,y=70,z=395,dx=75,dy=30,dz=47] run tag @s add warehouse
execute as @a[x=-407,y=70,z=398,dx=-63,dy=30,dz=57] run tag @s add warehouse

execute as @a[tag=warehouse] unless entity @s[x=-506,y=70,z=395,dx=75,dy=30,dz=47] unless entity @s[x=-407,y=70,z=398,dx=-63,dy=30,dz=57] run tag @s remove warehouse