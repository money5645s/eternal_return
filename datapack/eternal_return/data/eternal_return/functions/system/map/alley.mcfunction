#골목길
execute as @a[x=-711,y=104,z=676,dx=-55,dy=-32,dz=-60] at @s run tag @s add alley
execute as @a[x=-766,y=72,z=616,dx=19,dy=28,dz=-29] at @s run tag @s add alley

execute as @a[tag=alley] unless entity @s[x=-711,y=104,z=676,dx=-55,dy=-32,dz=-60] unless entity @s[x=-766,y=72,z=616,dx=19,dy=28,dz=-29] at @s run tag @s remove alley