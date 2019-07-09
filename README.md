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
> `spring.datasource.hikari.password={password}`
- Kakao API key 설정 : resources/kakaoapi.properties 
> `kakao.api.key={API KEY}`

위 설정을 마친 후 Run 'EcoTourApplication'



## 기능 명세

> API Documentation : `/swagger-ui.html`

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

### ElasticSearch 

#### 1. Document 

~~~~
@Document(indexName = "program", type = "eco_program")
@Setting(settingPath = "/elastic/analyzer-settings.json")
public class EcoProgramDocument {

    @Id
    private long prgmSeq;

    private String prgmName;

    @Field(type = FieldType.Text, analyzer = "ngram_analyzer")
    private String theme;

    @Field(type = FieldType.Keyword)
    private String regionCode;

    @Field(type = FieldType.Keyword)
    private String regionName;

    @Field(type = FieldType.Text, analyzer = "ngram_analyzer")
    private String region;

    @Field(type = FieldType.Text, analyzer = "ngram_analyzer")
    private String prgmInfo = "";

    @Field(type = FieldType.Text, analyzer = "ngram_analyzer")
    private String prgmDetailInfo = "";
}
~~~~

- Mapping
-- regionCode, regionName : 집계 기능으로 사용할 지역 코드, 지역구 이름은 데이터 타입을 keyword로 설정하여 문자열 필드 분석이 가능하도록 한다.
-- theme, region, prgmInfo, prgmDetailInfo : 전문 텍스트 검색에 활용할 길이가 긴 데이터는 타입을 text로 설정했다. 그리고 ngram_analyer를 적용했다.



#### 2. 맞춤형 분석기 만들기

~~~~
{
  "analysis": {
    "analyzer": {
      "ngram_analyzer": {
        "tokenizer": "ngram_tokenizer"
      }
    },
    "tokenizer": {
      "ngram_tokenizer": {
        "type": "nGram",
        "min_gram": "2",
        "max_gram": "10"
      }
    }
  }
}
~~~~

일래스틱서치에 내장된 기본 분석기는 공백 문자로 토큰을 분할하는 토크나이저를 사용한다. 해당 서비스 API에서 키워드로 검색하는 경우 이 표준 토크나이저를 사용하면 "생태체험"이라는 텍스트에서 "체험"이라는 키워드를 사용하면 검색되지 않는다.
따라서, ngram을 활용한 맞춤형 분석기를 만들었다.


ngram analyze란 텍스트를 토큰화하는 방식으로, 각 단어를 그 단어의 여러 서브 토큰으로 분리한다. 내가 설정한 ngram의 최소 토큰 길이는 2, 최대 토큰 길이는 10이다. 따라서 "생태체험"이라는 텍스트는 "생태", "태체", "체험", "생태체", "태체험", "생태체험"으로 토큰이 분리된다.
따라서, "체험"이라는 키워드를 입력해도 검색이 가능해진다.


이렇게 만든 ngram_tokenizer를 토크나이저로 적용하여 분석할 것이라고 설정하여, analyzer_settings.json 이라는 파일로 프로젝트에 추가했다.





#### 3. spring data elasticsearch Query

~~~~
GET /program/eco_program/_search
{
  "query":{
    "match": {
      "region": "{regionName}"
    }
  }
}
~~~~

이와 같은 일래스틱서치의 쿼리를 spring data elasticsearch를 이용하여,

~~~~
SearchResponse response = client.prepareSearch()
        .setQuery(QueryBuilders.matchQuery("region", regionName))
        .execute()
        .actionGet();
~~~~

이렇게 해서 검색 결과인 SearchResponse를 받아올 수 있었다.

마찬가지로, 

~~~~
GET /program/eco_program/_search?size=0
{
  "query":{
    "term": {
      "prgmInfo": "{keyword}"
    }
  },
  "aggs": {
    "byRegionName": {
      "terms": {
        "field": "regionName"
      }
    }
  }
}
~~~~

집계 기능을 사용하는 경우에도, 

~~~~
SearchResponse response = client.prepareSearch()
        .setQuery(QueryBuilders.termQuery("prgmInfo", keyword))
        .addAggregation(AggregationBuilders.terms("byRegionName").field("regionName"))
        .setSize(0)
        .execute()
        .actionGet();
~~~~

다음과 같이 SearchResponse를 받아왔다.



#### 4. 생태 관광 프로그램 추천

지역명과 키워드를 통해 프로그램을 추천하는 기능은 지역명이 일치하는 것이 더 중요하다고 판단하여, 지역명 일치에 키워드 일치보다 더 가중치를 많이 두었다.

키워드는 테마 이름, 프로그램 소개, 프로그램 상세 소개 세 가지 컬럼에 일치 여부를 찾는다. 테마 이름과 프로그램 소개, 프로그램 상세 소개 컬럼의 중요도는 각각 3:2:1 로 생각하여 다음과 같은 쿼리를 작성했다.

~~~~
final float regionScore = 10;
final float keywordScore = 0.1f;

QueryBuilder queryBuilder = QueryBuilders.disMaxQuery()
        .add(QueryBuilders.matchQuery("region", region).boost(regionScore))
        .add(QueryBuilders.multiMatchQuery(keyword, "theme^3", "prgmInfo^2", "prgmDetailInfo").boost(keywordScore));
~~~~




#### 5. Response VO

일래스틱서치의 검색 결과로 받은 SearchResponse에는 기능 수행 결과에 사용하지 않을 값도 많이 담겨있다. 

~~~~
{
  "took": 2,
  "timed_out": false,
  "_shards": {
    "total": 5,
    "successful": 5,
    "skipped": 0,
    "failed": 0
  },
  "hits": {
    "total": 4,
    "max_score": 10.890696,
    "hits": [
      {
        "_index": "program",
        "_type": "eco_program",
        "_id": "12",
        "_score": 10.890696,
        "_source": {
        ...
        }
      },
      ...
    ]
  }
}
~~~~

다음과 같은 결과를 가지고 있는데, 출력에 사용할 값들만 담아서 반환하기 위해 출력 데이터 값을 담은 VO를 작성했다. 


개발한 API 중에 지역명으로 생태관광 프로그램을 검색하는 API의 표준 출력 형식은 다음과 같다.

~~~~
{
    "region": {regionCode},
    "programs": [
        {
            "prgmName": {prgmName},
            "theme": {theme}
        },
        ....
    ]
}
~~~~

따라서, 위의 출력 형식에 맞는 VO를 작성했다.

~~~~
public class RegionSearchResultVO {

    private String region;  // Region Code

    private List<Program> programs;

    private static class Program {

        private String prgmName;   // 프로그램명

        private String theme;   // 테마
    }
}
~~~~

그리고 다음 static method를 작성해 SearchResponse를 파라미터로 받아 응답 결과를 해당 VO로 매핑하고 반환할 수 있도록 했다.

~~~~
public static RegionSearchResultVO of(SearchResponse response) throws SearchResultNotExistException{

    SearchHit[] hits = response.getHits().getHits();
    
    try {
        String regionCode = (String) hits[0].getSourceAsMap().get("regionCode");
        List<Program> programs = Arrays.stream(hits)
                .map(hit -> Program.of(hit.getSourceAsMap()))
                .collect(Collectors.toList());
        return new RegionSearchResultVO(regionCode, programs);
    } catch (ArrayIndexOutOfBoundsException e) {
        throw new SearchResultNotExistException();
    }

}
~~~~



### 서비스 지역 데이터 => Region Code

#### Region Code 할당 규칙

생태 관광 정보 프로그램으로 등록된 데이터의 '서비스 지역'은 보통 주소로 입력되어 있다. 

각 데이터에 Region Code 를 할당하기 전에 몇 가지 규칙을 세웠다.


1. Region Code 할당 방법

각 지역구에는 지역 코드가 있다. Kakao API 중 주소 검색 API를 주소와 함께 호출하면 h_code (행정 코드) 를 받아올 수 있어서 이 API 를 이용했다. 
h_code 는 10자리의 숫자로 이루어져 있다.


2. Region Code의 범위 : 시 군 구

'경기도 성남시 분당구' 와  '경기도 성남시 수정구' 두 개의 데이터가 있으면 '경기도 성남시' 로 통일할 수 있도록 했다.

Kakao API 에서 받아온 10자리의 h_code는 시/군/구 까지는 앞 다섯자리가 동일하다. 따라서, 받아온 h_code를 앞 다섯자리만 잘라서 Region Code로 사용하기로 했다.


3. 쉼표(,) 또는 물결(~) 이 있는 서비스 지역 데이터

데이터셋 중에서 어떤 데이터는 서비스 지역이 '강원도 속초, 양양, 고성' 이라고 입력되어 있기도 하고, '전라남도 완도군~여수시'로 되어있는 것도 있다. 

이러한 데이터는 각각 '강원도', '전라남도'로 지역구 이름을 정했다. 


4. 국립공원, 산 등의 주소 데이터

'강원도 오대산국립공원', '충청도 월악산'과 같은 데이터가 있다. 이 데이터로 API를 호출하게 되면 결과가 나오지 않는다. 따라서 이 경우에도 각각 '강원도', '충청도'로 지역구 이름을 정했다.



5. 검색 결과가 나오지 않는 경우

이 프로젝트를 위해 사용한 데이터셋의 서비스 지역 데이터 중에서는 아예 검색 결과가 나오지 않는 경우가 없었다. 왜냐하면 '경기도'라는 데이터만 있어도 검색이 되기 때문이다. 

하지만 추후에 아예 검색이 되지 않는 데이터가 입력될 경우 사용자 정의 예외 APINotFoundAddressException 를 발생시켰으며, 현재 프로젝트에서는 API가 주소를 검색할 수 없다는 내용의 로그를 출력하고, 문자열의 hashcode를 Region Code로 할당하는 것으로 했다.




#### Response VO

Region Code 검색을 위해 사용한 kakao 주소 검색 API 는 결과를 json 형태로 반환한다. 

현재 프로젝트에서 필요한 주소 관련 데이터는 '지역 코드', '지역구 이름' 이다. 따라서, API 응답 결과에서 필요한 데이터만을 매핑하여 쓸 수 있는 VO 클래스를 따로 작성하여 좀 더 명확하게 했다.

~~~~
@JsonIgnoreProperties(ignoreUnknown = true)
class APIResponseVO {

    @JsonProperty
    private List<Document> documents;

    private static class Document {

        @JsonProperty
        private Address address;

        private static class Address {

            @JsonProperty
            private String address_name;

            @JsonProperty
            private String h_code;
        }
    }
}
~~~~

응답 결과인 json의 데이터 구조 방식을 파악하고, 필요한 address_name과 h_code만을 받아왔다.


~~~~
    Optional<RegionEntity> getRegion() {
        return documents.isEmpty() ? Optional.empty()
                : Optional.of(documents.get(0).address.getRegion());
    }
~~~~

그리고 위와 같은 메소드를 VO 클래스 내부에 작성해서 documents가 비어있으면(검색 결과 없음) 비어있는 Optional 객체를 반환하고, 검색 결과가 존재하면 RegionCode와 RegionName이 담긴 RegionEntity 를 반환하도록 했다.




#### 서비스 분리

- APISearchAddressService.class

사실 이 kakao API를 호출하는 경우는 ManageProgramService에서 생태 관광 정보를 저장(saveEcoProgram()) 하는 상황 뿐이다.

하지만 외부 API 를 연동하는 서비스이며 Transactional 설정을 사용하고 있는 ManageProgramService 클래스에서 분리되어야 한다고 생각했으며 '주소 검색'이라는 확실한 역할을 담당하고 있어, APISearchAddressService 라는 클래스로 분리하였다. 

그리고 api key와 api url은 /resources의 kakaoapi.properties 에 따로 설정 정보를 저장하여, api 관련 정보를 관리할 수 있게 했다.


- RegionInfoRefiner.class

서비스 지역 데이터를 주소 검색 API 에 사용했을 때 정확한 결과를 받을 수 있도록 '서비스 지역 주소 값'을 정제해야 할 케이스가 많이 존재했기 때문에 서비스 지역 값을 정제하는 역할만 담당하는 RegionInfoRefiner 라는 클래스를 작성하여 역할을 분리했다.


