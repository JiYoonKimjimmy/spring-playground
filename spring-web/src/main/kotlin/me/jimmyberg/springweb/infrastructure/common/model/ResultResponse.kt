package me.jimmyberg.springweb.infrastructure.common.model

import me.jimmyberg.springweb.infrastructure.common.COMPONENT_CODE
import me.jimmyberg.springweb.infrastructure.common.enumerate.ResultStatus
import me.jimmyberg.springweb.infrastructure.common.enumerate.ErrorCode
import me.jimmyberg.springweb.infrastructure.common.enumerate.FeatureCode

data class ResultResponse(
    val status: ResultStatus = ResultStatus.SUCCESS,
    val code: String? = null,
    val message: String? = null,
    val detailMessage: String? = null
) {

    constructor(featureCode: FeatureCode, errorCode: ErrorCode, detailMessage: String? = null): this(
        status = ResultStatus.FAILED,
        code = "${COMPONENT_CODE}_${featureCode.code}_${errorCode.code}",
        message = "${featureCode.message} failed. ${errorCode.message}.",
        detailMessage = detailMessage
    )

}