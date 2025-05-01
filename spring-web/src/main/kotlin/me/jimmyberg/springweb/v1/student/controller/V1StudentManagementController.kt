package me.jimmyberg.springweb.v1.student.controller

import me.jimmyberg.springweb.v1.student.controller.model.V1SaveStudentRequest
import me.jimmyberg.springweb.v1.student.controller.model.V1SaveStudentResponse
import me.jimmyberg.springweb.v1.student.service.StudentSaveService
import me.jimmyberg.springweb.v1.student.service.mapper.StudentMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1")
@RestController
class V1StudentManagementController(
    private val studentMapper: StudentMapper,
    private val studentSaveService: StudentSaveService
) {

    @PostMapping("/student")
    fun saveStudent(@RequestBody request: V1SaveStudentRequest): ResponseEntity<V1SaveStudentResponse> {
        return studentMapper.requestToDomain(request)
            .let { studentSaveService.save(it) }
            .let { studentMapper.domainToResponse(it) }
            .success(HttpStatus.CREATED)
    }

}