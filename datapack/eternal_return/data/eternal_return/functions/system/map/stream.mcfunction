#개울
execute as @a[x=-704,y=70,z=424,dx=-28,dy=32,dz=83] run tag @s add stream
execute as @a[x=-688,y=70,z=507,dx=-16,dy=26,dz=-74] run tag @s add stream
execute as @a[x=-688,y=70,z=433,dx=36,dy=30,dz=30] run tag @s add stream
execute as @a[x=-675,y=70,z=463,dx=-14,dy=30,dz=20] run tag @s add stream

execute as @a[tag=stream] unless entity @s[x=-704,y=70,z=424,dx=-28,dy=32,dz=83] unless entity @s[x=-688,y=70,z=507,dx=-16,dy=26,dz=-74] unless entity @s[x=-688,y=70,z=433,dx=36,dy=30,dz=30] unless entity @s[x=-675,y=70,z=463,dx=-14,dy=30,dz=20] run tag @s remove stream