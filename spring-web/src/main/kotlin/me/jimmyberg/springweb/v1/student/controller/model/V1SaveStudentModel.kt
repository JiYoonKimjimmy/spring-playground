package me.jimmyberg.springweb.v1.student.controller.model

import me.jimmyberg.springweb.infrastructure.common.model.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

data class V1SaveStudentRequest(
    val name: String
)

data class V1SaveStudentResponse(
    val data: V1StudentModel
) : BaseResponse<V1SaveStudentResponse>() {
    override fun success(httpStatus: HttpStatus): ResponseEntity<V1SaveStudentResponse> {
        return ResponseEntity(this, httpStatus)
    }

}