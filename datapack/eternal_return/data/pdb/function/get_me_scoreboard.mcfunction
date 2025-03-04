execute store result storage pdb:main args.UUID0 int 1 run scoreboard players get @s UUID0
execute store result storage pdb:main args.UUID1 int 1 run scoreboard players get @s UUID1
execute store result storage pdb:main args.UUID2 int 1 run scoreboard players get @s UUID2
execute store result storage pdb:main args.UUID3 int 1 run scoreboard players get @s UUID3

function pdb:private/get_me with storage pdb:main args
data modify storage pdb:main in set from storage pdb:main out