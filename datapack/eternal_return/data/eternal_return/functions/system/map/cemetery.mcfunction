#묘지
execute as @a[x=-624,y=70,z=383,dx=60,dy=30,dz=48] run tag @s add cemetery
execute as @a[x=-555,y=70,z=500,dx=-55,dy=30,dz=-69] run tag @s add cemetery
execute as @a[x=-569,y=70,z=501,dx=2,dy=30,dz=3] run tag @s add cemetery
execute as @a[x=-578,y=70,z=501,dx=-4,dy=30,dz=3] run tag @s add cemetery

execute as @a[tag=cemetery] unless entity @s[x=-624,y=70,z=383,dx=60,dy=30,dz=48] unless entity @s[x=-555,y=70,z=500,dx=-55,dy=30,dz=-69] unless entity @s[x=-569,y=70,z=501,dx=2,dy=30,dz=3] unless entity @s[x=-578,y=70,z=501,dx=-4,dy=30,dz=3] run tag @s remove cemetery