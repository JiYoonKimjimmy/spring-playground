package me.jimmyberg.springjpa.member.repository.entity

class MemberEntityFixture {

    fun generateEntity(name: String): MemberEntity {
        return MemberEntity(
            name = name
        )
    }

}