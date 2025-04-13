$execute unless data storage tdb:main strings[{name:"$(name)"}] run return run tellraw @s {text:"존재하지 않는 문자열입니다.",color:"red"}
$data modify storage tdb:main queue set from storage tdb:main strings[{name:"$(name)"}]
