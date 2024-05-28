#절
execute as @a[x=-747,y=80,z=587,dx=-51,dy=31,dz=-143] run tag @s add temple
execute as @a[x=-732,y=80,z=526,dx=-16,dy=19,dz=-88] run tag @s add temple

execute as @a[tag=temple] unless entity @s[x=-747,y=80,z=587,dx=-51,dy=31,dz=-143] unless entity @s[x=-732,y=80,z=526,dx=-16,dy=19,dz=-88] run tag @s remove temple