package me.jimmyberg.springweb.infrastructure.error.exception

import me.jimmyberg.springweb.infrastructure.common.enumerate.ErrorCode

open class BaseException(
    val errorCode: ErrorCode,
    var detailMessage: String? = null
): RuntimeException()