#모래사장
execute as @a[x=-460,y=70,z=530,dx=70,dy=30,dz=123] run tag @s add beach
execute as @a[x=-460,y=70,z=530,dx=-25,dy=30,dz=54] run tag @s add beach
execute as @a[x=-460,y=70,z=611,dx=-16,dy=30,dz=8] run tag @s add beach
execute as @a[x=-396,y=70,z=528,dx=-46,dy=30,dz=1] run tag @s add beach

execute as @a[tag=beach] unless entity @s[x=-460,y=70,z=530,dx=70,dy=30,dz=123] unless entity @s[x=-460,y=70,z=530,dx=-25,dy=30,dz=54] unless entity @s[x=-460,y=70,z=611,dx=-16,dy=30,dz=8] unless entity @s[x=-396,y=70,z=528,dx=-46,dy=30,dz=1] run tag @s remove beach