## springboot-gql-basis
* springboot 와 graphql 같이 사용하기
* graphql plugins 도 인텔리제이에 설치해주자
    * `js graphql`

## graphql
1. 디렉토리 만들고 그 안에서 `npm install apollo-server graphql` 수행
    * apollo server 3.x
2. playground plugins 설치한다. https://www.apollographql.com/docs/apollo-server/testing/build-run-queries/#graphql-playground
    * playground 는 브라우저 환경에서 gql 에 대해 쿼리, 뮤테이션을 테스트할 수 있도록 해줄 수 있도록 도와준다.
3. datasource 이용 ()
    * apollo server 가 rest api 나 db 에서 데이터를 들고오기 위해 사용하는 클래스
    * RestDataSource 는 일반적으로 api 와 통신할때 사용한다.
        * willSendRequest 라는 메소드를 오버라이딩 하면 인터셉터의 효과를 얻을 수 있다.
4. error handling 가능
    * ApolloError 을 상속받아서 별도의 커스텀한 에러클래스를 만들어줄 수 있다.
    * ApolloServer 에서 formatError 을 설정하여 에러를 포맷팅해서 다른 에러로 치환하거나 로깅할 수 있다.
5. caching 은 잘 안되는듯?? 흠..

## graphql : federation

## 읽을 목차
* https://www.apollographql.com/docs/apollo-server/schema/schema/
* https://www.apollographql.com/docs/apollo-server/data/data-sources/#restdatasource-reference
* https://www.apollographql.com/docs/federation/
* https://www.apollographql.com/blog/backend/schema-design/modularizing-your-graphql-schema-code/
   * 스키마 모듈화
* [graphql 운영해보지 : deview](https://deview.kr/data/deview/session/attach/1100_T1_%E1%84%87%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A5%E1%86%BC%E1%84%92%E1%85%A7%E1%86%AB_GraphQL%20API%20%E1%84%81%E1%85%A1%E1%84%8C%E1%85%B5%E1%86%BA%E1%84%80%E1%85%A5%20%E1%84%8B%E1%85%AE%E1%86%AB%E1%84%8B%E1%85%A7%E1%86%BC%E1%84%92%E1%85%A2%E1%84%87%E1%85%A9%E1%84%8C%E1%85%B5%20%E1%84%86%E1%85%AF.pdf) 
    