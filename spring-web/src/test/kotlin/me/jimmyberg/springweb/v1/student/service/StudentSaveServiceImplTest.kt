package me.jimmyberg.springweb.v1.student.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.jimmyberg.springweb.v1.student.repository.FakeStudentRepository
import me.jimmyberg.springweb.v1.student.service.domain.Student

class StudentSaveServiceImplTest : BehaviorSpec({

    val fakeStudentRepository = FakeStudentRepository()
    val studentSaveService = StudentSaveServiceImpl(fakeStudentRepository)

    given("Student 정보 저장 요청하여") {
        val student = Student(name = "student")

        `when`("신규 정보 저장 성공인 경우") {
            val result = studentSaveService.save(student)

            then("저장 결과 정상 확인한다") {
                result.id shouldNotBe null
                result.name shouldBe "student"
            }

            then("신규 Student 정보 'id' 기준 DB 조회 결과 정상 확인한다") {
                val entity = fakeStudentRepository.findById(result.id!!)

                entity!! shouldNotBe null
                entity.id shouldBe result.id
                entity.name shouldBe result.name
            }
        }
    }


})
