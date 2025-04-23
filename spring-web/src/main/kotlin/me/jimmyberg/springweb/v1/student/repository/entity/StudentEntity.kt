package me.jimmyberg.springweb.v1.student.repository.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import me.jimmyberg.springweb.v1.base.entity.BaseEntity

@Entity
class StudentEntity(

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    val id: String? = null,
    var name: String

) : BaseEntity()