package me.jimmyberg.springredis.member.service

import me.jimmyberg.springredis.member.repository.entity.MemberEntity

interface MemberFindService {

    fun findOne(id: Long): MemberEntity?

}