package me.jimmyberg.springredis.testsupport

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

class EmbeddedRedisTestConfig(
    private val embeddedRedis: EmbeddedRedis
) {

     companion object {

        fun stringRedisTemplate(): RedisTemplate<String, String> {
            return RedisTemplate<String, String>()
                .apply {
                    this.keySerializer = StringRedisSerializer()
                    this.valueSerializer = GenericJackson2JsonRedisSerializer()
                    this.connectionFactory = lettuceConnectionFactory()
                }
                .also { it.afterPropertiesSet() }
        }

        fun lettuceConnectionFactory(): LettuceConnectionFactory {
            return LettuceConnectionFactory(EmbeddedRedis.REDIS_HOST, EmbeddedRedis.REDIS_PORT)
                .also { it.afterPropertiesSet() }
        }

     }

    fun startup() {
        embeddedRedis.startup()
    }

    fun shutdown() {
        embeddedRedis.shutdown()
    }

}