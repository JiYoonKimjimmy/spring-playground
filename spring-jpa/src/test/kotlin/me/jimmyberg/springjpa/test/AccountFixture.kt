package me.jimmyberg.springjpa.test

import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import me.jimmyberg.springjpa.account.repository.entity.AccountEntityV2
import me.jimmyberg.springjpa.member.repository.entity.MemberEntity
import java.util.UUID

class AccountFixture {

    fun generateAccountNo(): String {
        return UUID.randomUUID().toString()
    }

    private fun generateMemberName(): String {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10)
    }

    fun generateEntity(
        accountNo: String = generateAccountNo(),
        amount: Long = 0,
        beforeAmount: Long = 0
    ): AccountEntity {
        return AccountEntity(accountNo = accountNo, amount = amount, beforeAmount = beforeAmount)
    }

    fun generateEntityV2(
        accountNo: String = generateAccountNo(),
        amount: Long = 0,
        beforeAmount: Long = 0,
        member: MemberEntity = MemberEntity(name = generateMemberName())
    ): AccountEntityV2 {
        return AccountEntityV2(accountNo = accountNo, amount = amount, beforeAmount = beforeAmount, member = member)
    }

}