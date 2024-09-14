package me.jimmyberg.springjpa.account.repository

import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccountJpaRepository : JpaRepository<AccountEntity, Long> {

    fun findByAccountNo(accountNo: String): AccountEntity?

}