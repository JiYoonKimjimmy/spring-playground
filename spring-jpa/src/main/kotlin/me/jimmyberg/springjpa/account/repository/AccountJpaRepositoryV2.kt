package me.jimmyberg.springjpa.account.repository

import me.jimmyberg.springjpa.account.repository.entity.AccountEntityV2
import org.springframework.data.jpa.repository.JpaRepository

interface AccountJpaRepositoryV2 : JpaRepository<AccountEntityV2, Long> {

    fun findByAccountNo(accountNo: String): AccountEntityV2?

}