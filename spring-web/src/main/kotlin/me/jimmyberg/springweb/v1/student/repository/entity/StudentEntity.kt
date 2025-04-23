package me.jimmyberg.springweb.v1.student.repository.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class StudentEntity(
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    val id: String? = null,
    val name: String
)