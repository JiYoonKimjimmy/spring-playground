package me.jimmyberg.springredis.member.repository

import me.jimmyberg.springredis.member.repository.entity.MemberEntity
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(
    private val memberCrudRepository: MemberCrudRepository
) : MemberRepository {

    override fun save(entity: MemberEntity): MemberEntity {
        return memberCrudRepository.save(entity)
    }

    override fun findById(id: Long): MemberEntity? {
        return memberCrudRepository.findById(id).orElse(null)
    }

}