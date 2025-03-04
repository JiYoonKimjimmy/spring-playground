package me.jimmyberg.springredis.member.service

import me.jimmyberg.springredis.member.repository.MemberRepository
import me.jimmyberg.springredis.member.repository.entity.MemberEntity
import org.springframework.stereotype.Service

@Service
class MemberFindServiceImpl(
    private val memberRepository: MemberRepository
) : MemberFindService {

    override fun findOne(id: Long): MemberEntity? {
        return memberRepository.findById(id)
    }

}