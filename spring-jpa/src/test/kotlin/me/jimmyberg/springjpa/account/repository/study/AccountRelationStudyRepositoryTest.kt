package me.jimmyberg.springjpa.account.repository.study

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.jimmyberg.springjpa.account.repository.AccountJpaRepositoryV2
import me.jimmyberg.springjpa.account.repository.entity.AccountEntityV2
import me.jimmyberg.springjpa.card.repository.entity.CardEntity
import me.jimmyberg.springjpa.account.repository.entity.AccountEntityFixture
import me.jimmyberg.springjpa.member.repository.entity.MemberEntityFixture
import me.jimmyberg.springjpa.util.generateUUID
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class AccountRelationStudyRepositoryTest(
    private val repository: AccountRelationStudyRepository,
    private val accountJpaRepository: AccountJpaRepositoryV2
) : StringSpec({

    val accountEntityFixture = AccountEntityFixture()
    val memberEntityFixture = MemberEntityFixture()

    val saveAccountEntity: () -> AccountEntityV2 = {
        val entity = accountEntityFixture.make(
            accountNo = generateUUID(),
            member = memberEntityFixture.generateEntity(name = "Jim")
        )
        accountJpaRepository.save(entity)
    }

    "test01 테스트 정상 확인한다" {
        // given
        val accountNo = generateUUID()
        val memberName = "Hello"

        // when
        val exception = shouldThrow<InvalidDataAccessApiUsageException> { repository.test01(accountNo, memberName) }

        // then
        exception shouldNotBe null
        exception.message shouldBe "detached entity passed to persist: me.jimmyberg.springjpa.member.repository.entity.MemberEntity"
    }

    "test02 테스트 정상 확인한다" {
        /**
         * 1. insert member
         * 2. insert account
         * 3. flush
         */
        // given
        val accountNo = generateUUID()
        val memberName = "Hello"

        // when
        val result = repository.test02(accountNo, memberName)

        // then
        result.id shouldNotBe null
        result.accountNo shouldBe accountNo
        result.member.name shouldBe memberName
    }

    "test03 테스트 정상 확인한다" {
        /**
         * 1. insert member
         * 2. insert account
         * 3. flush
         */
        // given
        val accountNo = generateUUID()
        val memberName = "Hello"

        // when
        val result = repository.test03(accountNo, memberName)

        // then
        result.id shouldNotBe null
        result.accountNo shouldBe accountNo
        result.member.name shouldBe memberName
    }

    "test04 테스트 정상 확인한다" {
        /**
         * 1. delete account
         * 2. delete member
         * 3. flush
         */
        // given
        val saved = saveAccountEntity()

        // when then
        repository.test04(saved.id!!)
    }

    "test05 테스트 정상 확인한다" {
        /**
         * 1. flush
         * 2. delete account
         * 3. delete member
         */
        // given
        val saved = saveAccountEntity()

        // when then
        repository.test05(saved.id!!)
    }

    "test06 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. flush
         */
        // given
        val saved = saveAccountEntity()
        val card = CardEntity(cardNo = generateUUID(), cardName = "테스트 카드")

        // when
        val result = repository.test06(saved.id!!, card)

        // then
        result.id shouldBe saved.id
        result.getCard()?.id shouldBe null
    }

    "test07 테스트 정상 확인한다" {
        /**
         * 1. insert card
         * 2. select account member
         * 3. select account member card
         * 4. select card
         * 5. update account
         * 6. flush
         */
        // given
        val saved = saveAccountEntity()
        val card = CardEntity(cardNo = generateUUID(), cardName = "테스트 카드")

        // when
        val result = repository.test07(saved.id!!, card)

        // then
        result.id shouldBe saved.id
        result.getCard()?.id shouldBe 1
    }

    "test08 테스트 정상 확인한다" {
        /**
         * 1. select account member
         * 2. select account member card
         * 3. insert card
         * 4. update account
         * 5. flush
         */
        // given
        val saved = saveAccountEntity()
        val card = CardEntity(cardNo = generateUUID(), cardName = "테스트 카드")

        // when
        val result = repository.test08(saved.id!!, card)

        // then
        result.id shouldBe saved.id
        result.getCard()?.id shouldBe 1
    }

    "test09 테스트 정상 확인한다" {
        /**
         * 1. select account member
         * 2. insert card
         * 3. update account
         * 4. flush
         */
        // given
        val saved = saveAccountEntity()
        val card = CardEntity(cardNo = generateUUID(), cardName = "테스트 카드")

        // when
        val result = repository.test09(saved.id!!, card)

        // then
        result.id shouldBe saved.id
        result.getCard()?.id shouldBe 1
    }

    "test10 테스트 정상 확인한다" {
        /**
         * 1. select account member
         * 2. flush
         * 3. insert card
         * 4. update account
         */
        // given
        val saved = saveAccountEntity()
        val card = CardEntity(cardNo = generateUUID(), cardName = "테스트 카드")

        // when
        val result = repository.test10(saved.id!!, card)

        // then
        result.id shouldBe saved.id
        result.getCard()?.id shouldBe 1
    }

    "test11 테스트 정상 확인한다" {
        /**
         * 1. select account member
         * 3. insert card
         * 2. flush
         * 4. update account
         */
        // given
        val saved = saveAccountEntity()
        val card = CardEntity(cardNo = generateUUID(), cardName = "테스트 카드")

        // when
        val result = repository.test11(saved, card)

        // then
        result.id shouldBe saved.id
        result.getCard()?.id shouldBe 1
    }

})