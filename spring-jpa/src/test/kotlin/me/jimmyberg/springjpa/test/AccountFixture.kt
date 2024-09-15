package me.jimmyberg.springjpa.test

import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import java.util.UUID

class AccountFixture {

    fun generateAccountNo(): String {
        return UUID.randomUUID().toString()
    }

    fun generateEntity(
        accountNo: String = generateAccountNo(),
        amount: Long = 0,
        beforeAmount: Long = 0
    ): AccountEntity {
        return AccountEntity(accountNo = accountNo, amount = amount, beforeAmount = beforeAmount)
    }

}