package me.jimmyberg.springredis.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import redis.embedded.RedisServer

@Configuration
class EmbeddedRedisConfig(
    @Value("\${spring.data.redis.port}") private val port: Int
) {

    private val embeddedRedisServer: RedisServer by lazy {
        RedisServer
            .newRedisServer()
            .port(port)
            .build()
    }

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