package me.jimmyberg.springweb.infrastructure.error

import me.jimmyberg.springweb.infrastructure.common.EMPTY
import me.jimmyberg.springweb.infrastructure.common.enumerate.ErrorCode
import me.jimmyberg.springweb.infrastructure.common.enumerate.FeatureCode
import me.jimmyberg.springweb.infrastructure.common.model.ErrorResponse
import me.jimmyberg.springweb.infrastructure.error.exception.BaseException
import me.jimmyberg.springweb.infrastructure.error.exception.ResourceNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException

@ControllerAdvice
class BaseExceptionHandler(
    private val featureCode: FeatureCode = FeatureCode.UNKNOWN
) {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(e: BaseException): ResponseEntity<ErrorResponse> {
        return ErrorResponse.Companion.toResponseEntity(featureCode, e.errorCode)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val message = e.bindingResult.fieldErrors.joinToString(". ") { it.defaultMessage ?: EMPTY }
        return ErrorResponse.Companion.toResponseEntity(featureCode, ErrorCode.ARGUMENT_NOT_VALID_ERROR, message)
    }

    @ExceptionHandler(HttpClientErrorException::class)
    fun handleHttpClientErrorException(e: HttpClientErrorException): ResponseEntity<ErrorResponse> {
        return ErrorResponse.Companion.toResponseEntity(featureCode, ErrorCode.EXTERNAL_SERVICE_ERROR, e.message)
    }

    @ExceptionHandler(BaseException::class)
    fun handleCustomException(e: BaseException): ResponseEntity<ErrorResponse> {
        return ErrorResponse.Companion.toResponseEntity(featureCode, e.errorCode, e.detailMessage)
    }

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(e: Exception): ResponseEntity<ErrorResponse> {
        return ErrorResponse.Companion.toResponseEntity(featureCode, ErrorCode.UNKNOWN_ERROR, e.message)
    }

}