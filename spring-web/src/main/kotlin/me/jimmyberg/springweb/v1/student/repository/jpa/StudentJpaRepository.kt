package me.jimmyberg.springweb.v1.student.repository.jpa

import me.jimmyberg.springweb.v1.student.repository.entity.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StudentJpaRepository : JpaRepository<StudentEntity, String> {

    fun findByName(name: String): StudentEntity?

}