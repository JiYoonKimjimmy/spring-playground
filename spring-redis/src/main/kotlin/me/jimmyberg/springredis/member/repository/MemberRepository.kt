package me.jimmyberg.springredis.member.repository

import me.jimmyberg.springredis.member.repository.entity.MemberEntity
import org.springframework.stereotype.Repository

@Repository
class MemberRepository(
    private val memberCrudRepository: MemberCrudRepository
) {

    fun save(entity: MemberEntity): MemberEntity {
        return memberCrudRepository.save(entity)
    }

    fun findById(id: Long): MemberEntity? {
        return memberCrudRepository.findById(id).orElse(null)
    }

}