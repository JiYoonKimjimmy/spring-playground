package me.jimmyberg.springjpa.account.repository

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.jimmyberg.springjpa.test.AccountFixture
import me.jimmyberg.springjpa.util.printLine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class AccountsRepositoryImplTest @Autowired constructor(
    private val accountJpaRepository: AccountJpaRepository
) : StringSpec({

    val accountFixture = AccountFixture()

    "Account Entity 생성하여 DB 정보 정상 확인한다" {
        // given
        val entity = accountFixture.generateEntity()

        // when
        val result = accountJpaRepository.save(entity)

        // then
        result shouldNotBe null
        result.id!! shouldBeGreaterThan 0
    }

    "Account Entity 정보 조회하여 `amount = 10000` 프로퍼티 변경하여 `insert` 쿼리 수행 정상 확인한다" {
        // given
        val entity = accountFixture.generateEntity()

        // when
        val result = entity.incrementAmount(10000)

        // then
        result.id shouldBe entity.id
        result.amount shouldBe 10000
    }

    "'accountNo' 기준 Account Entity 조회하여 'amount' 업데이트하여 저장 정상 확인한다" {
        // given
        val accountNo = accountFixture.generateAccountNo()
        val entity = accountJpaRepository.findByAccountNo(accountNo) ?: accountFixture.generateEntity(accountNo)

        printLine("step-1")

        entity.incrementAmount(10000)

        // when
        accountJpaRepository.save(entity)

        printLine("step-2")

        // then
        val result = accountJpaRepository.findByAccountNo(accountNo)
        result shouldNotBe null
    }

})