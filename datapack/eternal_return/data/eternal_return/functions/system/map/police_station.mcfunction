#경찰서
execute as @a[x=-747,y=72,z=616,dx=42,dy=53,dz=-90] at @s run tag @s add policestation
execute as @a[x=-682,y=82,z=507,dx=-50,dy=42,dz=21] at @s run tag @s add policestation
execute as @a[x=-704,y=82,z=527,dx=3,dy=7,dz=10] at @s run tag @s add policestation

execute as @a[tag=policestation] unless entity @s[x=-747,y=72,z=616,dx=42,dy=53,dz=-90] unless entity @s[x=-682,y=82,z=507,dx=-50,dy=42,dz=21] unless entity @s[x=-704,y=82,z=527,dx=3,dy=7,dz=10] at @s run tag @s remove policestation