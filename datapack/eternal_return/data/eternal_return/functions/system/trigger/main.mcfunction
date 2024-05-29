#
##> trigger 스코어 주소 정리
#
#   1~19 : 하이퍼루프 워프
#   20~25 : 키오스크 구매
#
#

## 하이퍼루프 워프
execute if score @s[tag=open_hyperloop] ChatClick matches 1..19 run function eternal_return:system/trigger/hyperloop

## 키오스크
execute if score @s[tag=open_kiosk] ChatClick matches 21..25 run function eternal_return:system/trigger/kiosk