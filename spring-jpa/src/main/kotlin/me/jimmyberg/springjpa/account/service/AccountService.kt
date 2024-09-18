package me.jimmyberg.springjpa.account.service

import me.jimmyberg.springjpa.account.repository.AccountRepository
import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {

    fun saveEntity(entity: AccountEntity): AccountEntity {
        return accountRepository.save(entity)
    }

    fun findEntity(accountNo: String): AccountEntity {
        return accountRepository.findByAccountNo(accountNo)
            ?: throw IllegalArgumentException("No account entity found")
    }

    fun incrementAmountWithoutTransaction(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).incrementAmount(amount)
        return accountRepository.save(entity)
    }

    @Transactional
    fun incrementAmount(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo)
        return entity.incrementAmount(amount)
    }

}