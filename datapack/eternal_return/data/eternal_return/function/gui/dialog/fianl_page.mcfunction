

$dialog show @s {"type": "minecraft:multi_action",\
  "title": {"text": "","extra": [\
  {"translate": "space.300"},{"text": "\u$(character_profill)","font": "gui/character/standing","shadow_color":[0,0,0,0]},{\
  "translate": "space.-230"},{"text": "\u$(character_profill)","font": "gui/character/nametag","shadow_color": [0,0,0,0]}]},\
  "external_title": "",\
  "body": [{"type": "minecraft:plain_message","width": 240,"contents": [$(character_text_array)]}],\
  "inputs": [],\
  "pause": false,\
  "after_action": "none",\
  "can_close_with_escape": false,\
  "actions": [{"label": {"text": "바로가기","shadow_color": [0,0,0,0]},"width": 250,"action": {"type": "minecraft:run_command","command": "/trigger select set 200"}}]\
}
