#주유소
execute as @a[x=-649,y=82,z=613,dx=-62,dy=21,dz=94] at @s run tag @s add gasstation
execute as @a[x=-711,y=103,z=707,dx=-17,dy=-21,dz=-31] at @s run tag @s add gasstation

execute as @a[tag=gasstation] unless entity @s[x=-649,y=82,z=613,dx=-62,dy=21,dz=94] unless entity @s[x=-711,y=103,z=707,dx=-17,dy=-21,dz=-31] at @s run tag @s remove gasstation