package me.jimmyberg.springredis.member.repository

import me.jimmyberg.springredis.member.repository.entity.MemberEntity
import org.springframework.data.repository.CrudRepository

interface MemberCrudRepository : CrudRepository<MemberEntity, Long>