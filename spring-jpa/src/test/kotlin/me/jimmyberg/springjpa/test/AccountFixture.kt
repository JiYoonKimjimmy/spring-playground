package me.jimmyberg.springjpa.test

import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import me.jimmyberg.springjpa.account.repository.entity.AccountEntityV2
import me.jimmyberg.springjpa.member.repository.entity.MemberEntity
import me.jimmyberg.springjpa.util.generateUUID

class AccountFixture {

    fun generateEntity(
        accountNo: String = generateUUID(),
        amount: Long = 0,
        beforeAmount: Long = 0
    ): AccountEntity {
        return AccountEntity(accountNo = accountNo, amount = amount, beforeAmount = beforeAmount)
    }

    fun generateEntityV2(
        accountNo: String,
        amount: Long = 0,
        beforeAmount: Long = 0,
        member: MemberEntity
    ): AccountEntityV2 {
        return AccountEntityV2(accountNo = accountNo, amount = amount, beforeAmount = beforeAmount, member = member)
    }

}