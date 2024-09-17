package me.jimmyberg.springjpa.account.repository.study

import me.jimmyberg.springjpa.account.repository.AccountJpaRepository
import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import me.jimmyberg.springjpa.util.printLog
import me.jimmyberg.springjpa.util.printStep
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Repository
class AccountTransactionStudyRepository(
    private val accountJpaRepository: AccountJpaRepository
) {
    // logger
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun test01(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo)
        return saveEntity(entity.incrementAmount(amount))
    }

    fun test02(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).also { logger.printStep(1) }
        test02Sub(entity, amount)
        val saved = saveEntity(entity.incrementAmount(amount))
        return saved.also { logger.printStep(3) }
    }

    private fun test02Sub(entity: AccountEntity, amount: Long): AccountEntity {
        val saved = saveEntity(entity.incrementAmount(amount))
        return saved.also { logger.printStep(2) }
    }

    @Transactional
    fun test03(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).also { logger.printStep(1) }
        test03Sub(entity, amount)
        val saved = saveEntity(entity.incrementAmount(amount))
        return saved.also { logger.printStep(3) }
    }

    private fun test03Sub(entity: AccountEntity, amount: Long): AccountEntity {
        val saved = saveEntity(entity.incrementAmount(amount))
        return saved.also { logger.printStep(2) }
    }

    @Transactional
    fun test04(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).also { logger.printStep(1) }
        test04Sub(entity, amount)
        val saved = saveEntity(entity.incrementAmount(amount))
        return saved.also { logger.printStep(3) }
    }

    @Transactional
    fun test04Sub(entity: AccountEntity, amount: Long): AccountEntity {
        val saved = saveEntity(entity.incrementAmount(amount))
        return saved.also { logger.printStep(2) }
    }

    @Transactional
    fun test05(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).also { logger.printStep(1) }
        test05Sub(entity, amount)
        val saved = saveEntity(entity.incrementAmount(amount))
        return saved.also { logger.printStep(3) }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun test05Sub(entity: AccountEntity, amount: Long): AccountEntity {
        val saved = saveEntity(entity.incrementAmount(amount))
        return saved.also { logger.printStep(2) }
    }

    private fun findEntity(accountNo: String): AccountEntity {
        logger.printLog("START [findByAccountNo]")
        val find = accountJpaRepository.findByAccountNo(accountNo) ?: AccountEntity(accountNo = accountNo)
        logger.printLog("END [findByAccountNo]")
        return find
    }

    private fun saveEntity(entity: AccountEntity): AccountEntity {
        logger.printLog("START [saveEntity]")
        val saved = accountJpaRepository.save(entity)
        logger.printLog("END [saveEntity]")
        return saved
    }

}