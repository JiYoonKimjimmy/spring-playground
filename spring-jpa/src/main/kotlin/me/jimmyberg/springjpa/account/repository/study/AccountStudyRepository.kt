package me.jimmyberg.springjpa.account.repository.study

import me.jimmyberg.springjpa.account.repository.AccountJpaRepository
import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import me.jimmyberg.springjpa.util.printLog
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class AccountStudyRepository {
    @Autowired
    private lateinit var accountJpaRepository: AccountJpaRepository

    // logger
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun findEntity(accountNo: String): AccountEntity {
        logger.printLog("START [findByAccountNo]")
        val find = accountJpaRepository.findByAccountNo(accountNo) ?: AccountEntity(accountNo = accountNo)
        logger.printLog("END [findByAccountNo]")
        return find
    }

    fun saveEntity(entity: AccountEntity): AccountEntity {
        logger.printLog("START [saveEntity]")
        val saved = accountJpaRepository.save(entity)
        logger.printLog("END [saveEntity]")
        return saved
    }

}