package me.jimmyberg.springjpa.account.repository

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import me.jimmyberg.springjpa.test.AccountFixture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AccountRepository01Test @Autowired constructor(
    private val accountRepository: AccountRepository01
) : StringSpec({

    val accountFixture = AccountFixture()

    "test01 테스트 정상 확인한다" {
        // given
        val accountNo = accountFixture.generateAccountNo()
        val amount = 10000L

        // when
        val result = accountRepository.test01(accountNo, amount)

        // then
        result.id!! shouldBeGreaterThan 0
        result.amount shouldBe amount
    }

    "test02 테스트 정상 확인한다" {
        // given
        val accountNo = accountFixture.generateAccountNo()
        val amount = 10000L

        // when
        val result = accountRepository.test02(accountNo, amount)

        // then
        result.id!! shouldBeGreaterThan 0
        result.amount shouldBe 20000
    }

    "test03 테스트 정상 확인한다" {
        // given
        val accountNo = accountFixture.generateAccountNo()
        val amount = 10000L

        // when
        val result = accountRepository.test03(accountNo, amount)

        // then
        result.id!! shouldBeGreaterThan 0
        result.amount shouldBe 20000
    }

    "test04 테스트 정상 확인한다" {
        // given
        val accountNo = accountFixture.generateAccountNo()
        val amount = 10000L

        // when
        val result = accountRepository.test04(accountNo, amount)

        // then
        result.id!! shouldBeGreaterThan 0
        result.amount shouldBe 20000
    }

    "test05 테스트 정상 확인한다" {
        // given
        val accountNo = accountFixture.generateAccountNo()
        val amount = 10000L

        // when
        val result = accountRepository.test05(accountNo, amount)

        // then
        result.id!! shouldBeGreaterThan 0
        result.amount shouldBe 20000
    }

})