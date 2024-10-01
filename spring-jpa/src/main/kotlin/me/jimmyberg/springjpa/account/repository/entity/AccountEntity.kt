package me.jimmyberg.springjpa.account.repository.entity

import jakarta.persistence.*
import org.slf4j.LoggerFactory

@Table(name = "ACCOUNTS")
@Entity
open class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,
    open val accountNo: String,
    open var amount: Long,
    open var beforeAmount: Long,
) {

    constructor(accountNo: String): this(
        accountNo = accountNo,
        amount = 0,
        beforeAmount = 0
    )

    fun incrementAmount(amount: Long): AccountEntity {
        val logger = LoggerFactory.getLogger("$this")
        logger.info("START [incrementAmount]")
        this.beforeAmount = this.amount
        this.amount += amount
        logger.info("END [incrementAmount]")
        return this
    }

}