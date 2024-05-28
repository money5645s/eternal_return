#양궁장
execute as @a[x=-649,y=82,z=668,dx=102,dy=23,dz=61] at @s run tag @s add archeryrange
execute as @a[x=-556,y=82,z=654,dx=-57,dy=15,dz=13] at @s run tag @s add archeryrange

execute as @a[tag=archeryrange] unless entity @s[x=-649,y=82,z=668,dx=102,dy=23,dz=61] unless entity @s[x=-556,y=82,z=654,dx=-57,dy=15,dz=13] at @s run tag @s remove archeryrange