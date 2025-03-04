package me.jimmyberg.springredis.member.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.jimmyberg.springredis.member.repository.MemberCrudRepository
import me.jimmyberg.springredis.member.repository.MemberRepositoryImpl
import me.jimmyberg.springredis.member.repository.entity.MemberEntity
import me.jimmyberg.springredis.testsupport.CustomDataRedisTest
import kotlin.random.Random

@CustomDataRedisTest
class MemberFindServiceImplTest(
    private val memberCrudRepository: MemberCrudRepository
) : BehaviorSpec({

    val memberRepository = MemberRepositoryImpl(memberCrudRepository)
    val memberFindService = MemberFindServiceImpl(memberRepository)

    given("Member 데이터가 존재하지 않는 상황에서") {
        val nonExistentId = Random.nextLong(1000, Long.MAX_VALUE)

        `when`("memberId 로 Member 를 조회하면") {
            val result = memberFindService.findOne(nonExistentId)

            then("null 을 반환한다") {
                result shouldBe null
            }
        }
    }

    given("Member 데이터가 존재하는 상황에서") {
        val savedMember = MemberEntity(1L, "Jimmy")
        memberRepository.save(savedMember)

        `when`("memberId 로 Member 를 조회하면") {
            val result = memberFindService.findOne(savedMember.id)

            then("저장된 Member 정보가 정상적으로 조회된다") {
                result!! shouldNotBe null
                result.id shouldBe savedMember.id
                result.name shouldBe savedMember.name
            }
        }
    }

})