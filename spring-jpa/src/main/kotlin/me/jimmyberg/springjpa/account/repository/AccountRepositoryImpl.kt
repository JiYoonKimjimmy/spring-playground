package me.jimmyberg.springjpa.account.repository

import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl(
    private val accountJpaRepository: AccountJpaRepository
) : AccountRepository {

    override fun save(entity: AccountEntity): AccountEntity {
        return accountJpaRepository.save(entity)
    }

    override fun findByAccountNo(accountNo: String): AccountEntity? {
        return accountJpaRepository.findByAccountNo(accountNo)
    }

}