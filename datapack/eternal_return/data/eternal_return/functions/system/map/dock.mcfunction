#항구
execute as @a[x=-528,y=70,z=346,dx=121,dy=30,dz=37] run tag @s add dock
execute as @a[x=-407,y=70,z=383,dx=-99,dy=30,dz=12] run tag @s add dock
execute as @a[x=-407,y=70,z=383,dx=-24,dy=30,dz=15] run tag @s add dock

execute as @a[tag=dock] unless entity @s[x=-528,y=70,z=346,dx=121,dy=30,dz=37] unless entity @s[x=-407,y=70,z=383,dx=-99,dy=30,dz=12] unless entity @s[x=-407,y=70,z=383,dx=-24,dy=30,dz=15] run tag @s remove dock