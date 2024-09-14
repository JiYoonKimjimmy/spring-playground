package me.jimmyberg.springjpa.account.repository

import me.jimmyberg.springjpa.account.repository.entity.AccountEntity

interface AccountRepository {

    fun save(entity: AccountEntity): AccountEntity

    fun findByAccountNo(accountNo: String): AccountEntity?

}