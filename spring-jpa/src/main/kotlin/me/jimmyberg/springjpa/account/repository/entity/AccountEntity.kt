package me.jimmyberg.springjpa.account.repository.entity

import jakarta.persistence.*
import org.slf4j.LoggerFactory

@Table(name = "ACCOUNTS")
@Entity(name = "Account")
class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val accountNo: String,
    var amount: Long,
    var beforeAmount: Long,
) {

    constructor(accountNo: String): this(
        accountNo = accountNo,
        amount = 0,
        beforeAmount = 0
    )

    fun incrementAmount(amount: Long): AccountEntity {
        val logger = LoggerFactory.getLogger("$this")
        logger.info("START [incrementAmount]")
        this.beforeAmount = amount
        this.amount += amount
        logger.info("END [incrementAmount]")
        return this
    }

}