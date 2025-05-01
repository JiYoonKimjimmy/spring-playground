package me.jimmyberg.springweb.v1.student.repository.jpa

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.jimmyberg.springweb.infrastructure.config.ApplicationConfig
import me.jimmyberg.springweb.v1.student.repository.entity.StudentEntity
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import java.time.LocalDateTime
import java.util.*

@Import(ApplicationConfig::class)
@DataJpaTest
class StudentJpaRepositoryImplTest(
    private val studentJpaRepository: StudentJpaRepository,
) : BehaviorSpec({

    lateinit var saved: StudentEntity
    beforeSpec {
        saved = studentJpaRepository.save(StudentEntity(name = "student"))
    }

    given("Student entity 정보 신규 생성하여") {
        val entity = StudentEntity(name = "test")

        `when`("DB 저장 성공인 경우") {
            val result = studentJpaRepository.save(entity)

            then("'id' 생성 결과 정상 확인한다") {
                result.id shouldNotBe null
                result.version shouldBe 0
                result.created!! shouldBeLessThan LocalDateTime.now()
                result.updated shouldBe result.created
            }
        }
    }

    given("Student Entity 정보 'id' 조건 조회하여") {

        `when`("DB 정보 없는 경우") {
            val studentId = UUID.randomUUID().toString()

            then("'null' 결과 정상 확인한다") {
               val result = studentJpaRepository.findById(studentId).orElse(null)

                result shouldBe null
            }
        }

        `when`("DB 정보 있는 경우") {
            val entity = studentJpaRepository.save(StudentEntity(name = "test"))
            val studentId = entity.id!!

            then("조회 결과 정상 확인한다") {
                val result = studentJpaRepository.findById(studentId).orElse(null)

                result shouldNotBe null
                result.id shouldBe studentId
            }
        }
    }

    given("Student Entity 정보 'name' 정보 변경하여") {
        val updateName = "student-updated"
        saved.name = updateName

        `when`("DB 정보 변경 성공인 경우") {
            studentJpaRepository.save(saved)

            then("성공 결과 정상 확인한다") {
                val result = studentJpaRepository.findByName(updateName)

                result!! shouldNotBe null
                result.name shouldBe "student-updated"
                result.version shouldBe 1
                result.created shouldBe saved.created
                result.updated!! shouldBeGreaterThan result.created!!
            }
        }
    }

    given("Student Entity 정보 삭제하여") {
        val entity = studentJpaRepository.save(StudentEntity(name = "test"))
        val studentId = entity.id!!

        `when`("DB 정보 삭제 성공인 경우") {
            studentJpaRepository.deleteById(studentId)

            then("성공 결과 정상 확인한다") {
                val result = studentJpaRepository.findById(studentId).orElse(null)

                result shouldBe null
            }
        }
    }

})