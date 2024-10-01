package me.jimmyberg.springjpa.member.repository

import me.jimmyberg.springjpa.member.repository.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<MemberEntity, Long>