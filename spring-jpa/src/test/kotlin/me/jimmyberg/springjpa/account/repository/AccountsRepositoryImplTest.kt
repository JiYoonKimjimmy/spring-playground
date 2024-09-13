package me.jimmyberg.springjpa.account.repository

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldNotBe
import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.*

@DataJpaTest
class AccountsRepositoryImplTest @Autowired constructor(
    private val accountsJpaRepository: AccountsJpaRepository
) : StringSpec({

    val accountsRepository = AccountsRepositoryImpl(accountsJpaRepository)

    "Account Entity 생성하여 DB 정보 정상 확인한다" {
        // given
        val entity = AccountEntity(accountNo = UUID.randomUUID().toString(), amount = 0, beforeAmount = 0)

        // when
        val result = accountsRepository.save(entity)

        // then
        result shouldNotBe null
        result.id!! shouldBeGreaterThan 0
    }

})