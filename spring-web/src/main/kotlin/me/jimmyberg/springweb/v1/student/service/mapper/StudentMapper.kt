package me.jimmyberg.springweb.v1.student.service.mapper

import me.jimmyberg.springweb.v1.student.controller.model.V1SaveStudentRequest
import me.jimmyberg.springweb.v1.student.controller.model.V1SaveStudentResponse
import me.jimmyberg.springweb.v1.student.controller.model.V1StudentModel
import me.jimmyberg.springweb.v1.student.repository.entity.StudentEntity
import me.jimmyberg.springweb.v1.student.service.domain.Student
import org.springframework.stereotype.Component

@Component
class StudentMapper {

    fun domainToEntity(domain: Student): StudentEntity {
        return StudentEntity(
            id = domain.id,
            name = domain.name
        )
    }

    fun entityToDomain(entity: StudentEntity): Student {
        return Student(
            id = entity.id,
            name = entity.name
        )
    }

    fun domainToModel(domain: Student): V1StudentModel {
        return V1StudentModel(
            id = domain.id!!,
            name = domain.name
        )
    }

    fun requestToDomain(request: V1SaveStudentRequest): Student {
        return Student(name = request.name)
    }

    fun domainToResponse(domain: Student): V1SaveStudentResponse {
        return V1SaveStudentResponse(data = domainToModel(domain))
    }

}