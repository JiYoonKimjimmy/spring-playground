package me.jimmyberg.springjpa.account.repository.study

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.jimmyberg.springjpa.util.generateUUID
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class AccountRelationStudyRepositoryTest(
    private val repository: AccountRelationStudyRepository
) : StringSpec({

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
         * 3. commit
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
         * 3. commit
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

})