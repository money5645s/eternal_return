$dialog show @s {"type": "minecraft:notice",\
  "title": {"text": "","extra": [{"text":" "}]},\
  "external_title": "",\
  "body": [{"type": "minecraft:plain_message","width": 240,"contents": [$(character_text_array)]}],\
  "inputs": [],\
  "pause": false,\
  "can_close_with_escape": false,\
  "action": {"width": 250,"label": \
  {"text": "바로시작","color": "#88b7cf","shadow_color": [0,0,0,0]},"action": {"type": "run_command","command": "/trigger select set 0"}}\
}