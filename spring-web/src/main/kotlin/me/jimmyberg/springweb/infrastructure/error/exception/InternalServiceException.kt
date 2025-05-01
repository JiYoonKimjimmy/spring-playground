package me.jimmyberg.springweb.infrastructure.error.exception

import me.jimmyberg.springweb.infrastructure.common.enumerate.ErrorCode

class InternalServiceException(errorCode: ErrorCode) : BaseException(errorCode)