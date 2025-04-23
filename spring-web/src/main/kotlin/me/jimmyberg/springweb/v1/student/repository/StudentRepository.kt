package me.jimmyberg.springweb.v1.student.repository

import me.jimmyberg.springweb.v1.student.service.domain.Student

interface StudentRepository {

    fun save(student: Student): Student

}