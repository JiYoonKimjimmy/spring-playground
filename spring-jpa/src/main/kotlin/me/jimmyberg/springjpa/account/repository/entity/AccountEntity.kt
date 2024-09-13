package me.jimmyberg.springjpa.account.repository.entity

import jakarta.persistence.*

@Table(name = "ACCOUNTS")
@Entity(name = "Account")
class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val accountNo: String,
    val amount: Long,
    val beforeAmount: Long,
)