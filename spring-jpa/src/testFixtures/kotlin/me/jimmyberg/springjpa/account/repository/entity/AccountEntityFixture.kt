package me.jimmyberg.springjpa.account.repository.entity

import me.jimmyberg.springjpa.card.repository.entity.CardEntity
import me.jimmyberg.springjpa.member.repository.entity.MemberEntity
import me.jimmyberg.springjpa.util.generateUUID

class AccountEntityFixture {

    fun make(
        accountNo: String = generateUUID(),
        amount: Long = 0,
        beforeAmount: Long = 0
    ): AccountEntity {
        return AccountEntity(accountNo = accountNo, amount = amount, beforeAmount = beforeAmount)
    }

    fun make(
        accountNo: String,
        amount: Long = 0,
        member: MemberEntity,
        card: CardEntity? = null
    ): V2AccountEntity {
        return V2AccountEntity(accountNo = accountNo, amount = amount, member = member, card = card)
    }

}