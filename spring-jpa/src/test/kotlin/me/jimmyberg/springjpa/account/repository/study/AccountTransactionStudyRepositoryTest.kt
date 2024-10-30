package me.jimmyberg.springjpa.account.repository.study

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.jimmyberg.springjpa.account.repository.AccountJpaRepository
import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import me.jimmyberg.springjpa.account.repository.entity.AccountEntityFixture
import me.jimmyberg.springjpa.config.ApplicationConfig
import me.jimmyberg.springjpa.util.generateUUID
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import java.util.*

@Import(ApplicationConfig::class)
@SpringBootTest
class AccountTransactionStudyRepositoryTest(
    private val repository: AccountTransactionStudyRepository,
    private val accountJpaRepository: AccountJpaRepository
) : StringSpec({

    val accountEntityFixture = AccountEntityFixture()

    lateinit var saved: AccountEntity
    lateinit var saved2: AccountEntity

    beforeTest {
        saved = accountJpaRepository.save(accountEntityFixture.make(generateUUID(), 10000))
        saved2 = accountJpaRepository.save(accountEntityFixture.make(generateUUID()))
    }

    "test01 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. flush
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
         * 2. select & update
         * 3. flush
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
         * 2. flush
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
         * 3. flush
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
         * 2. flush
         * 3. update
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
         * 2. flush
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
         * 3. flush
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
         * 2. flush
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
         * 2. flush
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
         * 2. flush
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
         * 1. select entity1
         * 2. select entity2
         * 3. insert entity2
         * 4. flush
         * 5. update entity1
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
         * 1. select entity1
         * 2. select entity2
         * 3. flush
         * 4. update entity1
         * 5. update entity2
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
         * 1. select entity1
         * 2. select entity2
         * 3. flush
         * 4. update entity1
         * 5. update entity2
         */
        // given
        val id = saved.id!!
        val accountNo = saved2.accountNo
        val amount = 10000L

        // when
        val result = repository.test11(id, accountNo, amount)

        // then
        val entity1 = accountJpaRepository.findById(id).orElse(null)!!
        val entity2 = accountJpaRepository.findByAccountNo(accountNo)!!

        entity1.id shouldBe result.id
        entity1.amount shouldBe 20000
        entity2.amount shouldBe 10000
    }

    "test12 테스트 정상 확인한다" {
        /**
         * 1. insert entity1
         * 2. insert entity2
         * 3. flush
         */
        // given
        val accountNo1 = generateUUID()
        val accountNo2 = generateUUID()

        // when
        repository.test12(accountNo1, accountNo2)

        // then
        accountJpaRepository.findByAccountNo(accountNo1)?.accountNo shouldBe accountNo1
        accountJpaRepository.findByAccountNo(accountNo2)?.accountNo shouldBe accountNo2
    }

    "test13 테스트 정상 확인한다" {
        /**
         * 1. insert entity1
         * 2. insert entity2
         * 3. flush
         */
        // given
        val accountNo1 = generateUUID()
        val accountNo2 = generateUUID()

        // when
        repository.test13(accountNo1, accountNo2)

        // then
        accountJpaRepository.findByAccountNo(accountNo1)?.accountNo shouldBe accountNo1
        accountJpaRepository.findByAccountNo(accountNo2)?.accountNo shouldBe accountNo2
    }

})