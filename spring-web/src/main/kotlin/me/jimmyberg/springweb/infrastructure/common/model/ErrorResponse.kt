package me.jimmyberg.springweb.infrastructure.common.model

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import me.jimmyberg.springweb.infrastructure.common.enumerate.ErrorCode
import me.jimmyberg.springweb.infrastructure.common.enumerate.FeatureCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ErrorResponse(
    val result: ResultResponse = ResultResponse()
) {

    companion object {

        fun toResponseEntity(featureCode: FeatureCode, errorCode: ErrorCode, detailMessage: String? = null): ResponseEntity<ErrorResponse> {
            return ResponseEntity(ErrorResponse(result = ResultResponse(featureCode, errorCode, detailMessage)), errorCode.status)
        }

        fun toResponseEntity(detailMessage: String): ResponseEntity<ErrorResponse> {
            val httpStatus = HttpStatus.valueOf(detailMessage.substringBefore(" : ").toInt())
            val errorResponse = jacksonObjectMapper().readValue(detailMessage.substringAfter(" : ").trim('"'), ErrorResponse::class.java)
            return ResponseEntity(ErrorResponse(result = errorResponse.result), httpStatus)
        }

    }

}