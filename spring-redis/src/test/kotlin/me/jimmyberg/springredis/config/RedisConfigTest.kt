package me.jimmyberg.springredis.config

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate

@Import(EmbeddedRedisConfig::class)
@DataRedisTest
class RedisConfigTest(
    private val lettuceConnectionFactory: LettuceConnectionFactory,
    private val stringRedisTemplate: StringRedisTemplate
) : StringSpec({

    "Redis 연결 테스트 시도하여 정상 확인한다" {
        // given
        val connection = lettuceConnectionFactory.connection
        val port = lettuceConnectionFactory.port

        // when then
        connection.ping() shouldBe "PONG"
        port shouldBe 16379
    }

    "Redis key-value 데이터 저장하여 정상 확인한다" {
        // given
        val key = "test-key"
        val value = "Hello World!"

        // when
        stringRedisTemplate.opsForValue().set(key, value)

        // then
        val result = stringRedisTemplate.opsForValue().get(key)
        result shouldBe "Hello World!"
    }

})