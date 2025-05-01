package me.jimmyberg.springweb.v1.student.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import io.kotest.core.spec.style.BehaviorSpec
import me.jimmyberg.springweb.v1.student.controller.model.V1SaveStudentRequest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@AutoConfigureMockMvc
@SpringBootTest
class V1StudentManagementControllerTest(
    private val mockMvc: MockMvc
) : BehaviorSpec({

    val objectMapper = jacksonObjectMapper().registerModule(kotlinModule())
    val saveStudentUrl = "/api/v1/student"

    given("회원 정보 저장 API 요청되어") {
        val request = V1SaveStudentRequest(name = "student")

        `when`("신규 정상 정보인 경우") {
            val result = mockMvc
                .post(saveStudentUrl) {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(request)
                }
                .andDo { print() }

            then("'201 Created' 응답 결과 정상 확인한다") {
                result.andExpect {
                    status { isCreated() }
                    jsonPath("$.data.id") { isNotEmpty() }
                    jsonPath("$.data.name") { value("student") }
                }
            }
        }
    }


})
