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

---
