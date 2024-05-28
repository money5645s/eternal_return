#연구소
execute as @a[x=-595,y=70,z=504,dx=43,dy=30,dz=43] run tag @s add center
execute as @a[x=-558,y=70,z=550,dx=9,dy=30,dz=-9] run tag @s add center
execute as @a[x=-598,y=70,z=541,dx=9,dy=30,dz=9] run tag @s add center
execute as @a[x=-598,y=70,z=510,dx=9,dy=30,dz=-9] run tag @s add center
execute as @a[x=-558,y=70,z=501,dx=9,dy=30,dz=9] run tag @s add center

execute as @a[x=-577,y=70,z=504,dx=7,dy=30,dz=-4] run tag @s add center
execute as @a[x=-552,y=70,z=522,dx=4,dy=30,dz=7] run tag @s add center
execute as @a[x=-570,y=70,z=547,dx=-7,dy=30,dz=4] run tag @s add center
execute as @a[x=-595,y=70,z=529,dx=-4,dy=30,dz=-7] run tag @s add center

execute as @a[tag=center] unless entity @s[x=-595,y=70,z=504,dx=43,dy=30,dz=43] unless entity @s[x=-558,y=70,z=550,dx=9,dy=30,dz=-9] unless entity @s[x=-598,y=70,z=541,dx=9,dy=30,dz=9] unless entity @s[x=-598,y=70,z=541,dx=9,dy=30,dz=9] unless entity @s[x=-558,y=70,z=501,dx=9,dy=30,dz=9] unless entity @s[x=-577,y=70,z=504,dx=7,dy=30,dz=-4] unless entity @s[x=-552,y=70,z=522,dx=4,dy=30,dz=7] unless entity @s[x=-570,y=70,z=547,dx=-7,dy=30,dz=4] unless entity @s[x=-595,y=70,z=529,dx=-4,dy=30,dz=-7] run tag @s remove center