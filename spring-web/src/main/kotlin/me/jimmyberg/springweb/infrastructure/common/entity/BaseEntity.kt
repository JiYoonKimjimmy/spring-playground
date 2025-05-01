package me.jimmyberg.springweb.infrastructure.common.entity

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class BaseEntity {

    @Version
    var version: Long = 0

    @CreatedDate
    var created: LocalDateTime? = null

    @LastModifiedDate
    var updated: LocalDateTime? = null

}