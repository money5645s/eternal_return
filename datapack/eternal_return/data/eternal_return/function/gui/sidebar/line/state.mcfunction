## 사이드바 생성
# 상위함수 : 없음
# 입력 : @s pdb:main args


$scoreboard players display name line0 sidebar.$(UUID0) [\
\
    {"text":"b","font":"gui/character/bg"},{"translate":"space.0","font":"minecraft:default"},\
    {"text":"c","font":"gui/character/bg"},{"translate":"space.4","font":"minecraft:default"},\
\
    {score:{name:"@s",objective:"skill_exp_image"},"font":"gui/character/skill/exp"},{"translate":"space.-12","font":"minecraft:default"},\
    {storage:"pdb:main","nbt":"in.profile_unicode","font":"gui/character/skill/profile"},{"translate":"space.-56","font":"minecraft:default"},\
    {"text":"d","font":"gui/character/bg"},{"translate":"space.-150","font":"minecraft:default"},\
    {"text":"a","font":"gui/character/bg"},{"translate":"space.-42","font":"minecraft:default"},\
\
    {score:{name:"@s",objective:"active_level"},"font":"gui/character/skill/active_level"},{"translate":"space.1","font":"minecraft:default"},\
    {score:{name:"@s",objective:"passive_level"},"font":"gui/character/skill/passive_level"},{"translate":"space.-40","font":"minecraft:default"},\
    {storage:"pdb:main","nbt":"in.profile_unicode","font":"gui/character/skill/active"},{"translate":"space.-19","font":"minecraft:default"},\
    {storage:"pdb:main","nbt":"in.stats.active.cool.image","font":"gui/character/skill/active"},{"translate":"space.3","font":"minecraft:default"},\
    {storage:"pdb:main","nbt":"in.profile_unicode","font":"gui/character/skill/passive"},{"translate":"space.-17","font":"minecraft:default"},\
    {storage:"pdb:main","nbt":"in.stats.passive.cool.image","font":"gui/character/skill/passive"},{"translate":"space.-39","font":"minecraft:default"},\
\
    {storage:"pdb:main","nbt":"in.stats.active.point_image","font":"gui/character/skill/skill_point"},{"translate":"space.5","font":"minecraft:default"},\
    {storage:"pdb:main","nbt":"in.stats.passive.point_image","font":"gui/character/skill/skill_point"},{"translate":"space.0","font":"minecraft:default"},\
\
    {storage:"pdb:main","nbt":"in.stats.active.cool",interpret:true},{"translate":"space.0","font":"minecraft:default"},\
    {storage:"pdb:main","nbt":"in.stats.passive.cool",interpret:true},{"translate":"space.0","font":"minecraft:default"},\
    {storage:"pdb:main","nbt":"in.stats.credit","font":"gui/character/skill/text/credit"},{"translate":"space.0","font":"minecraft:default"},\
    {storage:"pdb:main","nbt":"in.stats.level",interpret:true},{"translate":"space.2","font":"minecraft:default"},\
\
    {storage:"pdb:main","nbt":"in.stats.atk","font":"gui/character/skill/text/atk"},{"translate":"space.-12","font":"minecraft:default"},\
    {storage:"pdb:main","nbt":"in.stats.atk_spd","font":"gui/character/skill/text/atk_spd"},{"text":"%","font":"gui/character/skill/text/atk_spd_per"},{"translate":"space.-11","font":"minecraft:default"},\
    {storage:"pdb:main","nbt":"in.stats.armor","font":"gui/character/skill/text/armor"},{"translate":"space.-12","font":"minecraft:default"},\
    {storage:"pdb:main","nbt":"in.stats.mvs","font":"gui/character/skill/text/speed"},{"text":"%","font":"gui/character/skill/text/speed_per"},{"translate":"space.55","font":"minecraft:default"}\
    ]
$scoreboard players display name line1 sidebar.$(UUID0) ""