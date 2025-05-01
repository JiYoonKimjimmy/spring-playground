package me.jimmyberg.springweb.v1.student.service.mapper

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

}