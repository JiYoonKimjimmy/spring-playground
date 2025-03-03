package me.jimmyberg.springredis.testsupport

import redis.embedded.RedisServer

class EmbeddedRedis {

    companion object {
        const val REDIS_HOST = "localhost"
        const val REDIS_PORT = 6379
    }

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