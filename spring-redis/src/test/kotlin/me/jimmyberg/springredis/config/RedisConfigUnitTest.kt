package me.jimmyberg.springredis.config

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RedisConfigUnitTest : StringSpec({

    extensions(EmbeddedRedisTestListener)

    val connectionFactory = EmbeddedRedisTestConfig.lettuceConnectionFactory()
    val stringRedisTemplate = EmbeddedRedisTestConfig.stringRedisTemplate()

    "Redis 연결 테스트 시도하여 정상 확인한다" {
        // given
        val connection = connectionFactory.connection

        // when
        val result = connection.ping()

        // then
        result shouldBe "PONG"
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