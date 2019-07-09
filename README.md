# ecotour
생태 관광 정보 서비스 API


## 기술 스택
- Java 8
- Spring Boot 2.1.6
- Spring Data JPA
- MySQL
- ElasticSearch 6.0.0


## 빌드 및 실행 방법
- MySQL Database 설정 
> Create Database ecotour  
- Kakao API : resources/kakaoapi.properties
> kakao.api.key=API 키 설정


## 기능 명세

## 

### 1. 생태 관광 데이터 (CSV) 저장

- HTTP Method : POST
- URI : /programs/load
- Params : fileName
> `/programs/load?fileName=저장경로.csv`



### 2. 생태 관광 정보 데이터 추가

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



### 3. 생태 관광 정보 데이터 수정

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


### 4. 생태 관광 정보 데이터 조회 (전체)

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


### 5. 생태 관광 정보 데이터 조회 (단일 프로그램 조회 by ID)

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






### Search

기능  | HTTP Method | URI | Input | Output
------|-------------|-----|-------|-------
관광 정보 조회 | GET | /programs/region/{regionCode} | | List
관광 정보 조회  | GET | /programs/search  | region | 서비스 지역 코드, 관광 정보(프로그램명, 테마) 목록
서비스 지역 개수 조회 | GET | /programs/count/region  | keyword | 키워드, 관광 정보(지역명, 개수) 목록
출현 빈도수 조회  | GET | /programs/count/keyword | keyword | 키워드, 출현 빈도수


### Recommend

기능  | HTTP Method | URI | Input | Output
------|-------------|-----|-------|-------
프로그램 추천 | GET | /programs/recommend | keword, region  | 프로그램 코드



## 빌드 및 실행 방법



## 문제 해결 전략






