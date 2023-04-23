package com.team.puddy.global.error.exception.user

import com.team.puddy.global.error.ErrorCode
import com.team.puddy.global.error.exception.BusinessException

class InvalidPasswordException(errorCode: ErrorCode) : BusinessException(errorCode) {

}
