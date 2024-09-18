package me.jimmyberg.springjpa.account.repository.study

import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import me.jimmyberg.springjpa.util.printStep
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Repository
class AccountEntityStudyRepository : AccountStudyRepository() {

    fun test01(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).printStep(1)
        return entity.incrementAmount(amount).printStep(2)
    }

    fun test01Sub(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).printStep(1)
        entity.incrementAmount(amount).printStep(2)
        return saveEntity(entity)
    }

    @Transactional
    fun test02(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).printStep(1)
        return entity.incrementAmount(amount).printStep(2)
    }

    @Transactional
    fun test03(id: Long, accountNo: String, amount: Long): AccountEntity {
        val entity1 = findEntity(id).printStep(1)
        val entity2 = findEntity(accountNo).printStep(2)
        entity1.incrementAmount(amount).printStep(3)
        entity2.incrementAmount(amount).printStep(4)
        return entity2.printStep(5)
    }

    @Transactional
    fun test03Sub(id: Long, accountNo: String, amount: Long): AccountEntity {
        val entity1 = findEntity(accountNo).printStep(2)
        val entity2 = findEntity(id).printStep(1)
        entity1.incrementAmount(amount).printStep(3)
        entity2.incrementAmount(amount).printStep(4)
        return entity2.printStep(5)
    }

    @Transactional
    fun test04(id: Long, amount: Long): AccountEntity {
        val entity1 = findEntity(id).printStep(1)
        val entity2 = findEntity(id).printStep(2)
        entity1.incrementAmount(amount).printStep(3)
        entity2.incrementAmount(amount).printStep(4)
        return entity2.printStep(5)
    }

    @Transactional
    fun test05(accountNo: String, amount: Long): AccountEntity {
        val entity1 = findEntity(accountNo).printStep(1)
        val entity2 = findEntity(accountNo).printStep(2)
        entity1.incrementAmount(amount).printStep(3)
        entity2.incrementAmount(amount).printStep(4)
        return entity2.printStep(5)
    }

    @Transactional
    fun test06(id: Long, amount: Long): AccountEntity {
        val entity = findEntity(id).printStep(1)
        test06Sub(id, amount).printStep(4)
        entity.incrementAmount(amount).printStep(5)
        return entity.printStep(6)
    }

    private fun test06Sub(id: Long, amount: Long): AccountEntity {
        val entity = findEntity(id).printStep(2)
        return entity.incrementAmount(amount).printStep(3)
    }

    @Transactional
    fun test07(id: Long, amount: Long): AccountEntity {
        val entity = findEntity(id).printStep(1)
        test07Sub(id, amount).printStep(4)
        entity.incrementAmount(amount).printStep(5)
        return entity.printStep(6)
    }

    @Transactional
    fun test07Sub(id: Long, amount: Long): AccountEntity {
        val entity = findEntity(id).printStep(2)
        return entity.incrementAmount(amount).printStep(3)
    }

    @Transactional
    fun test08(id: Long, amount: Long): AccountEntity {
        val entity = findEntity(id).printStep(1)
        test08Sub(id, amount).printStep(4)
        entity.incrementAmount(amount).printStep(5)
        return entity.printStep(6)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun test08Sub(id: Long, amount: Long): AccountEntity {
        val entity = findEntity(id).printStep(2)
        entity.incrementAmount(amount).printStep(3)
        return saveEntity(entity)
    }

    @Transactional
    fun test09(id: Long, accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(id).printStep(1)
        test09Sub(accountNo, amount).printStep(5)
        entity.incrementAmount(amount).printStep(6)
        return entity.printStep(7)
    }

    private fun test09Sub(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).printStep(2)
        entity.incrementAmount(amount).printStep(3)
        return saveEntity(entity).printStep(4)
    }

    @Transactional
    fun test10(id: Long, accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(id).printStep(1)
        test10Sub(accountNo, amount).printStep(5)
        entity.incrementAmount(amount).printStep(6)
        return entity.printStep(7)
    }

    private fun test10Sub(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).printStep(2)
        entity.incrementAmount(amount).printStep(3)
        return saveEntity(entity).printStep(4)
    }

    @Transactional
    fun test11(id: Long, accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(id).printStep(1)
        test11Sub(accountNo, amount).printStep(5)
        entity.incrementAmount(amount).printStep(6)
        return entity.printStep(7)
    }

    @Transactional
    fun test11Sub(accountNo: String, amount: Long): AccountEntity {
        val entity = findEntity(accountNo).printStep(2)
        entity.incrementAmount(amount).printStep(3)
        return saveEntity(entity).printStep(4)
    }

    fun test12(accountNo1: String, accountNo2: String) {
        val entity1 = AccountEntity(accountNo = accountNo1, amount = 0, beforeAmount = 0).printStep(1)
        val entity2 = AccountEntity(accountNo = accountNo2, amount = 0, beforeAmount = 0).printStep(2)
        saveEntity(entity1).printStep(3)
        saveEntity(entity2).printStep(4)
    }

    @Transactional
    fun test13(accountNo1: String, accountNo2: String) {
        val entity1 = AccountEntity(accountNo = accountNo1, amount = 0, beforeAmount = 0).printStep(1)
        val entity2 = AccountEntity(accountNo = accountNo2, amount = 0, beforeAmount = 0).printStep(2)
        saveEntity(entity1).printStep(3)
        saveEntity(entity2).printStep(4)
    }

}