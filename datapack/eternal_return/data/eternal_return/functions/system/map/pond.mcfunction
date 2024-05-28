#연못
execute as @a[x=-652,y=70,z=430,dx=32,dy=30,dz=83] run tag @s add pond
execute as @a[x=-652,y=70,z=513,dx=-13,dy=30,dz=-49] run tag @s add pond
execute as @a[x=-688,y=70,z=489,dx=22,dy=30,dz=24] run tag @s add pond

execute as @a[tag=pond] unless entity @s[x=-652,y=70,z=430,dx=32,dy=30,dz=83] unless entity @s[x=-652,y=70,z=513,dx=-13,dy=30,dz=-49] unless entity @s[x=-688,y=70,z=489,dx=22,dy=30,dz=24] run tag @s remove pond