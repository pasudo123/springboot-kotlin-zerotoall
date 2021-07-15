## springboot-concurrency-basis
동시성 이슈에 대해서 레디스로 극복하기 위함 <BR>

## 동시성 이슈란?
다수의 클라이언트가 한정된 자원에 대해서 점유하고자 할 때, <br>
자원의 무분별은 할당을 막는 것을 의미한다.

## 동시성 이슈가 제기될만한 문제들
* 콘서트의 티켓팅 현장
* 한정된 갯수의 이벤트성 쿠폰 발급
* 예약과 밀접하게 연관된 서비스들
* 그 외, 한정된 자원을 점유하려고 하는 도메인들

## 동시성 이슈를 이해하기 위해서 알면 좋은 개념들
* 이벤트루프
* race-condition
* thread-safe

## 동시성 이슈에 레디스가 등장하는 이유
* 레디스는 싱글스레드로 동작한다. 이벤트루프 덕분에 동시성 처리 가능하지만 결국 싱글로 동작한다.
* 싱글스레드로 동작한다는 것은 결국 원자성 성질을 보존하는 것이다.
* 이는 결국 thread-safe 하다는 것을 의미하고 thread-safe 는 결과적으로 race-condition 에 대해 방지할 수 있게 해준다.
* race-condition 이란, 멀티스레드 환경에서 단일 자원을 다수의 스레드가 점유하게 되면 결과값이 변경됨을 의미한다.
* spring redis-template 은 thread-safe 하다. [참고](https://github.com/spring-projects/spring-data-redis/blob/b6820f0f61ad6ecfa157253a24490f531983a812/src/main/java/org/springframework/data/redis/core/RedisTemplate.java#L72)

## 해당 프로젝트의 요구사항
* 하나의 콘서트당 발급되는 티켓의 갯수는 제한적이다.
    * ex) 2021년 여름 아이유 콘서트, 티켓은 10000개 발급된다.
    * 위의 경우로 따지면 티켓은 100001 개가 발급될 수 없다. 동시에 요청이 오더라도 조금 더 빨리 온 `누군가` 에게 티켓이 1개더 발급되고 티켓팅은 종료된다.

## 디비 스키마
* 스키마 설계는 JPA 로 가볍게 수행한다.

## run
```shell
$ ./ docker-compose up -d
```

<HR>

## 추가적으로 고려해볼만한 것
* 동일한 요청이 동시에 오는 경우도 있다.
    