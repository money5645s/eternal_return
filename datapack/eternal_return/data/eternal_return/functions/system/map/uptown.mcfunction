#고급 주택가
execute as @a[x=-506,y=70,z=442,dx=42,dy=30,dz=38] run tag @s add uptown
execute as @a[x=-485,y=70,z=455,dx=113,dy=30,dz=73] run tag @s add uptown
execute as @a[x=-443,y=70,z=530,dx=-42,dy=30,dz=-4] run tag @s add uptown

execute as @a[tag=uptown] unless entity @s[x=-506,y=70,z=442,dx=42,dy=30,dz=38] unless entity @s[x=-485,y=70,z=455,dx=113,dy=30,dz=73] unless entity @s[x=-443,y=70,z=530,dx=-42,dy=30,dz=-4] run tag @s remove uptown