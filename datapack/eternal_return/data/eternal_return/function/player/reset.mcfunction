## 플레이어 데이터 초기화

    tag @s remove selection_character
    tag @s remove player
    
    function pdb:get_me
    
    function eternal_return:state/init
    # 보스바 생성 및 표기
        function eternal_return:gui/bossbar/remove with storage pdb:main args
    # 사이드바 팀 명명
        function eternal_return:gui/sidebar/remove with storage pdb:main args
    # 추가내용 저장
    data remove storage pdb:main players
    data remove storage pdb:main in
    data remove storage pdb:main out
