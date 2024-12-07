# Hello, Spring Data Redis!

- 캐시, 메시지 브로커 등 다양한 데이터 관리 활용성을 가진 **Redis**를 조금 더 Spring 환경에서 **잘 활용할 수 있는** 방법에 대해 학습 목적
- 반복적, 개발 환경 국한되지 않는 Redis 단위 테스트를 위해서 **Embedded Redis** 라이브러리를 활용한 단위 테스트 코드 작성
- **Spring Data Redis** 제공 인터페이스 활용하거나, **RedisTemplate**을 활용한 Cache 데이터 관리
- `Lettuce` 라이브러리와 같은 Redis Client 라이브러리 특장점 정리

---

## Embedded Redis

- 테스트 코드를 작성할 때 중요한 점 중 하나가 **반복적이면서, 개발 환경 영향 없는** 테스트 코드를 작성해야 한다고 생각한다.
- Redis 테스트를 위해 로컬 Redis 서버를 설치하거나 할 수 있지만, 테스트 코드 수행 시에만 실행되는 **Embedded Redis** 라이브러리 적용을 검토하게 되었다.

> #### Embedded Redis 라이브러리 : [codemonstur/embedded-redis](https://github.com/codemonstur/embedded-redis)
>
> 많은 라이브러리를 거쳐 현재 `codemonstur` 에서 개발하고 있는 라이브러리만이 유지되고 있는 것 같다. (24/11/23 기준 한달전 커밋 기록)

### Redis Configuration

- 먼저 실제 프로덕트 코드의 Redis 기본 설정을 정의한다.

```kotlin
@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}") private val host: String,  // localhost
    @Value("\${spring.data.redis.port}") private val port: Int      // 6379
) {

    @Bean
    fun lettuceConnectionFactory(): LettuceConnectionFactory {
        return redisConnectionFactory()
    }

    private fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory(host, port).also { it.afterPropertiesSet() }
    }

}
```

##### Test Code

- Redis Server 의 연결 확인을 위해 `ping` 요청하여 정상 기동 확인한다.
- 하지만, 아직 별도의 Redis Server 를 로컬에 설치/기동하지 않기 때문에 에러가 발생할 것이다.

```kotlin
@DataRedisTest
class RedisConfigTest(
    private val stringRedisTemplate: StringRedisTemplate
) : StringSpec({

    "Redis 연결 테스트 시도하여 정상 확인한다" {
        // given
        val connection = stringRedisTemplate.connectionFactory!!.connection

        // when
        val result = connection.ping()

        // then
        result shouldBe "PONG"
    }

})
```

```text
Caused by: io.netty.channel.AbstractChannel$AnnotatedConnectException: Connection refused: localhost/127.0.0.1:6379
Caused by: java.net.ConnectException: Connection refused
```

---

### Embedded Redis Configuration

- 이제, `localhost:6379` url 정보를 갖는 Redis 서버를 **Embedded Redis** 로 정의하고 기동한다.
- Single Server 환경의 Redis 서버를 생성하고, Config 클래스의 라이프사이클에 맞춰 `start()` & `stop()` 처리한다.

```kotlin
@TestConfiguration
class EmbeddedRedisConfig(
    @Value("\${spring.data.redis.port}") private val port: Int
) {

    private val embeddedRedisServer: RedisServer = RedisServer
        .newRedisServer()
        .port(port)
        .build()

    @PostConstruct
    fun startup() {
        embeddedRedisServer.start()
    }

    @PreDestroy
    fun shutdown() {
        if (embeddedRedisServer.isActive) {
            embeddedRedisServer.stop()
        }
    }

}
```

##### Test Code

- Test Configuration 으로 정의한 `EmbeddedRedisConfig` 를 테스트 코드에 주입한다.
- 해당 테스트 코드를 성공하는 걸 알 수 있다.

```kotlin
@Import(EmbeddedRedisConfig::class)
@DataRedisTest
class RedisConfigTest(
    private val stringRedisTemplate: StringRedisTemplate
) : StringSpec({

    "Redis 연결 테스트 시도하여 정상 확인한다" {
        // given
        val connection = stringRedisTemplate.connectionFactory!!.connection

        // when
        val result = connection.ping()

        // then
        result shouldBe "PONG"
    }

})
```

---

#### References.

- [Codemonstur - embedded-redis](https://github.com/codemonstur/embedded-redis)

---