# 100,000 KeySound BMS File Format Idea


### File Layout:
```
*---------------------- HEADER FIELD
#PLAYER 싱글 / 더블
#GENRE 장르
#TITLE 제목
#ARTIST 작곡가
#BPM ㅈㄱㄴ
#PLAYLEVEL 난이도
#RANK 판정
#TOTAL 게이지
#STAGEFILE 로딩이미지

#WAV00000
└00001 ~ 99999

*---------------------- MAIN DATA FIELD
#aaa.bb.ccc.ddd.e: WAV00000
└마디.라인.등분.인덱스.타입: WAV00000

마디와 라인은 기존과 동일.
등분은 그 마디를 몇등분 할 것인지. (1 ~ 128)
인덱스는 등분된 마디의 첫번째(0)에서부터 세는 위치. (0 ~ (등분 - 1))
타입은 단노트인지 롱노트인지. (0: 단노트 1: 롱노트)
```
