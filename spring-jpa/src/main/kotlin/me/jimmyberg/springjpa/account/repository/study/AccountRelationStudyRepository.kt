package me.jimmyberg.springjpa.account.repository.study

import me.jimmyberg.springjpa.account.repository.V2AccountJpaRepository
import me.jimmyberg.springjpa.account.repository.entity.V2AccountEntity
import me.jimmyberg.springjpa.card.repository.CardJpaRepository
import me.jimmyberg.springjpa.card.repository.entity.CardEntity
import me.jimmyberg.springjpa.member.repository.MemberJpaRepository
import me.jimmyberg.springjpa.member.repository.entity.MemberEntity
import me.jimmyberg.springjpa.util.printStep
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class AccountRelationStudyRepository(
    private val v2AccountJpaRepository: V2AccountJpaRepository,
    private val memberJpaRepository: MemberJpaRepository,
    private val cardJpaRepository: CardJpaRepository
) {

    fun test01(accountNo: String, memberName: String): V2AccountEntity {
        val member = MemberEntity(name = memberName).printStep(1)
        memberJpaRepository.save(member).printStep(2)
        val account = V2AccountEntity(accountNo, member).printStep(3)
        return v2AccountJpaRepository.save(account).printStep(4)
    }

    @Transactional
    fun test02(accountNo: String, memberName: String): V2AccountEntity {
        val member = MemberEntity(name = memberName).printStep(1)
        memberJpaRepository.save(member).printStep(2)
        val account = V2AccountEntity(accountNo, member).printStep(3)
        return v2AccountJpaRepository.save(account).printStep(4)
    }

    @Transactional
    fun test03(accountNo: String, memberName: String): V2AccountEntity {
        val member = MemberEntity(name = memberName).printStep(1)
        val account = V2AccountEntity(accountNo, member).printStep(2)
        return v2AccountJpaRepository.save(account).printStep(3)
    }

    fun test04(accountId: Long) {
        v2AccountJpaRepository.deleteById(accountId).printStep(1)
    }

    @Transactional
    fun test05(accountId: Long) {
        v2AccountJpaRepository.deleteById(accountId).printStep(1)
    }

    fun test06(accountId: Long, card: CardEntity): V2AccountEntity {
        val account = v2AccountJpaRepository.findById(accountId).orElseThrow().printStep(1)
        return account.setCard(card).printStep(2)
    }

    fun test07(accountId: Long, card: CardEntity): V2AccountEntity {
        cardJpaRepository.save(card).printStep(1)
        val account = v2AccountJpaRepository.findById(accountId).orElseThrow().printStep(2)
        account.setCard(card).printStep(3)
        return v2AccountJpaRepository.save(account).printStep(4)
    }

    fun test08(accountId: Long, card: CardEntity): V2AccountEntity {
        val account = v2AccountJpaRepository.findById(accountId).orElseThrow().printStep(1)
        account.setCard(card).printStep(2)
        return v2AccountJpaRepository.save(account).printStep(3)
    }

    @Transactional
    fun test09(accountId: Long, card: CardEntity): V2AccountEntity {
        val account = v2AccountJpaRepository.findById(accountId).orElseThrow().printStep(1)
        account.setCard(card).printStep(2)
        return v2AccountJpaRepository.save(account).printStep(3)
    }

    @Transactional
    fun test10(accountId: Long, card: CardEntity): V2AccountEntity {
        val account = v2AccountJpaRepository.findById(accountId).orElseThrow().printStep(1)
        return account.setCard(card).printStep(2)
    }

    @Transactional
    fun test11(account: V2AccountEntity, card: CardEntity): V2AccountEntity {
        account.setCard(card).printStep(1)
        return v2AccountJpaRepository.save(account).printStep(2)
    }

}