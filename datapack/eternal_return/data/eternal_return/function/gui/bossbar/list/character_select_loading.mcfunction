$execute if score #sec TEMP matches 0..9 run bossbar set line2.$(UUID0) name [\
    {text:"\$(text)",font:"gui/character/loading",interpret:true,"shadow_color": [0,0,0,0]},\
    {"translate":"space.-320",font:"minecraft:default"},\
    {score:{name:"#sec",objective:"TEMP"},font:"mono/13"},\
    {"translate":"space.310",font:"minecraft:default"}]

$execute if score #sec TEMP matches 10.. run bossbar set line2.$(UUID0) name [\
    {text:"\$(text)",font:"gui/character/loading",interpret:true,"shadow_color": [0,0,0,0]},\
    {"translate":"space.-323",font:"minecraft:default"},\
    {score:{name:"#sec",objective:"TEMP"},font:"mono/13"},\
    {"translate":"space.305",font:"minecraft:default"}]
