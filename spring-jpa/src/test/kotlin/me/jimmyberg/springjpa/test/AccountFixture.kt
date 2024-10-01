package me.jimmyberg.springjpa.test

import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import me.jimmyberg.springjpa.util.generateUUID

class AccountFixture {

    fun generateEntity(
        accountNo: String = generateUUID(),
        amount: Long = 0,
        beforeAmount: Long = 0
    ): AccountEntity {
        return AccountEntity(accountNo = accountNo, amount = amount, beforeAmount = beforeAmount)
    }

}