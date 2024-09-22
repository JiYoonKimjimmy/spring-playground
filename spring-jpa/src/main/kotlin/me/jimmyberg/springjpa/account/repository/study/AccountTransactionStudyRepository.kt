package me.jimmyberg.springjpa.account.repository.study

import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import me.jimmyberg.springjpa.util.printStep
import org.springframework.stereotype.Repository

@Repository
class AccountTransactionStudyRepository : AccountStudyRepository() {

    fun test01(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).printStep(1)
        entity.incrementAmount(amount).printStep(2)
        return saveEntity(entity).printStep(3)
    }

}