package me.jimmyberg.springjpa.account.repository.study

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import me.jimmyberg.springjpa.test.AccountFixture
import me.jimmyberg.springjpa.util.generateUUID
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
        val accountNo = generateUUID()
        val amount = 10000L

        // when
        val result = repository.test01(accountNo, amount)

        // then
        result.id!! shouldBeGreaterThan 0
        result.amount shouldBe amount
    }

})