package me.jimmyberg.springjpa.account.repository.study

import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import me.jimmyberg.springjpa.util.printStep
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Repository
class AccountTransactionStudyRepository : AccountStudyRepository() {
    // logger
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun test01(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).printStep(1)
        entity.incrementAmount(amount).printStep(2)
        return saveEntity(entity).printStep(3)
    }

}