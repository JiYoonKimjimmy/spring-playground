# Hello, Spring Data JPA!

- Entity 관리
- Transaction 관리
- Optimistic Lock(낙관적 락) vs Pessimistic Lock(비관적 락)
- Dynamic Query 지원 라이브러리
  - QueryDSL
  - KotlinJDSL
- Paging Query 처리 
  - 페이징 쿼리 성능 이슈 & 최적화 방법
- **Exposed** 적용 검토

---

## Entity 관리

- 영속성 컨텍스트 관리
- 관계 매핑
- Fetch 전략
- 2차 캐시
- `N+1` 문제

### Persistence Context 영속성 컨텍스트

`Persistence Context` **영속성 컨텍스트**는 Entity 엔티티 객체를 관리하고,
데이터베이스와의 상호작용을 중재하는 역할을 한다.

- 엔티티 객체의 상태를 추적하고,
- 데이터베이스와의 동기화를 관리하며,
- 데이터 일관성을 유지하고,
- 성능을 최적화할 수 있다.

#### 영속성 관리

- 엔티티의 영속성은 `@Id` 정보 기준으로 관리된다.
- 영속성 컨텍스트에 영속화된 엔티티는 `@Id` 정보 조건으로 다시 조회하더라도 동일한 엔티티를 반환한다.
  - 하지만, 다른 컬럼(프로퍼티) 조건으로 조회하는 경우 다시 `select` 쿼리가 수행된다.

##### `@Transactional` 애노테이션과 관계

- 영속화된 엔티티에 변경이 발생한 경우, `Dirty Checking` 을 통해서 영속성 컨텍스트는 엔티티의 변경을 감지한다.
- `@Transactional` 애노테이션이 선언되어 있는 함수에서 엔티티의 변경 정보는 트랜잭션 커밋 순간 `update` 쿼리가 수행된다.
- 하지만 `@Transactional` 애노테이션 없이 명시적으로 `save()` 함수로 엔티티 변경을 반영하는 함수에서는 `select > update` 쿼리가 수행된다.
  - 이 경우 엔티티가 실제 DB 존재 여부 확인을 위해 `select` 쿼리가 먼저 수행된다.

---
