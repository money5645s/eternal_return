#gamerule sendCommandFeedback false

#gamerule commandBlockOutput false

say Start Install EternalRetrun Beta ...

function df_library:init
setblock 0 -63 0 oak_sign

##system values
scoreboard objectives add ER.sys dummy
scoreboard objectives add ER.health dummy

scoreboard objectives add ER.UUID.0 dummy
scoreboard objectives add ER.UUID.1 dummy
scoreboard objectives add ER.UUID.2 dummy
scoreboard objectives add ER.UUID.3 dummy

scoreboard objectives add click minecraft.used:minecraft.carrot_on_a_stick
scoreboard objectives add f_click dummy


say DONE !








