package com.team.puddy.global.error.exception

import com.team.puddy.global.error.ErrorCode

open class BusinessException(val errorCode: ErrorCode, message: String? = null) : RuntimeException(message)
