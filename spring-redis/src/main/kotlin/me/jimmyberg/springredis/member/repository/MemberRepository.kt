package me.jimmyberg.springredis.member.repository

import me.jimmyberg.springredis.member.repository.entity.MemberEntity

interface MemberRepository {

    fun save(entity: MemberEntity): MemberEntity

    fun findById(id: Long): MemberEntity?

}