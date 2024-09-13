package me.jimmyberg.springjpa.account.repository

import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import org.springframework.stereotype.Repository

@Repository
class AccountsRepositoryImpl(
    private val accountsJpaRepository: AccountsJpaRepository
) : AccountsRepository {

    override fun save(entity: AccountEntity): AccountEntity {
        return accountsJpaRepository.save(entity)
    }

    override fun findByAccountNo(accountNo: String): AccountEntity? {
        return accountsJpaRepository.findByAccountNo(accountNo)
    }

}