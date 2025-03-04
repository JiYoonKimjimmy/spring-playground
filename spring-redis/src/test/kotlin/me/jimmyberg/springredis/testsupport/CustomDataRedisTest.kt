package me.jimmyberg.springredis.testsupport

import me.jimmyberg.springredis.config.EmbeddedRedisConfig
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import

@Import(EmbeddedRedisConfig::class)
@DataRedisTest
annotation class CustomDataRedisTest