package me.jimmyberg.springjpa.member.repository.entity

import jakarta.persistence.*

@Table(name = "MEMBERS")
@Entity
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String
)