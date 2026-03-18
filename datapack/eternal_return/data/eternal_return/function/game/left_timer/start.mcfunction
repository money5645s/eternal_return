

# 게임 시작


execute if score left.sec time matches 1 if score left.tick time matches 1 run gamemode spectator @a
execute if score left.sec time matches 1 if score left.tick time matches 1 run title @a[tag=player] times 10 30 10
execute if score left.sec time matches 1 if score left.tick time matches 1 run title @a[tag=player] title [{"text":"b","font":"mono_color"}]
execute if score left.sec time matches 1 if score left.tick time matches 2 run tag @a[tag=player] add freeze
execute if score left.sec time matches 1 if score left.tick time matches 13 run tag @a[tag=player] remove bos.waitbar
execute if score left.sec time matches 1 if score left.tick time matches 19 run scoreboard players set start.tick time 120
execute if score left.sec time matches 1 if score left.tick time matches 17 run tag @a[tag=player] add bos.start
execute if score left.sec time matches 1 if score left.tick time matches 19 run scoreboard players set msg skript 101







## 시작 5초 배너
execute if score start.tick time matches 1.. run scoreboard players remove start.tick time 1
execute if score start.tick time matches 10 run function eternal_return:game/left_timer/start_event