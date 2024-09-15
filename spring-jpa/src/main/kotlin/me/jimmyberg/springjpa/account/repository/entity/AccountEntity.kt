package me.jimmyberg.springjpa.account.repository.entity

import jakarta.persistence.*

@Table(name = "ACCOUNTS")
@Entity(name = "Account")
class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val accountNo: String,
    var amount: Long,
    val beforeAmount: Long,
) {

    constructor(accountNo: String): this(
        accountNo = accountNo,
        amount = 0,
        beforeAmount = 0
    )

    fun incrementAmount(amount: Long): AccountEntity {
        this.amount += amount
        return this
    }

}