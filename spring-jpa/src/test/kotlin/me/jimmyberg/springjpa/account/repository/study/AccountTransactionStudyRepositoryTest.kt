package me.jimmyberg.springjpa.account.repository.study

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import me.jimmyberg.springjpa.test.AccountFixture
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AccountTransactionStudyRepositoryTest(
    private val repository: AccountTransactionStudyRepository
) : StringSpec({

    val accountFixture = AccountFixture()

    "test01 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. insert
         * 3. commit
         */
        // given
        val accountNo = accountFixture.generateAccountNo()
        val amount = 10000L

        // when
        val result = repository.test01(accountNo, amount)

        // then
        result.id!! shouldBeGreaterThan 0
        result.amount shouldBe amount
    }

    "test02 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. insert
         * 3. select > update
         * 4. commit
         */
        // given
        val accountNo = accountFixture.generateAccountNo()
        val amount = 10000L

        // when
        val result = repository.test02(accountNo, amount)

        // then
        result.id!! shouldBeGreaterThan 0
        result.amount shouldBe 20000
    }

    "test03 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. insert
         * 3. update
         * 4. commit
         */
        // given
        val accountNo = accountFixture.generateAccountNo()
        val amount = 10000L

        // when
        val result = repository.test03(accountNo, amount)

        // then
        result.id!! shouldBeGreaterThan 0
        result.amount shouldBe 20000
    }

    "test04 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. insert
         * 3. commit
         * 4. update
         */
        // given
        val accountNo = accountFixture.generateAccountNo()
        val amount = 10000L

        // when
        val result = repository.test04(accountNo, amount)

        // then
        result.id!! shouldBeGreaterThan 0
        result.amount shouldBe 20000
    }

    "test05 테스트 정상 확인한다" {
        /**
         * 1. select
         * 2. insert
         * 3. commit
         * 4. update
         */
        // given
        val accountNo = accountFixture.generateAccountNo()
        val amount = 10000L

        // when
        val result = repository.test05(accountNo, amount)

        // then
        result.id!! shouldBeGreaterThan 0
        result.amount shouldBe 20000
    }

})