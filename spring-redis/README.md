# Hello, Spring Data Redis!

- 캐시, 메시지 브로커 등 다양한 데이터 관리 활용성을 가진 **Redis**를 조금 더 Spring 환경에서 **잘 활용할 수 있는** 방법에 대해 학습 목적
- 반복적, 개발 환경 국한되지 않는 Redis 단위 테스트를 위해서 **Embedded Redis** 라이브러리를 활용한 단위 테스트 코드 작성
- **Spring Data Redis** 제공 인터페이스 활용하거나, **RedisTemplate**을 활용한 Cache 데이터 관리
- `Lettuce` 라이브러리와 같은 Redis Client 라이브러리 특장점 정리

---

## Embedded Redis

`Embedded Redis` 를 활용한다면, 테스트 코드 또는 애플리케이션 실행할 때 **별도 `Redis` 서버를 구축하지 않더라도 `Redis` 사용이 가능하다.**

> ### [Embedded Redis 설정 관련 정리](./docs/01_embedded-redis.md)

---