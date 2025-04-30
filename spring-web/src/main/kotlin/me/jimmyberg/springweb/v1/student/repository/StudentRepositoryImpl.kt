package me.jimmyberg.springweb.v1.student.repository

import me.jimmyberg.springweb.v1.student.service.mapper.StudentMapper
import me.jimmyberg.springweb.v1.student.repository.jpa.StudentJpaRepository
import me.jimmyberg.springweb.v1.student.service.domain.Student
import org.springframework.stereotype.Repository

@Repository
class StudentRepositoryImpl(
    private val studentMapper: StudentMapper,
    private val studentJpaRepository: StudentJpaRepository
) : StudentRepository {

    override fun save(student: Student): Student {
        return studentMapper.domainToEntity(student)
            .let { studentJpaRepository.save(it) }
            .let { studentMapper.entityToDomain(it) }
    }

}