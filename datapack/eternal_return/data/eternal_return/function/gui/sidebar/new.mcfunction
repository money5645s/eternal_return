## 사이드바 생성
# 상위함수 : function eternal_return:player/frist_join
# 입력 : @s pdb:main args
# 출력 : 개인 스코어보드, 팀 생성

# 스코어보드 생성
    $scoreboard objectives add sidebar.$(UUID0) dummy
    $scoreboard objectives modify sidebar.$(UUID0) numberformat blank
    $scoreboard objectives modify sidebar.$(UUID0) displayname ""

# 색깔 배정
    # 배열에서 가져오기
        data modify storage pdb:main args.color set from storage pdb:main in.team
    # 가져온 색깔로 설정
        function eternal_return:gui/sidebar/setdisplay with storage pdb:main args
    # args.color Free
        data remove storage pdb:main args.color