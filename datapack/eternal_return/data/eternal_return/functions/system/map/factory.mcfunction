#공장
execute as @a[x=-640,y=70,z=383,dx=112,dy=30,dz=-85] run tag @s add factory

execute as @a[tag=factory] unless entity @s[x=-640,y=70,z=383,dx=112,dy=30,dz=-85] run tag @s remove factory