#소방서
execute as @a[x=-705,y=80,z=537,dx=56,dy=30,dz=76] at @s run tag @s add firestation
execute as @a[x=-616,y=80,z=584,dx=-66,dy=30,dz=-71] at @s run tag @s add firestation
execute as @a[x=-701,y=80,z=537,dx=19,dy=16,dz=-9] at @s run tag @s add firestation
execute as @a[x=-599,y=80,z=529,dx=-17,dy=24,dz=-16] at @s run tag @s add firestation

execute as @a[tag=firestation] unless entity @s[x=-705,y=80,z=537,dx=56,dy=30,dz=76] unless entity @s[x=-616,y=80,z=584,dx=-66,dy=30,dz=-71] unless entity @s[x=-701,y=80,z=537,dx=19,dy=16,dz=-9] unless entity @s[x=-599,y=80,z=529,dx=-17,dy=24,dz=-16] at @s run tag @s remove firestation
