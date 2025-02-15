package me.jimmyberg.springjpa.account.repository.study

import me.jimmyberg.springjpa.account.repository.AccountJpaRepository
import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import me.jimmyberg.springjpa.util.printLog
import org.slf4j.LoggerFactory
import java.util.*

open class AccountStudyRepository(
    private val accountJpaRepository: AccountJpaRepository
) {
    // logger
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun findEntity(id: Long): AccountEntity {
        logger.printLog("START [findById]")
        val find = accountJpaRepository.findById(id).orElse(null)
        logger.printLog("END [findById] : ${find != null}")
        return find ?: AccountEntity(accountNo = UUID.randomUUID().toString())
    }

    fun findEntity(accountNo: String): AccountEntity {
        logger.printLog("START [findByAccountNo]")
        val find = accountJpaRepository.findByAccountNo(accountNo)
        logger.printLog("END [findByAccountNo] : ${find != null}")
        return find ?: AccountEntity(accountNo = accountNo)
    }

    fun saveEntity(entity: AccountEntity): AccountEntity {
        logger.printLog("START [saveEntity]")
        val saved = accountJpaRepository.save(entity)
        logger.printLog("END [saveEntity]")
        return saved
    }

}