package me.jimmyberg.springredis.testsupport

import io.kotest.core.listeners.AfterSpecListener
import io.kotest.core.listeners.BeforeSpecListener
import io.kotest.core.spec.Spec

class EmbeddedRedisTestListener : BeforeSpecListener, AfterSpecListener {

    private val embeddedRedisTestConfig: EmbeddedRedisTestConfig by lazy {
        EmbeddedRedisTestConfig(EmbeddedRedis())
    }

    override suspend fun beforeSpec(spec: Spec) {
        embeddedRedisTestConfig.startup()
    }

    override suspend fun afterSpec(spec: Spec) {
        embeddedRedisTestConfig.shutdown()
    }

}