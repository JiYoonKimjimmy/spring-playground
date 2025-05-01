package me.jimmyberg.springweb.infrastructure.error.exception

import me.jimmyberg.springweb.infrastructure.common.enumerate.ErrorCode

class ResourceNotFoundException(errorCode: ErrorCode) : BaseException(errorCode)