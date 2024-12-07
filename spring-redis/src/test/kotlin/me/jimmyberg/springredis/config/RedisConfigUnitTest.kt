package me.jimmyberg.springredis.config

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

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