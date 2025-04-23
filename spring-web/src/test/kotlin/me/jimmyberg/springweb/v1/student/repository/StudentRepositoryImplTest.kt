package me.jimmyberg.springweb.v1.student.repository

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.jimmyberg.springweb.v1.student.repository.entity.StudentEntity
import me.jimmyberg.springweb.v1.student.repository.jpa.StudentJpaRepository
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.UUID

@DataJpaTest
class StudentRepositoryImplTest(
    private val studentJpaRepository: StudentJpaRepository
) : BehaviorSpec({

    given("Student entity 정보 신규 생성하여") {
        val entity = StudentEntity(name = "test")

        `when`("DB 저장 성공인 경우") {
            val result = studentJpaRepository.save(entity)

            then("'id' 생성 결과 정상 확인한다") {
                result.id shouldNotBe null
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
        val entity = studentJpaRepository.save(StudentEntity(name = "test"))
        val studentId = entity.id!!

        `when`("DB 정보 변경 성공인 경우") {
            val student = studentJpaRepository.findById(studentId).orElse(null)!!
            val updated = StudentEntity(student.id, "test-updated")
            studentJpaRepository.save(updated)

            then("성공 결과 정상 확인한다") {
                val result = studentJpaRepository.findById(studentId).orElse(null)

                result shouldNotBe null
                result.name shouldBe "test-updated"
            }
        }
    }

})