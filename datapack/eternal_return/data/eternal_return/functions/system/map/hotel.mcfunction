#호텔
execute as @a[x=-476,y=70,z=691,dx=-71,dy=30,dz=-107] run tag @s add hotel
execute as @a[x=-476,y=70,z=691,dx=16,dy=30,dz=-72] run tag @s add hotel

execute as @a[tag=hotel] unless entity @s[x=-476,y=70,z=691,dx=-71,dy=30,dz=-107] unless entity @s[x=-476,y=70,z=691,dx=16,dy=30,dz=-72] run tag @s remove hotel