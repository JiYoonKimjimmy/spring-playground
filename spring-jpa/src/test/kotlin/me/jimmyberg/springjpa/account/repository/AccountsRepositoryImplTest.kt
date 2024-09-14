package me.jimmyberg.springjpa.account.repository

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.*

@DataJpaTest
class AccountsRepositoryImplTest @Autowired constructor(
    private val accountJpaRepository: AccountJpaRepository
) : StringSpec({

    val accountsRepository = AccountRepositoryImpl(accountJpaRepository)

    "Account Entity 생성하여 DB 정보 정상 확인한다" {
        // given
        val entity = AccountEntity(accountNo = UUID.randomUUID().toString(), amount = 0, beforeAmount = 0)

        // when
        val result = accountsRepository.save(entity)

        // then
        result shouldNotBe null
        result.id!! shouldBeGreaterThan 0
    }

    "Account Entity 정보 조회하여 `amount = 10000` 프로퍼티 변경하여 `insert` 쿼리 수행 정상 확인한다" {
        // given
        val entity = accountsRepository.save(AccountEntity(accountNo = UUID.randomUUID().toString(), amount = 0, beforeAmount = 0))

        // when
        val result = entity.incrementAmount(10000)

        // then
        result.id shouldBe entity.id
        result.amount shouldBe 10000
    }

})