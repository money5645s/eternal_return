$dialog show @s {"type": "minecraft:notice",\
  "title": {"text": "","extra": [\
  {"translate": "space.300"},\
  {"text": "\u$(character_profill)","font":"gui/character/standing","shadow_color": [0,0,0,0]},\
  {"translate": "space.-230"},\
  {"text":"\u$(character_profill)","font":"gui/character/nametag","shadow_color": [0,0,0,0]}]},\
  "external_title": "",\
  "body": [{"type": "minecraft:plain_message","width": 240,"contents": [$(character_text_array)]}],\
  "inputs": [],\
  "pause": false,\
  "can_close_with_escape": false,\
  "action": {"width": 250,"label": \
  {"text": "바로시작","color": "white","shadow_color": [0,0,0,0]},"action": {"type": "run_command","command": "/trigger select set 200"}}\
}