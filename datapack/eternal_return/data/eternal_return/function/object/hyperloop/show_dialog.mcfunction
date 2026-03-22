

dialog show @s {"type": "minecraft:multi_action",\
  "title": {"text": "","extra": ["asdf"]},\
  "external_title": "",\
  "body": [{"type": "minecraft:plain_message","width": 240,"contents": ["asdf"]}],\
  "inputs": [],\
  "pause": false,\
  "after_action": "none",\
  "can_close_with_escape": true,\
  "actions": [{"label": {"text": "바로가기","shadow_color": [0,0,0,0]},"width": 250,"action": {"type": "minecraft:run_command","command": "/trigger select set 200"}}]\
}
