$dialog show @s {"type": "minecraft:multi_action",\
  "title": {"text": "","extra": [{"text":" "}]},\
  "external_title": "",\
  "body": [{"type": "minecraft:plain_message","width": 240,"contents": [$(character_text_array)]}],\
  "inputs": [],\
  "pause": false,\
  "can_close_with_escape": false,\
  "actions": [{"label": {"text": "바로가기","color": "#828282"},"width": 250,"action": {"type": "minecraft:run_command","command": "/trigger select set 0"}}]\
}