
$dialog show @s {\
  "type": "minecraft:multi_action","title": {"text": "","extra": [{"translate":"space.215"}]},"external_title": "",\
  "body": [\
    {"type": "minecraft:plain_message","width": 210,"contents": [ {"text": "\n\n\n\n\n\n\n\n\n\n\n\n"},\
    {"text":"1","font":"gui/quick_action/bg","shadow_color":[0,0,0,0]},\
    {"text":"a","font":"gui/quick_action/bg","shadow_color":[0,0,0,0]},\
    {"text":"\n"},\
    {"translate": "space.-2"},\
    {"text":"$(active_level_temp)","font":"gui/quick_action/active_level","shadow_color":[0,0,0,0]},\
    {"translate": "space.15"},\
    {"text":"$(passive_level_temp)","font":"gui/quick_action/passive_level","shadow_color":[0,0,0,0]},\
    {"translate": "space.-95"},\
    {"text": "\u$(character_profill)","font":"gui/quick_action/active","shadow_color": [0,0,0,0],"click_event": {"action": "run_command","command": "/trigger select set 51"},"hover_event": {"action": "show_text","value": [\
      {"text":"$(aname) (레벨 $(active_level_temp)) [Q]","color":"gold"},{"text":" - 액티브 스킬 \n","color":"gray"},{"text":"쿨다운 $(acool) (s)\n\n","color":"white"},{"text":"$(alore1)\n$(alore2)\n$(alore3)\n$(alore4)","color":"#b3b3b3"}]}},\
    {"translate": "space.19"},\
    {"text": "\u$(character_profill)","font":"gui/quick_action/passive","shadow_color": [0,0,0,0],"click_event": {"action": "run_command","command": "/trigger select set 52"},"hover_event": {"action": "show_text","value": [\
      {"text":"$(pname) (레벨 $(passive_level_temp))","color":"gold"},{"text":" - 패시브 스킬 \n","color":"gray"},{"text":"쿨다운 $(pcool)\n\n","color":"white"},{"text":"$(plore1)\n$(plore2)\n$(plore3)\n$(plore4)","color":"#b3b3b3"}]}},\
    {"text":"\n\n"},\
    {"text":"2","font":"gui/quick_action/bg","shadow_color":[0,0,0,0],"click_event": {"action": "run_command","command": "/trigger select set 53"},"hover_event": {"action": "show_text","value": [{"text":"원격 드론"}]}},\
    {"translate": "space.135"},\
    {"text":"\n"}]}\
  ],"inputs": [],"columns": 5,"pause": false,"can_close_with_escape": true,\
"actions": [{"label": "나가기","width": 60,"action": {"type": "minecraft:custom","id": "asdf"}}]}
scoreboard players set @s select -1