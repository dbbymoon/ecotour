# ecotour

생태 관광 정보 서비스 API


## 기술 스택

- Java 8
- Spring Boot 2.1.6
- Spring Data JPA
- MySQL
- ElasticSearch 6.0.0


## 빌드 및 실행 방법
- MySQL Database 생성
> `mysql> create database ecotour`
- MySQL 설정 : resources/application.properties
> `spring.datasource.hikari.username={username}`
> `spring.datasource.hikari.password=skfo`
- Kakao API key 설정 : resources/kakaoapi.properties 
> `kakao.api.key={API KEY}`

위 설정을 마친 후 Run 'EcoTourApplication'



## 기능 명세

### 기본 기능 

#### 1. 생태 관광 데이터 (CSV) 저장

- HTTP Method : POST
- URI : /programs/load
- Params : fileName
> `/programs/load?fileName=저장경로.csv`




#### 2. 생태 관광 정보 데이터 추가

- HTTP Method : POST
- URI : /programs
- Request Body : Programs
> `/programs`

~~~
{
  "prgmSeq": {prgmSeq},
  "prgmName": "프로그램명",
  "theme": "테마별 분류",
  "region": "경기도 광주시",
  "prgmInfo": "프로그램 소개",
  "prgmDetailInfo": "프로그램 상세 소개"
}
~~~




#### 3. 생태 관광 정보 데이터 수정

- HTTP Method : PUT
- URI : /programs/{prgmSeq}
- Request Body : Programs
> `/programs/{prgmSeq}`

~~~
{
  "prgmSeq": {prgmSeq},
  "prgmName": "프로그램명 수정",
  "theme": "테마별 분류 수정",
  "region": "경기도 성남시",
  "prgmInfo": "프로그램 소개 수정",
  "prgmDetailInfo": "프로그램 상세 소개 수정"
}
~~~




#### 4. 생태 관광 정보 데이터 조회 (전체)

- HTTP Method : GET
- URI : /programs
> `/programs`
- Response
~~~~
[
    {
        "prgmSeq": 1,
        "prgmName": "자연과 문화를 함께 즐기는 설악산 기행",
        "theme": "문화생태체험,자연생태체험,",
        "region": "강원도 속초",
        "prgmInfo": "설악산 탐방안내소, 신흥사, 권금성, 비룡폭포",
        "prgmDetailInfo": " 설악산은 왜 설악산이고, 신흥사는 왜 신흥사일까요? 설악산에 대해 정확히 알고, 배우고, 느낄 수 있는 당일형 생태관광입니다."
    },
    {
        "prgmSeq": 2,
        ....
    },
    ...
]
~~~~




#### 5. 생태 관광 정보 데이터 조회 (단일 프로그램 조회 by ID)

- HTTP Method : GET
- URI : /programs/{prgmSeq}
> `/programs/{prgmSeq}`
- Response
~~~~
{
    "prgmSeq": 1,
    "prgmName": "자연과 문화를 함께 즐기는 설악산 기행",
    "theme": "문화생태체험,자연생태체험,",
    "region": "강원도 속초",
    "prgmInfo": "설악산 탐방안내소, 신흥사, 권금성, 비룡폭포",
    "prgmDetailInfo": " 설악산은 왜 설악산이고, 신흥사는 왜 신흥사일까요? 설악산에 대해 정확히 알고, 배우고, 느낄 수 있는 당일형 생태관광입니다."
}
~~~~








### 검색 

#### 1. 생태 관광 정보 조회 by 지역 코드

- HTTP Method : GET
- URI : /programs/region/{regionCode}
> `/programs/region/{regionCode}`
- Response
~~~~
[
    {
        "prgmSeq": 110,
        "prgmName": "[생태나누리] 아름다운 날들을 위한 힐링",
        "theme": "자연생태체험,",
        "region": "충청북도 제천시 한수면",
        "prgmInfo": "",
        "prgmDetailInfo": " 문경지구 실버세대의 건강증진과 여가생활 지원을 결합한 힐링 프로그램입니다."
    },
    {
        "prgmSeq": 109,
        "prgmName": "[생태나누리] 야생화향기와 함께 떠나는 월악산여행",
        "theme": "자연생태체험,",
        "region": "충청북도 제천시 한수면",
        "prgmInfo": "월악산국립공원 송계지구 및 단양지구(하늘재~만수계곡자연관찰로~장회나루~사인암)",
        "prgmDetailInfo": " 월악산국립공원  '야생화향기와 함께 떠나는 월악산여행' 생태나누리 프로그램은 하늘재, 만수계곡자연관찰로, 옥순봉·구담봉, 사인암 등 월악산국립공원의 대표 역사문화자원과 자연생태를 자유롭게 체험하고 느낄 수 있는 프로그램입니다."
    }
]
~~~~




#### 2. 생태 관광 정보(프로그램명, 테마) 조회 by 지역명

- HTTP Method : GET
- URI : /programs/search
- Params : region
> `/programs/search?region=평창군`
- Response
~~~~
{
    "region": "reg42760",
    "programs": [
        {
            "prgmName": "오감만족! 오대산 에코 어드벤처 투어",
            "theme": "아동·청소년 체험학습"
        },
        {
            "prgmName": "소금강 지역문화 체험",
            "theme": "자연생태,"
        },
        {
            "prgmName": "오대산국립공원 힐링캠프",
            "theme": "숲 치유,"
        },
        {
            "prgmName": "(1박2일)자연으로 떠나는 행복여행",
            "theme": "문화생태체험,자연생태체험,"
        }
    ]
}
~~~~




#### 3. 서비스 지역 개수 조회 by 프로그램 소개 키워드

- HTTP Method : GET
- URI : /programs/count/region
- Params : keyword
> `/programs/count/region?keyword=세계문화유산`
- Response
~~~~
{
    "keyword": "세계문화유산",
    "programs": [
        {
            "region": "경북 경주시",
            "count": 2
        }
    ]
}
~~~~




#### 4. 키워드 출현 빈도수 조회 by 프로그램 상세 소개 키워드

- HTTP Method : GET
- URI : /programs/count/keyword
- Params : keyword
> `/programs/count/keyword?keyword=생태체험`
- Response
~~~~
{
    "keyword": "생태체험",
    "count": 8
}
~~~~








### 추천

#### 1. 생태 관광 프로그램 추천 by 키워드, 지역명

- HTTP Method : GET
- URI : /programs/recommend
- Params : keyword, region
> `/programs/recommend?keyword=평창&region=국립공원`
- Response
~~~~
{
    "program": "prgm12"
}
~~~~













## 문제 해결 전략






