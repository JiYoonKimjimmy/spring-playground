package me.jimmyberg.springweb.v1.student.service

import me.jimmyberg.springweb.v1.student.repository.StudentRepository
import me.jimmyberg.springweb.v1.student.service.domain.Student
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudentSaveServiceImpl(
    private val studentRepository: StudentRepository
) : StudentSaveService {

    @Transactional
    override fun save(student: Student): Student {
        return studentRepository.save(student)
    }

}