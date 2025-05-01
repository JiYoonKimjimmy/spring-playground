package me.jimmyberg.springweb.v1.student.service.mapper

import me.jimmyberg.springweb.v1.student.controller.model.SaveStudentRequest
import me.jimmyberg.springweb.v1.student.controller.model.SaveStudentResponse
import me.jimmyberg.springweb.v1.student.controller.model.StudentModel
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

    fun domainToModel(domain: Student): StudentModel {
        return StudentModel(
            id = domain.id!!,
            name = domain.name
        )
    }

    fun requestToDomain(request: SaveStudentRequest): Student {
        return Student(name = request.name)
    }

    fun domainToResponse(domain: Student): SaveStudentResponse {
        return SaveStudentResponse(data = domainToModel(domain))
    }

}