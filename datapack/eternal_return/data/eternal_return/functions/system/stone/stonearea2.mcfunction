execute as @e[tag=st2] at @s run playsound minecraft:entity.generic.explode player @a ~ ~ ~ 1 0.5
execute as @e[tag=st2] at @s run playsound minecraft:block.amethyst_block.break player @a ~ ~ ~ 2 0.5
execute as @e[tag=st2] at @s run particle explosion_emitter ~ ~ ~ 0 0 0 0 1 force
execute as @e[tag=st2] at @s run setblock ~ ~ ~ sculk
execute as @a[tag=player] at @s if entity @e[tag=st2,distance=..1] run damage @s 10 generic
execute as @e[tag=st2,name="골목길"] at @s run summon minecraft:interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["alleystone","stone"]}
execute as @e[tag=st2,name="양궁장"] at @s run summon minecraft:interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["archerystone","stone"]}
execute as @e[tag=st2,name="모래사장"] at @s run summon minecraft:interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["beachstone","stone"]}
execute as @e[tag=st2,name="성당"] at @s run summon minecraft:interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["chapelstone","stone"]}
execute as @e[tag=st2,name="항구"] at @s run summon minecraft:interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["dockstone","stone"]}
execute as @e[tag=st2,name="공장"] at @s run summon minecraft:interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["factorystone","stone"]}
execute as @e[tag=st2,name="소방서"] at @s run summon minecraft:interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["firestone","stone"]}
execute as @e[tag=st2,name="주유소"] at @s run summon minecraft:interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["gasstone","stone"]}
execute as @e[tag=st2,name="병원"] at @s run summon minecraft:interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["hospitalstone","stone"]}
execute as @e[tag=st2,name="경찰서"] at @s run summon minecraft:interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["policestone","stone"]}
execute as @e[tag=st2,name="연못"] at @s run summon minecraft:interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["pondstone","stone"]}
execute as @e[tag=st2,name="학교"] at @s run summon minecraft:interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["schoolstone","stone"]}
execute as @e[tag=st2,name="고급 주택가"] at @s run summon minecraft:interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["uptownstone","stone"]}
execute as @e[tag=st2,name="창고"] at @s run summon minecraft:interaction ~ ~ ~ {width:1.1f,height:1.1f,Tags:["warehousestone","stone"]}


