package me.jimmyberg.springjpa.test

import me.jimmyberg.springjpa.member.repository.entity.MemberEntity

class MemberFixture {

    fun generateEntity(name: String): MemberEntity {
        return MemberEntity(
            name = name
        )
    }

}