# Embedded Redis

- 테스트 코드를 작성할 때 중요한 점 중 하나가 **반복적이면서, 개발 환경 영향 없는** 테스트 코드를 작성해야 한다고 생각한다.
- Redis 테스트를 위해 로컬 Redis 서버를 설치하거나 할 수 있지만, 테스트 코드 수행 시에만 실행되는 **Embedded Redis** 라이브러리 적용을 검토하게 되었다.

> #### Embedded Redis 라이브러리 : [codemonstur/embedded-redis](https://github.com/codemonstur/embedded-redis)
>
> 많은 라이브러리를 거쳐 현재 `codemonstur` 에서 개발하고 있는 라이브러리만이 유지되고 있는 것 같다. (24/11/23 기준 한달전 커밋 기록)

## Redis Configuration

- `Embedded Redis` 설정하기 앞서, 실제 프로덕트 코드의 `Redis` 기본 설정을 정의한다.
- `Redis` 기본 설정한 후에 해당 설정이 `Embedded Redis` 를 바라보도록 프로퍼티 설정을 할 것이다.

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

## Embedded Redis Configuration

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

### Bean 주입 없이 단위 테스트 코드 작성

- Redis 서버 설정과 Embedded Redis 기동을 위해 `@Configuration` 클래스를 정의하고,
- 테스트 코드의 `@DataJpaTest`, `@Import` 와 같은 Spring 의존성 애노테이션의 적용이 필요하였다.
- 하지만, 프레임워크의 의존성을 조금이나마 덜어내고, 단위 테스트 코드를 작성하기 위해서 `Kotest` 의 **`TestListener`** 를 적용하고자 한다.

> [Kotest - Lifecycle hooks](https://kotest.io/docs/framework/lifecycle-hooks.html)
> [Kotest - Simple Extensions](https://kotest.io/docs/framework/extensions/simple-extensions.html)

---

### EmbeddedRedisTestConfig

- `Embedded Redis` 단위 테스트 설정을 위해 기본 Redis 설정을 `object` 테스트 클래스로 새로 정의를 한다.
- `EmbeddedRedisTestConfig` 클래스는 `EmbeddedRedis` 와 `RedisTemplate` 설정 함수를 관리하여 `Bean` 주입을 대체한다.
- 그 다음, `Kotest TestListener` 를 정의하여 설정한 `EmbeddedRedis` 서버를 기동/종료 하도록 한다.

```kotlin
object EmbeddedRedisTestConfig {

    fun stringRedisTemplate(): RedisTemplate<String, String> {
        return RedisTemplate<String, String>()
            .apply {
                this.keySerializer = StringRedisSerializer()
                this.valueSerializer = GenericJackson2JsonRedisSerializer()
                this.connectionFactory = lettuceConnectionFactory()
            }
            .also { it.afterPropertiesSet() }
    }

    private fun lettuceConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory(EmbeddedRedis.REDIS_HOST, EmbeddedRedis.REDIS_PORT)
            .also { it.afterPropertiesSet() }
    }

    fun startup() {
        EmbeddedRedis.startup()
    }

    fun shutdown() {
        EmbeddedRedis.shutdown()
    }

    object EmbeddedRedis {

        const val REDIS_HOST = "localhost"
        const val REDIS_PORT = 6379

        private val embeddedRedisServer: RedisServer by lazy {
            RedisServer
                .newRedisServer()
                .port(REDIS_PORT)
                .build()
        }

        fun startup() {
            embeddedRedisServer.start()
        }

        fun shutdown() {
            if (embeddedRedisServer.isActive) {
                embeddedRedisServer.stop()
            }
        }

    }

}
```

---

## EmbeddedRedisTestListener

- `Kotest` 의 `Spec` 단위로 시작/종료 시점에 각각 특정 로직을 수행하도록 도와주는 `BeforeSpecListener`, `AfterSpecListener` 를 구현한다.
- 각 구현 함수에서 `EmbeddedRedisTestConfig` 클래스이 `startup()`, `stutdown()` 함수를 호출하여 `EmbeddedRedisServer` 라이프사이클을 관리한다.

```kotlin
object EmbeddedRedisTestListener : BeforeSpecListener, AfterSpecListener {

    override suspend fun beforeSpec(spec: Spec) {
        EmbeddedRedisTestConfig.startup()
    }

    override suspend fun afterSpec(spec: Spec) {
        EmbeddedRedisTestConfig.shutdown()
    }

}
```

##### Test Code

- `EmbeddedRedisTestListener` 구현 클래스를 `Kotest Extensions` 등록하여 해당 `Spec` 실행 전/후 Embedded Redis 를 관리한다.
- 기존 `EmbeddedRedisTestConfig` 정의한 `stringRedisTemplat` 으로 `Bean` 주입을 대체한다.

```kotlin
class RedisConfigUnitTest : StringSpec({

    extensions(EmbeddedRedisTestListener)

    val stringRedisTemplate = EmbeddedRedisTestConfig.stringRedisTemplate()

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