package me.jimmyberg.springweb.v1.student.service

import me.jimmyberg.springweb.v1.student.service.domain.Student

interface StudentSaveService {

    fun save(student: Student): Student

}