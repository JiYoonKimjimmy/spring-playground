package me.jimmyberg.springredis.testsupport

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import redis.embedded.RedisServer

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

    fun lettuceConnectionFactory(): LettuceConnectionFactory {
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