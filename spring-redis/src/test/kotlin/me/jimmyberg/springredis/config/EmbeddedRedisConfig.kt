package me.jimmyberg.springredis.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import redis.embedded.RedisServer

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