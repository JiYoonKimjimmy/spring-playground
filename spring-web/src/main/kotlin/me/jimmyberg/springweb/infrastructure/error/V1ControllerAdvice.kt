package me.jimmyberg.springweb.infrastructure.error

import me.jimmyberg.springweb.infrastructure.common.enumerate.FeatureCode
import me.jimmyberg.springweb.v1.student.controller.V1StudentManagementController
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = [V1StudentManagementController::class])
class V1StudentManagementControllerAdvice : BaseExceptionHandler(FeatureCode.V1_STUDENT_MANAGEMENT_SERVICE)