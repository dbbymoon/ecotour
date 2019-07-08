# ecotour
생태 관광 정보 API

기본 문제


1.생태 관광 프로그램 CSV 데이터셋 저장

데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API 를 개발하세요.

HTTP Method : POST 

URI : /programs/load

Input : fileName

Output : 




2.생태 관광 정보 데이터 조회

생태 관광정보 데이터를 조회할 수 있는 API 를 개발하세요

2.1. 프로그램 전체 목록 조회

HTTP Method : GET

URI : /programs

Input : 

Output : List<Program>
  
2.2. ID에 해당하는 단일 프로그램 조회 

HTTP Method : GET

URI : /programs/{prgmSeq}

Input :

Output : Program

2.3. 서비스 지역 코드(RegionCode)에 해당하는 프로그램 목록 조회

HTTP Method : GET

URI : /programs/region/{regionCode}

Input :

Output : List<Program>



3.생태 관광 정보 데이터 추가
생태 관광정보 데이터를 추가할 수 있는 API 를 개발하세요

HTTP Method : POST

URI : /programs

Input : Program CSV 데이터 형식

Output : 


4.생태 관광 정보 데이터 수정

생태 관광정보 데이터를 수정할 수 있는 API 를 개발하세요

HTTP Method : PUT

URI : /programs/{prgmSeq}

Input : Program CSV 데이터 형식

Output :


5.지역명에 해당하는 프로그램 조회

생태 관광지 중에 서비스 지역 컬럼에서 특정 지역에서 진행되는 프로그램명과 테마를 출력하는 API 를 개발하세요.

HTTP Method : GET

URI : /programs/search

Input : region 지역 이름

Output : 서비스 지역 코드, 프로그램 정보(프로그램명, 테마) 목록


6.키워드에 해당하는 서비스 지역 개수 조회

생태 정보 데이터에 "프로그램 소개” 컬럼에서 특정 문자열이 포함된 레코드에서 서비스 지역 개수를 세어 출력하는 API 를 개발하세요.

HTTP Method : GET

URI : /programs/count/region

Input : keyword 검색 키워드

Output : 키워드, 프로그램 정보(지역명, 개수) 목록


7.키워드에 해당하는 출현 빈도수 조회

모든 레코드에 프로그램 상세 정보를 읽어와서 입력 단어의 출현빈도수를 계산하여 출력
하는 API 를 개발하세요.

HTTP Method : GET

URI : /programs/count/keyword

Input : keyword 검색 키워드

Output : 키워드, 출현 빈도수


8.프로그램 추천

생태관광 정보를 기반으로 생태관광 프로그램을 추천해주려고 합니다. 지역명과 관광 키
워드를 입력받아 프로그램 코드를 출력하는 API 를 개발하세요.

HTTP Method : GET

URI : /programs/recommend

Input : keyword 검색 키워드, region 지역명

Output : 프로그램 코드
