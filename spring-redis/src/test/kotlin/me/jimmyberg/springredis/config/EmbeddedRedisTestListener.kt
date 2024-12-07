package me.jimmyberg.springredis.config

import io.kotest.core.listeners.AfterSpecListener
import io.kotest.core.listeners.BeforeSpecListener
import io.kotest.core.spec.Spec

object EmbeddedRedisTestListener : BeforeSpecListener, AfterSpecListener {

    override suspend fun beforeSpec(spec: Spec) {
        EmbeddedRedisTestConfig.startup()
    }

    override suspend fun afterSpec(spec: Spec) {
        EmbeddedRedisTestConfig.shutdown()
    }

}