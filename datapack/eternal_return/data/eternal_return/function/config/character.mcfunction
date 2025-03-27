## 캐릭터 스탯 설정
# 상위 함수 : function eternal_return:load

# 캐릭터를 추가하는 방법
# 1. 캐릭터 스탯 배열에 번호를 1씩 줄이고 추가한다.
# 2. 캐릭터 리스트에 번호를 1씩 더하고 추가한다.
# 3. 캐릭터 스탯에 넣은 번호는 리소스팩에 font/gui/character/line1~3에 있는 곳에 \u69xx(선택안됨) \u64xx(선택됨)을 적어둔다. [총 6개]
# 4. 캐릭터 프로필을 font/gui/character/profile에 

## 캐릭터 갯수
    scoreboard players set #charater.count NUM 31
    
####################################### 건들지 마시오 ###################################
        scoreboard players operation #charater.count+1 NUM = #charater.count NUM
        scoreboard players operation #charater.count-4 NUM = #charater.count NUM
        scoreboard players add #charater.count+1 NUM 1
        scoreboard players remove #charater.count-4 NUM 4
#######################################################################################

## 캐릭터 선택 배열
    data modify storage config character_array set value \
    [[999,998,997,996,995],\
    [994,993,992,991,990],\
    [989,988,987,986,985],\
    [984,983,982,981,980],\
    [979,978,977,976,975],\
    [974,973,972,971,970],\
    [969,968,0,0,0]]

## 캐릭터 리스트
    data modify storage config character_list set value [\
        {no:2,name:"adriana",\
            aname:"불길 쇄도",alore1:"바라보는 방향으로 돌진하며 이동한 경로에는 일정시간 유지되는 화염 지대가 생성됩니다.",alore2:"화염지대는 범위 안에 대상에게 지속적으로 피해를 입히며 화상상태를 부여한다.",alore3:"방관피해량 : 1/1/2/2/3 ",alore4:"지속시간 : 3/4/5/6/8",acool:"30/28/26/24/20",alevel:0,\
            pname:"활활",plore1:"아드리아나는 스킬로 피해를 입히면 적을 불태워 화상 상태로 만듭니다.",plore2:"화상상태 : 이동속도 -15/15/30/30/45(%) // 갱신은 가능하나 중첩되지 않는다.",plore3:"아드리아나는 릴리의 죽음의 트라우마로 인한 강박적 방화때문에 불에 대한 저항이 높아졌습니다.",plore4:"화염저항 : 불로 인한 피해 면역(발화,화염 등)",pcool:"없음",plevel:0},\
        {no:3,name:"alex",\
            aname:"타겟 마커 & 플라즈마 마인",alore1:"line1",alore2:"line2",alore3:"line3",alore4:"",acool:"",alevel:0,\
            pname:"잠입",plore1:"line1",plore2:"line2",plore3:"line3",plore4:"",pcool:"",plevel:0},\
        {no:4,name:"arda",\
            aname:"고고학적 분석",alore1:"line1",alore2:"line2",alore3:"line3",alore4:"",acool:"",alevel:0,\
            pname:"유물 탐구",plore1:"line1",plore2:"line2",plore3:"line3",plore4:"",pcool:"",plevel:0},\
        {no:5,name:"bernice",\
            aname:"사냥 덫",alore1:"버니스가 바라보는 곳에 사냥덫을 설치합니다.",alore2:"적이 사냥덫을 밟으면 느려지고 출혈피해를 2초간 받습니다.",alore3:"덫 효과 : 속도감소 15% ,독3 // 효과는 중첩되지 않고 갱신됩니다.",alore4:"  지속시간 : 15초 // 레벨당 충전되는 덫의 개수 : 1/1/2/2/3 // 덫은 지울수 없습니다.",acool:"50/45/40/35/30 // 재설치 쿨타임 : 3초",alevel:0,\
            pname:"산탄",plore1:"버니스는 다수의 탄환을 사용해 공격합니다.(다중발사)",plore2:"사냥의 노하우를 가진 버니스는 야생동물 처치시에 일정 체력을 회복합니다.",plore3:"회복량 : 1/2/3/4/5",plore4:"",pcool:"20/18/16/14/10",plevel:0},\
        {no:6,name:"bianca",\
            aname:"짧은 안식",alore1:"line1",alore2:"line2",alore3:"line3",alore4:"",acool:"",alevel:0,\
            pname:"흡혈귀",plore1:"line1",plore2:"line2",plore3:"line3",plore4:"",pcool:"",plevel:0},\
        {no:7,name:"celine",\
            aname:"플라즈마 폭탄",alore1:"F키로 지정한 위치에(바닥)에 폭탄을 설치합니다.",alore2:"Q키로 설치된 폭탄과 인근 폭탄이 융합되며 터집니다. 인근 폭탄이 없을시에 융합되지 않고 바로 터집니다.",alore3:"융합단계별 폭탄 피해량 및 효과 : 1단계 4/5/6/7/8 ,2단계 6/7/8/9/10(슬로우) ,3단계 10/11/12/13/14(강한슬로우)",alore4:"",acool:"없음",alevel:0,\
            pname:"폭발물 전문가",plore1:"셀린의 공격이 적에게 적중하면 플라즈마 폭탄의 보유량이 1개 증가됩니다.",plore2:"폭탄은 최대 3개까지 보유되고 설치될 수 있습니다. ",plore3:"(경우의수 : 1단계 3개 // 2단계 1개, 1단계 1개 // 3단계 1개)",plore4:"",pcool:"10/9/8/7/5",plevel:0},\
        {no:8,name:"chloe",\
            aname:"생명 공유",alore1:"line1",alore2:"line2",alore3:"line3",alore4:"",acool:"",alevel:0,\
            pname:"살아 있는 마리오네트",plore1:"line1",plore2:"line2",plore3:"line3",plore4:"",pcool:"",plevel:0},\
        {no:9,name:"daniel",\
            aname:"그림자 가위",alore1:"다니엘이 전방에 그림자 가위를 불러와 적들에게 피해를 입힙니다.",alore2:"방관 피해량 : 3/4/5/6/8",alore3:"또한 피해를 입은 적은 잠깐동안 시야가 축소되며, 이동속도가 15% 감소됩니다.",alore4:"지속시간 : 2/3/4/5/6",acool:"40/35/30/25/20",alevel:0,\
            pname:"고독한 예술가",plore1:"녹슨 가위(공격력 7)를 사용하는 다니엘은 더아프게 적을 공격할 수 있으나 적 처치시에 스스로 파상풍(독)에 걸립니다.",plore2:"적 처치시 받는 디버프 : 독4 5/4/3/2/0",plore3:"밤이되면 다니엘을 고독한 예술가가 되어 밤 시야가 증가하고 이동속도가 증가합니다.",plore4:"고독한 예술가 : 야간 투시 , 이동속도 +15%",pcool:"없음",plevel:0},\
        {no:10,name:"elena",\
            aname:"죽음의 무도",alore1:"엘레나가 전방으로 죽음의 얼음지대를 7초간 생성합니다.",alore2:"죽음의 얼음지대 위에서 엘레나는 화염저항, 근접공격력 +3 추가효과를 받으며,",alore3:"죽음의 얼음지대 또한 얼음지대효과를 공유합니다.",alore4:"",acool:"60/55/50/45/40",alevel:0,\
            pname:"겨울여왕의 영지",plore1:"엘레나가 밤에 엘레나의 발자취에 유지되는 얼음지대를 생성합니다.",plore2:"얼음지대 위에서의 적은 냉기가 차올라 이동속도(%)가 15/15/30/30/45 느려집니다.",plore3:"엘레나는 피겨스케이팅을 통해 유연성을 길러 긴 리치를 가졌습니다. 기본사거리 : 3.5",plore4:"",pcool:"없음",plevel:0},\
        {no:11,name:"hart",\
            aname:"Flanger",alore1:"하트가 지정한 방향으로 짧게 이동한 후 근처 적에게 방어력을 관통하는 음파를 날려 피해를 입힙니다.",alore2:"5초내로 스킬을 재사용할 수 있습니다.",alore3:"방관피해량 : 1/2/3/4/5 ",alore4:"",acool:"30/28/26/24/20",alevel:0,\
            pname:"Peacemaker",plore1:"하트가 낮에는 앰프를 소환해 'Love and Peace'버스킹을 하여 ",plore2:"해당지역을 피스존으로 선언합니다.(1일차 낮은 제외)",plore3:"피스존에 있었던 실험체들은 15초간 재생효과와 근접공격력 -4의 효과를 받습니다.",plore4:"피스존 내 하트만 받는 효과 : 재생,근접공격력-4 / 재생,근접공격력-4 / 재생 / 재생 / 재생2",pcool:"없음",plevel:0},\
        {no:12,name:"hyunwoo",\
            aname:"선빵필승",alore1:"바라보는 방향으로 돌진하며 적과 부딪히면 밀어내며 데미지를 입힌다.",alore2:"밀어낸 적이 벽에 부딪히면 추가 피해를 입히고 일정시간동안 슬로우를 겁니다.",alore3:"피해량 : 7/8/9/10/12 , 추가 피해량 : 3/4/5/6/8",alore4:"슬로우 효과 수치 : 99% , 지속시간 2초(고정)",acool:"30/26/22/18/14",alevel:0,\
            pname:"도그파이트",plore1:"현우가 적을 공격할 떄마다 도그파이트 중첩을 얻습니다.",plore2:"5번의 중첩을 쌓았을때에 활성화 되며 동시에 체력을 회복합니다.",plore3:"회복량 : 1/2/3/4/5",plore4:"",pcool:"없음",plevel:0},\
        {no:13,name:"isol",\
            aname:"Mok제 폭탄",alore1:"line1",alore2:"line2",alore3:"line3",alore4:"",acool:"",alevel:0,\
            pname:"유격전",plore1:"line1",plore2:"line2",plore3:"line3",plore4:"",pcool:"",plevel:0},\
        {no:14,name:"jackie",\
            aname:"전기톱 살인마",alore1:"재키의 이동속도(20%)가 일정시간동안 증가되고 지속 시간 내에 학살을 사용할 수 있습니다.",alore2:"학살 : 재키가 전기톱을 크게 휘둘러 주위에 7/8/9/10/12 피해를 입힘과 동시에 흡혈합니다.",alore3:"또한 지속시간 내에 적에게 피해를 주면 딱 한번 2초간 이동속도를 30% 느려지게 합니다.",alore4:"지속시간 : 5/6/7/8/10",acool:"50/47/44/41/35",alevel:0,\
            pname:"피의 축제",plore1:"재키가 야생동물 4마리 혹은 실험체 1명을 죽일 경우 '피의 축제'가 20초간 활성화 됩니다.(갱신 가능)",plore2:"피의 축제 : 근접 공격력 +3, 공격속도 + 10%",plore3:"항상 피에 목말라있는 재키는 피가 없으면 기력이 부족합니다.",plore4:"피의 축제 효과가 없을 평상시에는 이동속도가 15% 감소됩니다.",pcool:"없음",plevel:0},\
        {no:15,name:"jenny",\
            aname:"페르소나",alore1:"제니가 전방으로 짧은거리를 이동하며 배역을 전환합니다.",alore2:"전환된 배역에 따라 추가 효과를 획득합니다.",alore3:"블랙티 : 이동속도 +20% , 근접공격력 -4",alore4:"레드와인 : 공격속도 +10%",acool:"30/27/24/21/15",alevel:0,\
            pname:"죽음의 연기",plore1:"제니의 체력이 0이되면 3초간 죽음의 연기상태가 됩니다.",plore2:"죽음의 연기 : 추가체력 4/4/8/8/12 , 화염저항 , 근접공격력 -4, 이동속도 -30%",plore3:"",plore4:"",pcool:"70(고정)",plevel:0},\
        {no:16,name:"katja",\
            aname:"접근 금지",alore1:"line1",alore2:"line2",alore3:"line3",alore4:"",acool:"",alevel:0,\
            pname:"잿빛 사신",plore1:"line1",plore2:"line2",plore3:"line3",plore4:"",pcool:"",plevel:0},\
        {no:17,name:"leni",\
            aname:"뿅! 망치",alore1:"레니가 바라보는 방향으로 도약하여 바닥에 뿅망치 자국을 남기며 적에게 피해를 입힌다.",alore2:"뿅망치 자국을 맞은 적은 5/7/9/11/13의 피해를 입고 느림효과를 3초간 받습니다.",alore3:"동시에 적 피격과 관계 없이 레니는 이동속도와 점프강화를 얻습니다.",alore4:"제공 받는 효과 : 이동속도 15%, 점프강화 2배",acool:"50/45/40/35/30",alevel:0,\
            pname:"곰돌이! 공격",plore1:"레니가 점프를 20회 진행할 시에 액티브 스킬의 쿨타임을 감소시킨다.",plore2:"'성장기인 레니는 점프하는걸 너무 좋아합니다.'",plore3:"감소되는 쿨타임 : 1/1/2/2/3",plore4:"",pcool:"없음",plevel:0},\
        {no:18,name:"lenox",\
            aname:"회오리 비늘",alore1:"레녹스가 원형 범위의 적들에게 피해를 입힙니다.",alore2:"해당 스킬을 맞은 상대는 느려집니다.",alore3:"느림 효과 : 15/15/30/30/45",alore4:"피해량 : 6/8/10/12/14",acool:"30/27/24/21/18",alevel:0,\
            pname:"위풍당당",plore1:"레녹스가 적에게 피해를 입히면 보호막을 2초동안 획득합니다.",plore2:"획득하는 보호막량 : 4(고정)",plore3:"레녹스는 강태공입니다. 물론 세월도 낚지만요...",plore4:"낚시시에 높은확률(40%)로 연어를 낚습니다.",pcool:"30/27/24/21/15",plevel:0},\
        {no:19,name:"leon",\
            aname:"물보라",alore1:"레온이 스킬 사용 후 지속시간동안 삼지창 투척으로 적에게 피해를 주면 체력을 회복합니다.",alore2:"지속시간 7/9/11/13/15",alore3:"회복되는 체력량 : 4(고정)",alore4:"",acool:"50/47/44/41/35",alevel:0,\
            pname:"인간 어뢰",plore1:"레온은 물가지역에서 공격속도 10%를 제공받습니다.외에 지역에선 근접공격력 -4의 효과를 받습니다.",plore2:"물가지역 : 소방서, 개울, 연못, 숲, 항구, 모래사장",plore3:"삼지창 조준시에 이동속도가 완화됩니다.",plore4:"완화되는 이동속도 15/30/45/60/75%",pcool:"없음",plevel:0},\
        {no:20,name:"luke",\
            aname:"클리닝 서비스",alore1:"line1",alore2:"line2",alore3:"line3",alore4:"",acool:"",alevel:0,\
            pname:"청소 완료",plore1:"line1",plore2:"line2",plore3:"line3",plore4:"",pcool:"",plevel:0},\
        {no:21,name:"lyanh",\
            aname:"해방",alore1:"line1",alore2:"line2",alore3:"line3",alore4:"",acool:"",alevel:0,\
            pname:"사로잡힌 육신",plore1:"line1",plore2:"line2",plore3:"line3",plore4:"",pcool:"",plevel:0},\
        {no:22,name:"magnus",\
            aname:"강타",alore1:"line1",alore2:"line2",alore3:"line3",alore4:"",acool:"",alevel:0,\
            pname:"근성",plore1:"line1",plore2:"line2",plore3:"line3",plore4:"",pcool:"",plevel:0},\
        {no:23,name:"markus",\
            aname:"지각변동",alore1:"마커스가 전방으로 도약해 지면을 내려찍습니다.",alore2:"피격당한 적들은 방어력을 관통하는 피해를 입으며",alore3:"5초간 '충격' 상태가 되어 이동속도가 15% 느려집니다.",alore4:"방관피해량 : 4/5/6/7/8",acool:"50/45/40/35/30",alevel:0,\
            pname:"전사의 투지",plore1:"적에게 스킬을 피격시에 마커스는 전사의 투지를 획득합니다.",plore2:"전사의 투지 : 공격속도 1/1.4/1.8/2.2/2.6 , 이동속도 +20/20/40/40/60%",plore3:"지속시간 : 7/8/9/10/12",plore4:"",pcool:"없음",plevel:0},\
        {no:24,name:"nadine",\
            aname:"늑대 맹습",alore1:"나딘은 늑대의 영혼이 담긴 폭죽을 일정시간동안 장전하여 발사할 수 있습니다.",alore2:"폭죽 피해량(방어관통) : 6",alore3:"지속시간 : 5/6/7/8/10",alore4:"",acool:"60/55/50/45/40",alevel:0,\
            pname:"야성",plore1:"나딘이 야생동물을 5마리 잡게되면 랜덤으로 속성 화살을 1개 제공받습니다.",plore2:"속성 화살 종류 : 나약함/감속/분광/독/신속 (확률 균등)",plore3:"속성 화살의 지속시간 : 3/4/5/6/7 , 신속 화살 지속시간 : 22초",plore4:"",pcool:"없음",plevel:0},\
        {no:25,name:"nathapon",\
            aname:"인스턴트 포토",alore1:"나타폰이 바라보는 방향으로 프레임을 발사합니다.",alore2:"프레임을 적에게 적중할 시에 그 위치에 표식을 남기고 1.5초 후부터 3.5초동안",alore3:"스킬 재사용을 통해 프레임을 맞춘 적에게 방어력을 관통하는 피해를 입히고 표식이 남긴 위치로 되돌려 보낸다.(적이 표식으로부터 너무 멀어지면 재사용 불가)",alore4:"방관비해량 : 4/5/6/7/8",acool:"40/37/34/31/25",alevel:0,\
            pname:"슬로우 셔터",plore1:"나타폰은 매 순간을 카메라에 담기위해 주기적으로 셔터를 닦습니다.",plore2:"셔터를 열심히 닦은 나머지 나타폰이 셔터를 터트려 적에게 공격을 하면 ,적은 4초간 실명됩니다.(달리기 불가,시야축소)",plore3:"또한 나타폰은 밤이되어도 카메라를 통해 어두운 부분도 잘 볼 수 있습니다. (야간 투시)",plore4:"",pcool:"30/28/26/24/20",plevel:0},\
        {no:26,name:"piolo",\
            aname:"튕겨내기&휘두르기",alore1:"line1",alore2:"line2",alore3:"line3",alore4:"",acool:"",alevel:0,\
            pname:"단련광",plore1:"line1",plore2:"line2",plore3:"line3",plore4:"",pcool:"",plevel:0},\
        {no:27,name:"shou",\
            aname:"뜨거운 맛",alore1:"쇼우의 현재 체력의 20%를 소모하여, 소모된 체력만큼 근접 공격력을 상승시킨다.",alore2:"소모된 체력과 상승되는 공격력 비율(내림 계산): 1:0.9 / 1:1 / 1:1.1 / 1:1.2 / 1:1.4",alore3:"지속시간 : 20초(고정)",alore4:"",acool:"70/65/60/55/50",alevel:0,\
            pname:"요리사의 열정",plore1:"쇼우가 음식을 제작할때 완성되는 요리의 수량이 일정확률로 1개 더 추가됩니다.",plore2:"일정확률 : 20/40/60/80/100(%)",plore3:"'셰프 위에 식재료가 있다' - 주오 쇼우카이",plore4:"낚시와 채집시에 무조건 연어와 호박고구마를 얻습니다.",pcool:"없음",plevel:0},\
        {no:28,name:"silvia",\
            aname:"기동전",alore1:"스킬 사용시 실비아는 말을 소환해 탑승합니다.",alore3:"실비아는 말을 타거나 내릴 수 있습니다.",alore4:"지속시간 : 20초(고정)",acool:"60/55/50/45/40",alevel:0,\
            pname:"그란투리스모",plore1:"새로운 지역 8개를 방문하면 200 크레딧을 획득합니다.",plore2:"너의 바이크의 단단함은 말 갑옷으로 대체되었다.",plore3:"레벨당 장착되는 말 갑옷 : 없음/가죽/철/금/다이아",plore4:"",pcool:"없음",plevel:0},\
        {no:29,name:"sissela",\
            aname:"모두 해방이에요.",alore1:"1.5초간 정신집중 후 시셀라 자신이 존재하는 지역에 있는",alore2:"모든 실험체에게 방어력을 관통하는 피해를 줍니다.",alore3:"발동조건 : 최대체력의 40% 미만",alore4:"방관피해량 : 4/6/8/10/12",acool:"60/55/50/45/40",alevel:0,\
            pname:"삷은 고통이에요.",plore1:"시셀라는 현재 체력이 낮아지면 낮아질수록 추가효과를 받습니다.",plore2:"체력 40% 미만일때 : 근접 공격력 +3",plore3:"체력 20% 미만일때 : 근접 공격력 +6",plore4:"병들고 어린 시셀라는 팔이 짧습니다. 기본사거리 : 2.5",pcool:"없음",plevel:0},\
        {no:30,name:"vanya",\
            aname:"꿈결 가루",alore1:"환상의 비늘가루를 전방에 흩뿌려 피해를 주며 수면에 들게 합니다.",alore2:"비늘가루를 맞은 적은 이동속도가 4초에 걸쳐서 점차 감소되다 결국 수면에 빠집니다.",alore3:"수면 : 1.5초간 실명효과가 걸리며, 이동속도가 99% 감소되어 움직이기 힘들어 진다.",alore4:"피해량 : 4/6/8/10/12",acool:"70/65/60/55/50",alevel:0,\
            pname:"몽환 나비",plore1:"삼지창 투척 이후 바냐의 손에 다시 돌아오면 일정시간 유지되는 4만큼의 보호막을 제공받습니다.",plore2:"보호막 지속시간 : 6초(고정)",plore3:"타 보호막 효과와 중첩시에 더 높은 효과가 유지된다.",plore4:"",pcool:"30/27/24/21/15",plevel:0},\
        {no:31,name:"yuki",\
            aname:"화무십일홍(花無十日紅)",alore1:"유키가 검에 손을 가져가서 순식간에 공간을 베어 버립니다.",alore2:"피격당한 적에게 피해줌과 동시에 표시를 남기며 1초간 이동속도를 30% 느려지게 한 뒤",alore3:"유키가 검을 검집에 집어 넣는 순간 표식이 터지며 대상에게 방어력을 관통하는 피해를 입힙니다.",alore4:"1차 피해량 : 4/5/6/7/8 , 2차 방관피해량 : 6/7/8/9/10",acool:"60/55/50/45/40",alevel:0,\
            pname:"완벽한 옷매무새",plore1:"유키가 최대체력의 80%이상 체력을 보유시에 근접무기의 데미지가 추가됩니다.",plore2:"추가되는 데미지 : 1/2/3/4/5",plore3:"",plore4:"",pcool:"없음",plevel:0},\
        {no:32,name:"estelle",\
            active:\
            {name:"헬기호출",lore1:"line1",lore2:"line2",lore3:"line3",cool:0},\
            passive:\
            {name:"방패방어",lore1:"line1",lore2:"line2",lore3:"line3",cool:0}}]

## 캐릭터 별 스탯
    # 쇼우
        scoreboard players set #shou.\
            active.cool NUM 10
        scoreboard players set #shou.\
            active.damage NUM 10
        scoreboard players set #shou.\
            passive.cool NUM 5
        scoreboard players set #shou.\
            passive.damage NUM 2