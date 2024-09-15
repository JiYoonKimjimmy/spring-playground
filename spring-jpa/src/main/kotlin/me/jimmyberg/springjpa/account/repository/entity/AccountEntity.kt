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

    fun incrementAmount(amount: Long): AccountEntity {
        this.amount += amount
        return this
    }

}