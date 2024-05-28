#병원
execute as @a[x=-624,y=70,z=430,dx=-80,dy=30,dz=-47] run tag @s add hospital

execute as @a[tag=hospital] unless entity @s[x=-624,y=70,z=430,dx=-80,dy=30,dz=-47] run tag @s remove hospital