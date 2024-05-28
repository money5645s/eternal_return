#성당
execute as @a[x=-564,y=70,z=383,dx=58,dy=30,dz=48] run tag @s add chapel
execute as @a[x=-506,y=70,z=480,dx=-49,dy=30,dz=-48] run tag @s add chapel

execute as @a[tag=chapel] unless entity @s[x=-564,y=70,z=383,dx=58,dy=30,dz=48] unless entity @s[x=-506,y=70,z=480,dx=-49,dy=30,dz=-48] run tag @s remove chapel