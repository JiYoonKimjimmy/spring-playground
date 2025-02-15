package me.jimmyberg.springjpa.account.repository

import me.jimmyberg.springjpa.account.repository.entity.V2AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface V2AccountJpaRepository : JpaRepository<V2AccountEntity, Long>