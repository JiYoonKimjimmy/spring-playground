package me.jimmyberg.springjpa.account.repository

import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import me.jimmyberg.springjpa.util.printLog
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Repository
class AccountRepository01(
    private val accountJpaRepository: AccountJpaRepository
) {
    // logger
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun test01(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo) // 1. select
        return saveEntity(entity.incrementAmount(amount)) // 2. insert
    }

    fun test02(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).also { logger.printLog(1) } // 1. select
        test02Sub(entity, amount)
        val saved = saveEntity(entity.incrementAmount(amount)) // 3. select > update
        return saved.also { logger.printLog(3) }
    }

    private fun test02Sub(entity: AccountEntity, amount: Long): AccountEntity {
        val saved = saveEntity(entity.incrementAmount(amount)) // 2. insert
        return saved.also { logger.printLog(2) }
    }

    @Transactional
    fun test03(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).also { logger.printLog(1) } // 1. select
        test03Sub(entity, amount)
        val saved = saveEntity(entity.incrementAmount(amount))
        return saved.also { logger.printLog(3) } // 3. update
    }

    private fun test03Sub(entity: AccountEntity, amount: Long): AccountEntity {
        val saved = saveEntity(entity.incrementAmount(amount)) // 2. insert
        return saved.also { logger.printLog(2) }
    }

    @Transactional
    fun test04(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).also { logger.printLog(1) } // 1. select
        test04Sub(entity, amount)
        val saved = saveEntity(entity.incrementAmount(amount))
        return saved.also { logger.printLog(3) } // 3. update
    }

    @Transactional
    fun test04Sub(entity: AccountEntity, amount: Long): AccountEntity {
        val saved = saveEntity(entity.incrementAmount(amount)) // 2. insert
        return saved.also { logger.printLog(2) }
    }

    @Transactional
    fun test05(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).also { logger.printLog(1) } // 1. select
        test05Sub(entity, amount)
        val saved = saveEntity(entity.incrementAmount(amount))
        return saved.also { logger.printLog(3) } // 3. update
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun test05Sub(entity: AccountEntity, amount: Long): AccountEntity {
        val saved = saveEntity(entity.incrementAmount(amount)) // 2. insert
        return saved.also { logger.printLog(2) }
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