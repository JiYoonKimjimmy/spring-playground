package me.jimmyberg.springredis.member.repository

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.jimmyberg.springredis.config.EmbeddedRedisConfig
import me.jimmyberg.springredis.member.repository.entity.MemberEntity
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import

@Import(EmbeddedRedisConfig::class)
@DataRedisTest
class MemberRepositoryImplTest(
    private val memberCrudRepository: MemberCrudRepository
) : StringSpec({

    val memberRepository = MemberRepositoryImpl(memberCrudRepository)

    "Member 정보 저장하여 정상 확인한다" {
        // given
        val entity = MemberEntity(1L, "Jimmy")

        // when
        memberRepository.save(entity)

        // then
        val saved = memberRepository.findById(entity.id)!!
        saved.name shouldBe "Jimmy"
    }

})