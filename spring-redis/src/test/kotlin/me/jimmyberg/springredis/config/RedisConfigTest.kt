package me.jimmyberg.springredis.config

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import
import org.springframework.data.redis.core.StringRedisTemplate

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