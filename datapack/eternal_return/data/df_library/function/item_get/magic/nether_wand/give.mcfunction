


data modify storage df_temp temp.customModelData set value 3001
data modify storage df_temp temp.custom_data set value {               \
        itemStatNeed:{     pow:  0, dex:  0, mp:25  },                 \
        itemStatBonus:{    pow:  0, dex:  0, mp:15  },                 \
        itemHolder:{       pow: -1, dex: -1, mp:-1  },                 \
        itemStatGrad:{     pow:  0, dex:  0, mp: 1.2},                 \
        itemGrade:{                                                    \
            temp:"",                                                   \
            Grade:"I",                                                 \
            Letter:["I","II","III","IV","V","VI","VII","VIII","IX","X"]\
            },                                                         \
        itemBasicDMG:1,                                                \
        itemDMG:1,                                                     \
        Tags:["weapon","nether_wand","magic_wand"]}

data modify storage df_temp temp.ID set value "minecraft:carrot_on_a_stick"
data modify storage df_temp temp.ItemName set value '{"italic":false,"bold":true,"color":gold,"text":"지옥나무 지팡이"}'
data modify storage df_temp temp.Line set value []

function df_library:item_get/attribute/set_grade with storage df_temp temp.custom_data.itemGrade
function df_library:item_get/attribute/set_lore {text:""}
function df_library:item_get/attribute/set_lore {text:"<Description> : "}
function df_library:item_get/attribute/set_lore {text:"마법을 다루기 위한 촉매."}
function df_library:item_get/attribute/set_lore {text:"그 촉매에 마법을 담아 사용할 수 있다."}
function df_library:item_get/attribute/set_lore {text:"지옥에서 자라나는 나무로 만들어 매우 튼튼하며"}
function df_library:item_get/attribute/set_lore {text:"또한 강력한 마법을 구사할 수 있다."}
function df_library:item_get/attribute/set_stat_need with storage df_temp temp.custom_data.itemStatNeed
function df_library:item_get/attribute/set_stat_bonus with storage df_temp temp.custom_data.itemStatBonus
function df_library:item_get/get with storage df_temp temp

#data modify storage df_temp temp set value {}
