package me.jimmyberg.springredis.member.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "member")
class MemberEntity(
    @Id
    val id: Long,
    val name: String
)