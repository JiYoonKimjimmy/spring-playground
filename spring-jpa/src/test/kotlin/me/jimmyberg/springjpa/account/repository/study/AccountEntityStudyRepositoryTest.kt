package me.jimmyberg.springjpa.account.repository.study

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.jimmyberg.springjpa.account.repository.AccountJpaRepository
import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import me.jimmyberg.springjpa.test.AccountFixture
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
@SpringBootTest
class AccountEntityStudyRepositoryTest(
    private val repository: AccountEntityStudyRepository,
    private val accountJpaRepository: AccountJpaRepository
) : StringSpec({

    val accountFixture = AccountFixture()

    lateinit var saved: AccountEntity
    lateinit var saved2: AccountEntity

    beforeTest {
        saved = accountJpaRepository.save(accountFixture.generateEntity(accountFixture.generateAccountNo(), 10000))
        saved2 = accountJpaRepository.save(accountFixture.generateEntity(accountFixture.generateAccountNo()))
    }

    "test01 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. commit
         */
        // given
        val accountNo = saved.accountNo
        val amount = 10000L

        // when
        val result = repository.test01(accountNo, amount)

        // then
        result.id!! shouldBeGreaterThan 0
        result.amount shouldBe 20000

        val entity = accountJpaRepository.findByAccountNo(accountNo)!!
        entity.id shouldBe result.id
        entity.amount shouldNotBe 20000
    }

    "test01Sub 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. select > update
         * 3. commit
         */
        // given
        val accountNo = saved.accountNo
        val amount = 10000L

        // when
        val result = repository.test01Sub(accountNo, amount)

        // then
        result.id!! shouldBeGreaterThan 0
        result.amount shouldBe 20000

        val entity = accountJpaRepository.findByAccountNo(accountNo)!!
        entity.id shouldBe result.id
        entity.amount shouldBe 20000
    }

    "test02 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. commit
         * 3. update
         */
        // given
        val accountNo = saved.accountNo
        val amount = 10000L

        // when
        val result = repository.test02(accountNo, amount)

        // then
        val entity = accountJpaRepository.findByAccountNo(accountNo)!!
        entity.id shouldBe result.id
        entity.amount shouldBe 20000
    }

    "test03 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. select
         * 3. commit
         * 4. update
         */
        // given
        val id = saved.id!!
        val accountNo = saved.accountNo
        val amount = 10000L

        // when
        val result = repository.test03(id, accountNo, amount)

        // then
        val entity = accountJpaRepository.findByAccountNo(accountNo)!!
        entity.id shouldBe result.id
        entity.amount shouldBe 30000
    }

    "test03Sub 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. select
         * 3. commit
         * 4. update
         */
        // given
        val id = saved.id!!
        val accountNo = saved.accountNo
        val amount = 10000L

        // when
        val result = repository.test03Sub(id, accountNo, amount)

        // then
        val entity = accountJpaRepository.findByAccountNo(accountNo)!!
        entity.id shouldBe result.id
        entity.amount shouldBe 30000
    }

    "test04 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. commit
         * 3. update
         */
        // given
        val id = saved.id!!
        val accountNo = saved.accountNo
        val amount = 10000L

        // when
        val result = repository.test04(id, amount)

        // then
        val entity = accountJpaRepository.findByAccountNo(accountNo)!!
        entity.id shouldBe result.id
        entity.amount shouldBe 30000
    }

    "test05 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. select
         * 3. commit
         * 4. update
         */
        // given
        val accountNo = saved.accountNo
        val amount = 10000L

        // when
        val result = repository.test05(accountNo, amount)

        // then
        val entity = accountJpaRepository.findByAccountNo(accountNo)!!
        entity.id shouldBe result.id
        entity.amount shouldBe 30000
    }

    "test06 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. commit
         * 3. update
         */
        // given
        val id = saved.id!!
        val accountNo = saved.accountNo
        val amount = 10000L

        // when
        val result = repository.test06(id, amount)

        // then
        val entity = accountJpaRepository.findByAccountNo(accountNo)!!
        entity.id shouldBe result.id
        entity.amount shouldBe 30000
    }

    "test07 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. commit
         * 3. update
         */
        // given
        val id = saved.id!!
        val accountNo = saved.accountNo
        val amount = 10000L

        // when
        val result = repository.test07(id, amount)

        // then
        val entity = accountJpaRepository.findByAccountNo(accountNo)!!
        entity.id shouldBe result.id
        entity.amount shouldBe 30000
    }

    "test08 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. commit
         * 3. update
         */
        // given
        val id = saved.id!!
        val accountNo = saved.accountNo
        val amount = 10000L

        // when
        val result = repository.test08(id, amount)

        // then
        val entity = accountJpaRepository.findByAccountNo(accountNo)!!
        entity.id shouldBe result.id
        entity.amount shouldBe 30000
    }

    "test09 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. select
         * 3. insert
         * 4. commit
         * 5. update
         */
        // given
        val id = saved.id!!
        val accountNo = UUID.randomUUID().toString()
        val amount = 10000L

        // when
        val result = repository.test09(id, accountNo, amount)

        // then
        val entity1 = accountJpaRepository.findById(id).orElse(null)!!
        entity1.id shouldBe result.id
        entity1.amount shouldBe 20000

        val entity2 = accountJpaRepository.findByAccountNo(accountNo)!!
        entity2.amount shouldBe 10000
    }

    "test10 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. select
         * 3. commit
         * 4. update
         * 5. update
         */
        // given
        val id = saved.id!!
        val accountNo = saved2.accountNo
        val amount = 10000L

        // when
        val result = repository.test10(id, accountNo, amount)

        // then
        val entity1 = accountJpaRepository.findById(id).orElse(null)!!
        entity1.id shouldBe result.id
        entity1.amount shouldBe 20000

        val entity2 = accountJpaRepository.findByAccountNo(accountNo)!!
        entity2.amount shouldBe 10000
    }

    "test11 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. select
         * 3. commit
         * 4. update
         * 5. update
         */
        // given
        val id = saved.id!!
        val accountNo = saved2.accountNo
        val amount = 10000L

        // when
        val result = repository.test11(id, accountNo, amount)

        // then
        val entity1 = accountJpaRepository.findById(id).orElse(null)!!
        entity1.id shouldBe result.id
        entity1.amount shouldBe 20000

        val entity2 = accountJpaRepository.findByAccountNo(accountNo)!!
        entity2.amount shouldBe 10000
    }

    "test12 테스트 정상 확인한다" {
        /**
         * 1. insert
         * 2. insert
         * 3. commit
         */
        // given
        val accountNo1 = accountFixture.generateAccountNo()
        val accountNo2 = accountFixture.generateAccountNo()

        // when
        repository.test12(accountNo1, accountNo2)

        // then
        accountJpaRepository.findByAccountNo(accountNo1) shouldNotBe null
        accountJpaRepository.findByAccountNo(accountNo2) shouldNotBe null
    }

    "test13 테스트 정상 확인한다" {
        /**
         * 1. insert
         * 2. insert
         * 3. commit
         */
        // given
        val accountNo1 = accountFixture.generateAccountNo()
        val accountNo2 = accountFixture.generateAccountNo()

        // when
        repository.test13(accountNo1, accountNo2)

        // then
        accountJpaRepository.findByAccountNo(accountNo1) shouldNotBe null
        accountJpaRepository.findByAccountNo(accountNo2) shouldNotBe null
    }

})