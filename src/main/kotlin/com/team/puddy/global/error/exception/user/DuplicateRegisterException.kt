package com.team.puddy.global.error.exception.user

import com.team.puddy.global.error.ErrorCode
import com.team.puddy.global.error.exception.BusinessException

class DuplicateRegisterException : BusinessException {
    constructor(errorCode: ErrorCode) : super(errorCode,errorCode.message)
    constructor(errorCode: ErrorCode, message: String) : super(errorCode, message)
}