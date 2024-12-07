package me.jimmyberg.springredis.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory

@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}") private val host: String,
    @Value("\${spring.data.redis.port}") private val port: Int
) {

    @Bean
    fun lettuceConnectionFactory(): LettuceConnectionFactory {
        return redisConnectionFactory()
    }

    private fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory(host, port).also { it.afterPropertiesSet() }
    }

}