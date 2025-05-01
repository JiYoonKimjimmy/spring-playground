package me.jimmyberg.springweb.v1.student.controller.model

import me.jimmyberg.springweb.infrastructure.common.model.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

data class SaveStudentRequest(
    val name: String
)

data class SaveStudentResponse(
    val data: StudentModel
) : BaseResponse<SaveStudentResponse>() {
    override fun success(httpStatus: HttpStatus): ResponseEntity<SaveStudentResponse> {
        return ResponseEntity(this, httpStatus)
    }

}