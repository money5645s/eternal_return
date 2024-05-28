#학교
execute as @a[x=-547,y=82,z=584,dx=-102,dy=19,dz=70] run tag @s add school
execute as @a[x=-609,y=82,z=654,dx=-40,dy=12,dz=13] run tag @s add school
execute as @a[x=-616,y=70,z=584,dx=66,dy=30,dz=-33] run tag @s add school
execute as @a[x=-564,y=70,z=547,dx=-5,dy=30,dz=3] run tag @s add school
execute as @a[x=-583,y=70,z=547,dx=5,dy=30,dz=3] run tag @s add school

execute as @a[tag=school] unless entity @s[x=-547,y=82,z=584,dx=-102,dy=19,dz=70] unless entity @s[x=-609,y=82,z=654,dx=-40,dy=12,dz=13] unless entity @s[x=-616,y=70,z=584,dx=66,dy=30,dz=-33] unless entity @s[x=-564,y=70,z=547,dx=-5,dy=30,dz=3] unless entity @s[x=-583,y=70,z=547,dx=5,dy=30,dz=3] run tag @s remove school