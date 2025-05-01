package me.jimmyberg.springweb.v1.student.repository

import me.jimmyberg.springweb.testsupport.generateUUID
import me.jimmyberg.springweb.v1.student.service.domain.Student
import java.util.concurrent.ConcurrentHashMap

class FakeStudentRepository : StudentRepository {

    private val students = ConcurrentHashMap<String, Student>()

    override fun save(student: Student): Student {
        val savedStudent = student.copy(id = student.id ?: generateUUID())
        students[savedStudent.id!!] = savedStudent
        return savedStudent
    }

    fun findById(id: String): Student? {
        return students[id]
    }

}
