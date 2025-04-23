package me.jimmyberg.springweb.v1.student.repository.jpa

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.jimmyberg.springweb.v1.student.repository.entity.StudentEntity
import org.assertj.core.api.LocalDateTimeAssert
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.orm.ObjectOptimisticLockingFailureException
import org.springframework.transaction.annotation.Transactional
import java.util.UUID
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import java.time.LocalDateTime

@DataJpaTest
class StudentJpaRepositoryImplTest(
    private val studentJpaRepository: StudentJpaRepository,
    private val transactionManager: PlatformTransactionManager
) : BehaviorSpec({

    given("Student entity 정보 신규 생성하여") {
        val entity = StudentEntity(name = "test")

        `when`("DB 저장 성공인 경우") {
            val result = studentJpaRepository.save(entity)

            then("'id' 생성 결과 정상 확인한다") {
                result.id shouldNotBe null
                result.version shouldBe 0
                result.created!! shouldBeGreaterThan LocalDateTime.now()
                result.updated shouldBe null
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
        val transactionTemplate = TransactionTemplate(transactionManager)
        val entity = studentJpaRepository.save(StudentEntity(name = "test"))
        val studentId = entity.id!!

        `when`("DB 정보 변경 성공인 경우") {
            val student = studentJpaRepository.findById(studentId).orElse(null)!!
            student.name = "test-updated"

            then("성공 결과 정상 확인한다") {
                val result = studentJpaRepository.findById(studentId).orElse(null)

                result shouldNotBe null
                result.name shouldBe "test-updated"
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