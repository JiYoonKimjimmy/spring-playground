package me.jimmyberg.springjpa.account.repository.study

import me.jimmyberg.springjpa.account.repository.AccountJpaRepositoryV2
import me.jimmyberg.springjpa.account.repository.entity.AccountEntityV2
import me.jimmyberg.springjpa.member.repository.MemberJpaRepository
import me.jimmyberg.springjpa.member.repository.entity.MemberEntity
import me.jimmyberg.springjpa.util.printStep
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class AccountRelationStudyRepository(
    private val accountJpaRepositoryV2: AccountJpaRepositoryV2,
    private val memberJpaRepository: MemberJpaRepository
) {

    fun test01(accountNo: String, memberName: String): AccountEntityV2 {
        val member = MemberEntity(name = memberName).printStep(1)
        memberJpaRepository.save(member).printStep(2)
        val account = AccountEntityV2(accountNo, member).printStep(3)
        return accountJpaRepositoryV2.save(account).printStep(4)
    }

    @Transactional
    fun test02(accountNo: String, memberName: String): AccountEntityV2 {
        val member = MemberEntity(name = memberName).printStep(1)
        memberJpaRepository.save(member).printStep(2)
        val account = AccountEntityV2(accountNo, member).printStep(3)
        return accountJpaRepositoryV2.save(account).printStep(4)
    }

    @Transactional
    fun test03(accountNo: String, memberName: String): AccountEntityV2 {
        val member = MemberEntity(name = memberName).printStep(1)
        val account = AccountEntityV2(accountNo, member).printStep(2)
        return accountJpaRepositoryV2.save(account).printStep(3)
    }

}